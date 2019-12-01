package Domaine;


import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.PathIterator;
import java.lang.reflect.Array;
import java.nio.file.Path;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import java.lang.Cloneable;

public class Surface implements Cloneable{

    public Polygon polygone;
    public boolean estUnTrou;
    public ArrayList<Surface> trous;
    private Revetement revetement;
    private ArrayList<Tuile> listeTuiles = new ArrayList<>();
    private int tailleDuCoulis = 4;
    private Color couleurCoulis = Color.WHITE;
    ;

    public Surface(List<Point> listePoints, boolean trou) {
        int[] coords_x = listePoints.stream().mapToInt(point -> point.x).toArray();
        int[] coords_y = listePoints.stream().mapToInt(point -> point.y).toArray();
        polygone = new Polygon(coords_x, coords_y, listePoints.size());
        this.estUnTrou = trou;
        this.trous = new ArrayList<>();
        // liste de tuiles
        this.revetement = new Revetement();
        setListeTuiles(genererListeDeTuiles());
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
        setListeTuiles(genererListeDeTuiles());
    }

    //méthode permettant de déplacer une surface selon le vecteur de déplacement reçu
    public void deplacerSurface(int deplacement_x, int deplacement_y){
        int[] nouveaux_x = Arrays.stream(polygone.xpoints).map(x -> x + deplacement_x).toArray();
        int[] nouveaux_y = Arrays.stream(polygone.ypoints).map(x -> x + deplacement_y).toArray();
        polygone = new Polygon(nouveaux_x, nouveaux_y, polygone.npoints);
        setListeTuiles(genererListeDeTuiles());
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
            points = points.stream().map(point ->{
                int nouveau_x = (largeur * Math.abs(point.x - limites.x) / limites.width) + limites.x;
                int nouveau_y = (hauteur * Math.abs(point.y - limites.y) / limites.height) + limites.y;
                return new Point(nouveau_x, nouveau_y);
            }).collect(Collectors.toCollection(ArrayList::new));
            changerPoints(points);
            for(Surface trou : trous){
                points = trou.getListePoints().stream().map(point ->{
                    int nouveau_x = (largeur * Math.abs(point.x - limites.x) / limites.width) + limites.x;
                    int nouveau_y = (hauteur * Math.abs(point.y - limites.y) / limites.height) + limites.y;
                    return new Point(nouveau_x, nouveau_y);
                }).collect(Collectors.toCollection(ArrayList::new));
                trou.changerPoints(points);
            }
        }
    }

    public boolean fusionner(Surface s){
        Area aire = new Area(polygone);
        aire.add(new Area(s.polygone));
        if(!aire.isSingular()){return false;}
        PathIterator iterator = aire.getPathIterator(null);
        double[] coords = new double[6];
        Polygon nouveau_polygone = new Polygon();
        while (!iterator.isDone()) {
            int type = iterator.currentSegment(coords);
            int x = (int) coords[0];
            int y = (int) coords[1];
            if(type != PathIterator.SEG_CLOSE) {
                nouveau_polygone.addPoint(x, y);
            }
            iterator.next();
        }
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
        // String motif = "Installation en décallé"; // TODO enlever
        int tailleCoulis = this.getTailleDuCoulis();
        Color couleurCoulis = getCouleurCoulis();

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

                newListeTuiles.add(new Tuile(genererSommetsTuile(positionEnX, coordYduBond, tuileWidth, tuileHeight)));
                positionEnX += tuileWidth + tailleCoulis;
                i++;
            }
            coordYduBond += tuileHeight + tailleCoulis;
            positionEnX = coordXduBound;
            j++;
        } ;
        return newIntersectionTuiles(newListeTuiles);
        // return  newListeTuiles;
    }

    private ArrayList<Point> genererSommetsTuile(int x, int y, int width, int height){
        ArrayList<Point> listeSommets = new ArrayList<Point>();
        listeSommets.add(new Point(x,y));
        listeSommets.add(new Point(x, y + height));
        listeSommets.add(new Point(x + width, y + height));
        listeSommets.add(new Point(x + width, y));
        return listeSommets;
    }

    private ArrayList<Tuile> newIntersectionTuiles(ArrayList<Tuile> ListeDetuiles){ // TODO refactor le nom
        // sera utilisé pour le calcul des intersections à partir de ligne pour forme irreguliere
        ArrayList<Tuile> newListeTuiles = new ArrayList<>();
        Area areaSurface = new Area(polygone);
        for (Tuile tuile : ListeDetuiles){
            Area areaTuile = new Area(tuile.getPolygone());
            areaTuile.intersect(areaSurface);
            PathIterator iterTuile = areaTuile.getPathIterator(null); //TODO isoler dans une méthode tout ça
            Polygon newPolyTuile = new Polygon();
            double[] coordsTuile = new double[6];
            while (!iterTuile.isDone()){
                int type = iterTuile.currentSegment(coordsTuile);
                int x = (int) coordsTuile[0];
                int y = (int) coordsTuile[1];
                if(type != PathIterator.SEG_CLOSE) {
                    newPolyTuile.addPoint(x, y);
                }
                iterTuile.next();
            }
            Tuile newTuile = new Tuile(newPolyTuile);
            newListeTuiles.add(newTuile);
        }

        return newListeTuiles;
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
        setListeTuiles(genererListeDeTuiles());
    }

    public Color getCouleurCoulis() {
        return couleurCoulis;
    }

    public boolean EstUnTrou() {
        return estUnTrou;
    }

    public void setCouleurCoulis(Color couleurCoulis) {
        this.couleurCoulis = couleurCoulis;
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

