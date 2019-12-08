package Domaine;


import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.PathIterator;
import java.io.Serializable;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import java.lang.Cloneable;

import static Domaine.Outils.genereSommetsPolygon;

public class Surface implements Cloneable, Serializable {

    public Polygon polygone;
    public boolean estUnTrou;
    public ArrayList<Surface> trous;
    private Revetement revetement;
    private ArrayList<Tuile> listeTuiles = new ArrayList<>();
    private int tailleDuCoulis = 4;
    private Color couleurCoulis = Color.WHITE;;
    private String couleurCoulisText = "Blanc";

    public Surface(List<Point> listePoints, boolean trou) {
        int[] coords_x = listePoints.stream().mapToInt(point -> point.x).toArray();
        int[] coords_y = listePoints.stream().mapToInt(point -> point.y).toArray();
        polygone = new Polygon(coords_x, coords_y, listePoints.size());
        this.estUnTrou = trou;
        this.trous = new ArrayList<>();
        this.revetement = new Revetement();
        MajListeTuiles();
    }

    public Surface clone(){
        Surface nouvelleSurface;
        try{
            nouvelleSurface = (Surface) super.clone();
            nouvelleSurface.trous = trous.stream().map(Surface::clone).collect(Collectors.toCollection(ArrayList::new));
        }
        catch (CloneNotSupportedException e) {
            nouvelleSurface =  null;
        }
        return nouvelleSurface;
    }

    public void changerPoints(List<Point> listePoints){
        int[] coords_x = listePoints.stream().mapToInt(point -> point.x).toArray();
        int[] coords_y = listePoints.stream().mapToInt(point -> point.y).toArray();
        polygone = new Polygon(coords_x, coords_y, listePoints.size());
        MajListeTuiles();
    }

    //méthode permettant de déplacer une surface selon le vecteur de déplacement reçu
    public void deplacerSurface(int deplacement_x, int deplacement_y){
        int[] nouveaux_x = Arrays.stream(polygone.xpoints).map(x -> x + deplacement_x).toArray();
        int[] nouveaux_y = Arrays.stream(polygone.ypoints).map(x -> x + deplacement_y).toArray();
        polygone = new Polygon(nouveaux_x, nouveaux_y, polygone.npoints);
        MajListeTuiles();
        trous.forEach(trou -> trou.deplacerSurface(deplacement_x, deplacement_y));
    }


    public ArrayList<Point> getListePoints(){
        ArrayList<Point> listePoints = new ArrayList<Point>();
        for(int i = 0; i < polygone.npoints; i++){
            listePoints.add(new Point(polygone.xpoints[i], polygone.ypoints[i]));
        }
        return listePoints;
    }

    public void setListePoints(ArrayList<Point> listePoints){
        int[] coords_x = listePoints.stream().mapToInt(point -> point.x).toArray();
        int[] coords_y = listePoints.stream().mapToInt(point -> point.y).toArray();
        this.polygone = new Polygon(coords_x, coords_y, listePoints.size());
    }

    public Area getAireSansTrou(){
        Area aireSansTrou = new Area(polygone);
        trous.forEach(trou -> aireSansTrou.subtract(new Area(trou.polygone)));
        return aireSansTrou;
    }

    public void setDimensions(int hauteur, int largeur){
        ArrayList<Point> points = getListePoints();
        Rectangle limites = polygone.getBounds();
        if(hauteur != 0 && largeur != 0){
            points = generePoints(hauteur, largeur, points, limites, limites.x, limites.y);
            changerPoints(points);
            for(Surface trou : trous){
                Rectangle limitesTuile = trou.polygone.getBounds();
                points = generePoints(hauteur,largeur, trou.getListePoints(), limitesTuile, limitesTuile.x, limitesTuile.y);
                trou.changerPoints(points);
            }
        }
    }

    static ArrayList<Point> generePoints(int hauteur, int largeur, ArrayList<Point> points, Rectangle limites, int x, int y) {
        points = points.stream().map(point ->{ // TODO faire une méthode général dans outils
            int nouveau_x = (largeur * Math.abs(point.x - limites.x) / limites.width) + x;
            int nouveau_y = (hauteur * Math.abs(point.y - limites.y) / limites.height) + y;
            return new Point(nouveau_x, nouveau_y);
        }).collect(Collectors.toCollection(ArrayList::new));
        return points;
    }

    public boolean fusionner(Surface s){
        Area aire = new Area(polygone);
        aire.add(new Area(s.polygone));
        if(!aire.isSingular()){return false;}
        PathIterator iterator = aire.getPathIterator(null);
        double[] coords = new double[6];
        Polygon nouveau_polygone = new Polygon();
        calculIntersections(iterator, nouveau_polygone, coords);
        polygone = nouveau_polygone;
        setListeTuiles((genererListeDeTuiles()));
        trous.addAll(s.trous);
        return true;
    }

    public boolean fusionnerTrou(Surface s){
        if (fusionner(s)){
            trous.add(s);
            return true;
        }
        return false;
    }


    public ArrayList<Tuile> genererListeDeTuiles(){
        // "Installation droite", "Installation " "imitation parquet", "Installation en décallé", "Installation en chevron", "Installation en L"
        String motif = this.revetement.getMotifTuiles();
        int tailleCoulis = this.getTailleDuCoulis();

        int coordXduBound = polygone.getBounds().x; int coordYduBond = polygone.getBounds().y;
        int boundsWidth = polygone.getBounds().width; int boundsHeight = polygone.getBounds().height;
        int tuileWidth = revetement.getLongueurTuile() ; int tuileHeight = revetement.getHauteurTuile() ;

        if (motif.equals("Installation Droite")){
            int ancienneWidth = tuileWidth;
            tuileWidth = tuileHeight;
            tuileHeight = ancienneWidth;
        }

        int nbTuilesX = (boundsWidth / (tuileWidth + tailleCoulis)); int nbTuilesY = (boundsHeight / (tuileHeight + tailleCoulis));
        ArrayList<Tuile> newListeTuiles = new ArrayList<>();

        if(estUnTrou){return newListeTuiles;}

        int j = 0;
        while (j <= nbTuilesY){
            int i = 0;
            int positionEnX = coordXduBound;
            if (motif.equals("Installation en décallé") && (j % 2 == 0) ){
                int offset = tuileWidth / 2;
                positionEnX = positionEnX -offset;
            }
            while (i <= nbTuilesX ) {

                newListeTuiles.add(new Tuile(genereSommetsPolygon(positionEnX, coordYduBond, tuileWidth, tuileHeight)));
                positionEnX += tuileWidth + tailleCoulis;
                i++;
            }
            coordYduBond += tuileHeight + tailleCoulis;
            positionEnX = coordXduBound;
            j++;
        } ;
        return IntersectionTuiles(newListeTuiles);
    }

    private ArrayList<Tuile> IntersectionTuiles(ArrayList<Tuile> ListeDetuiles){
        // pour taille du coulis, on va générer un nouveau polygone plus petit qui va contenir seulement les tuiles
        // On peut utiliser la méthode "setDimension" pour changer la taille de la surface
        // On va extraire une méthode plus générale de set dimension pour ne pas changer la taille du polygone
        // On va alors calculer l'intersections avec le polygone SurfaceTuile plutôt que le polygone lui même
        // pour les trous, on pourrait ajouter la taille du coulis à sa taille pour l'intersection, mais pas au trou lui-même
        ArrayList<Tuile> newListeTuiles = new ArrayList<>();
        Area areaSurface = new Area(polygone);
        for (Tuile tuile : ListeDetuiles){
            Area areaTuile = new Area(tuile.getPolygone());
            areaTuile.intersect(areaSurface);
            PathIterator iterTuile = areaTuile.getPathIterator(null);
            Polygon newPolyTuile = new Polygon();
            double[] coordsTuile = new double[6];
            calculIntersections(iterTuile, newPolyTuile, coordsTuile);
            Tuile newTuile = new Tuile(newPolyTuile);
            if(newTuile.getHeight() != 0 && newTuile.getLength() != 0){
                newListeTuiles.add(newTuile);
            }
        }
        return newListeTuiles;
    }

    // TODO à placer dans outils ?
    private void calculIntersections(PathIterator iterTuile, Polygon newPolyTuile, double[] coordsTuile) {
        while (!iterTuile.isDone()){
            int type = iterTuile.currentSegment(coordsTuile);
            int x = (int) coordsTuile[0];
            int y = (int) coordsTuile[1];
            if(type != PathIterator.SEG_CLOSE) {
                newPolyTuile.addPoint(x, y);
            }
            iterTuile.next();
        }
    }

    private void MajListeTuiles(){
        // ensemble des méthodes utilisées pour la génération et l'ajout de tuiles
        // TODO si pas plus de code que la ligne finalement, enlever !
        setListeTuiles(genererListeDeTuiles());
    }

    public Tuile getTuileAtPoint(Point point){
        for (Tuile tuile : listeTuiles){
            if(tuile.getPolygone().contains(point)){
                return tuile;
            }
        }
        return new Tuile(new Polygon());
    }

    public void setRevetement(Revetement revetement) {
        this.revetement = revetement;
        setListeTuiles((genererListeDeTuiles()));
    }

    public Revetement getRevetement() {
        return revetement;
    }

    public ArrayList<Tuile> getListeTuiles() {
        return listeTuiles;
    }

    public void setListeTuiles(ArrayList<Tuile> listeTuiles) {
        this.listeTuiles = listeTuiles;
    }

    public int getTailleDuCoulis() {
        return tailleDuCoulis;
    }

    public void setTailleDuCoulis(int tailleDuCoulis) {
        this.tailleDuCoulis = tailleDuCoulis;
        MajListeTuiles();
    }

    public Color getCouleurCoulis() {
        return couleurCoulis;
    }

    public boolean EstUnTrou() {
        return estUnTrou;
    }

    public void setCouleurCoulis(String couleurCoulis) {
        switch (couleurCoulis){
            case "Rouge":
                this.couleurCoulis = Color.red;
                this.couleurCoulisText = couleurCoulis;
                break;
            case "Blanc":
                this.couleurCoulis = Color.white;
                this.couleurCoulisText = couleurCoulis;
                break;
            case "Gris":
                this.couleurCoulis = Color.lightGray;
                this.couleurCoulisText = couleurCoulis;
                break;
            case "Bleu":
                this.couleurCoulis = Color.blue;
                this.couleurCoulisText = couleurCoulis;
                break;
            case "Vert":
                this.couleurCoulis = Color.green;
                this.couleurCoulisText = couleurCoulis;
                break;
        }
    }

    public String getCouleurCoulisText() {
        return couleurCoulisText;
    }

    public void flipHorizontal(){flipHorizontal(polygone.getBounds().width);}

    public void flipHorizontal(int axe){
        ArrayList<Point> points = this.getListePoints();
        for (Point point : points){
            point.x = (axe - point.x);
        }
        this.setListePoints(points);
        trous.forEach(trou -> trou.flipHorizontal(axe));
    }

    public void flipVertical(){flipVertical(polygone.getBounds().height);}

    public void flipVertical(int axe){
        ArrayList<Point> points = this.getListePoints();
        for (Point point : points){
            point.y = (axe - point.y);
        }
        this.setListePoints(points);
        trous.forEach(trou -> trou.flipVertical(axe));
    }
}

