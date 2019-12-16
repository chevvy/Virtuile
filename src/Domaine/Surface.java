package Domaine;


import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.io.Serializable;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import java.lang.Cloneable;

import Domaine.Outils.*;

public class Surface implements Cloneable, Serializable {

    public Polygon polygone;
    private Polygon surfaceTuilee;
    public boolean estUnTrou;
    public ArrayList<Surface> trous;
    private Revetement revetement;
    private ArrayList<Tuile> listeTuiles = new ArrayList<>();
    private int tailleDuCoulis = 4;
    private Color couleurCoulis = Color.WHITE;
    private int offset = 50;
    private int tuileCentre = 1;
    private Point pointMilieu;

    public Surface(List<Point> listePoints, boolean trou) {
        int[] coords_x = listePoints.stream().mapToInt(point -> point.x).toArray();
        int[] coords_y = listePoints.stream().mapToInt(point -> point.y).toArray();
        polygone = new Polygon(coords_x, coords_y, listePoints.size());
        surfaceTuilee = new Polygon(coords_x, coords_y, listePoints.size());
        this.estUnTrou = trou;
        this.trous = new ArrayList<>();
        this.revetement = new Revetement();
        this.pointMilieu = new Point((int) polygone.getBounds().getMinX(),(int) polygone.getBounds().getMinY());
        majListeTuiles();
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

    public void changerPointsSurface(List<Point> listePoints){
        int[] coords_x = listePoints.stream().mapToInt(point -> point.x).toArray();
        int[] coords_y = listePoints.stream().mapToInt(point -> point.y).toArray();
        polygone = new Polygon(coords_x, coords_y, listePoints.size());
        this.pointMilieu = new Point((int) polygone.getBounds().getMinX(),(int) polygone.getBounds().getMinY());
        majListeTuiles();
    }

    public void changerPointsSurfaceTuile(List<Point> listePoints){
        int[] coords_x = listePoints.stream().mapToInt(point -> point.x).toArray();
        int[] coords_y = listePoints.stream().mapToInt(point -> point.y).toArray();
        surfaceTuilee = new Polygon(coords_x, coords_y, listePoints.size());
        majListeTuiles();
    }

    //méthode permettant de déplacer une surface selon le vecteur de déplacement reçu
    public void deplacerSurface(int deplacement_x, int deplacement_y){
        int[] nouveaux_x = Arrays.stream(polygone.xpoints).map(x -> x + deplacement_x).toArray();
        int[] nouveaux_y = Arrays.stream(polygone.ypoints).map(x -> x + deplacement_y).toArray();
        polygone = new Polygon(nouveaux_x, nouveaux_y, polygone.npoints);
        trous.forEach(trou -> trou.deplacerSurface(deplacement_x, deplacement_y));
        this.pointMilieu = new Point((int) polygone.getBounds().getMinX(),(int) polygone.getBounds().getMinY());
        majListeTuiles();
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
        majListeTuiles();
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
            changerPointsSurface(points);
            for(Surface trou : trous){
                Rectangle limitesTuile = trou.polygone.getBounds();
                points = generePoints(hauteur,largeur, trou.getListePoints(), limitesTuile, limitesTuile.x, limitesTuile.y);
                trou.changerPointsSurface(points);
            }
        }
        majListeTuiles();
    }

    static ArrayList<Point> generePoints(int hauteur, int largeur, ArrayList<Point> points, Rectangle limites, int x, int y) {
        if(hauteur != 0 && largeur != 0){
            points = points.stream().map(point ->{ // TODO déplacer méthode général dans outils
                int nouveau_x = (largeur * Math.abs(point.x - limites.x) / limites.width) + x;
                int nouveau_y = (hauteur * Math.abs(point.y - limites.y) / limites.height) + y;
                return new Point(nouveau_x, nouveau_y);
            }).collect(Collectors.toCollection(ArrayList::new));
        }
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


    public ArrayList<Tuile> genererListeDeTuiles() {
        // "Installation droite", "Installation imitation parquet", "Installation en décallé", "Installation en chevron",
        // "Installation en L"
        // En ce moment, si le motif est autre que Installation droite ou installation décallé, il fait "installation"
        // CAD : installation equivalent en x et y
        String motif = this.revetement.getMotifTuiles();
        int tailleCoulis = this.getTailleDuCoulis();
        // on fait start le motif à 1 fois la taille du polygone, en dehors de celui-ci
        int coordXduBound = polygone.getBounds().x - 2*polygone.getBounds().width;
        int coordYduBond = polygone.getBounds().y - 2*polygone.getBounds().height;
        int boundsWidth = 3*polygone.getBounds().width;
        int boundsHeight = 3*polygone.getBounds().height;
        int tuileWidth = revetement.getLongueurTuile();
        int tuileHeight = revetement.getHauteurTuile();
        int nbTuilesX = (boundsWidth / (tuileWidth + tailleCoulis))*2;
        int nbTuilesY = (boundsHeight / (tuileHeight + tailleCoulis)) *2;
        ArrayList<Tuile> newListeTuiles = new ArrayList<>();

        if (estUnTrou) {
            return newListeTuiles;
        }
        int j = 0;
        //TODO ajouter angle dans Installation en L ou on fait comme si c'était une installation droite et on va modifier l'angle?
        if (motif.equals("Installation en décallé") || motif.equals("Installation droite") || motif.equals("Installation en L")) {
            while (j <= nbTuilesY + 1) {
                int i = 0;
                int positionEnX = coordXduBound;
                // si motifs décallé et que la rangée actuelle est pair
                if (motif.equals("Installation en décallé") && (j % 2 == 0)) {
                    int decallage = tuileWidth / (100 / offset);
                    positionEnX = positionEnX - decallage;
                }
                while (i <= nbTuilesX) {
                    newListeTuiles.add(new Tuile(genereSommetsPolygon(positionEnX, coordYduBond, tuileWidth, tuileHeight)));
                    positionEnX += tuileWidth + tailleCoulis;
                    i++;
                }
                if (positionEnX < coordXduBound + boundsWidth){
                    newListeTuiles.add(new Tuile(genereSommetsPolygon(positionEnX, coordYduBond, tuileWidth, tuileHeight)));
                }
                coordYduBond += tuileHeight + tailleCoulis;
                j++;
            }
        }
        if (motif.equals("Installation imitation parquet")){
            genererImitationParquet(nbTuilesY, coordXduBound, nbTuilesX, tailleCoulis, tuileWidth, tuileHeight, coordYduBond, newListeTuiles);
        }
        if (motif.equals("Installation en chevron")){
            genererChevron(nbTuilesY, coordXduBound, nbTuilesX, tailleCoulis, tuileWidth, tuileHeight, coordYduBond, newListeTuiles);
        }

        return IntersectionTuiles(newListeTuiles);
    }

    private void genererImitationParquet(int nbTuilesY, int coordXduBound, int nbTuilesX, int tailleCoulis, int tuileWidth,
                                         int tuileHeight, int coordYduBond, ArrayList<Tuile> newListeTuiles ) {
        int j = 0;
        while (j <= nbTuilesY / 2) {
            int positionEnX = coordXduBound;
            int i = (j % 2 == 0) ? 0 : -1;
            while (i <= nbTuilesX) {
                if (i % 2 == 0) {
                    tuileWidth = revetement.getHauteurTuile();
                    tuileHeight = revetement.getLongueurTuile();
                    newListeTuiles.add(new Tuile(genereSommetsPolygon(positionEnX, coordYduBond, tuileWidth, tuileHeight)));
                    positionEnX = positionEnX + tuileWidth + tailleCoulis;
                } else {
                    tuileWidth = revetement.getLongueurTuile();
                    tuileHeight = revetement.getHauteurTuile();
                    newListeTuiles.add(new Tuile(genereSommetsPolygon(positionEnX, coordYduBond, tuileWidth, tuileHeight)));
                    coordYduBond = coordYduBond + tuileHeight + tailleCoulis;
                }
                newListeTuiles.add(new Tuile(genereSommetsPolygon(positionEnX, coordYduBond, tuileWidth, tuileHeight)));
                coordYduBond = (i % 2 == 0) ? coordYduBond : coordYduBond - tuileHeight - tailleCoulis;
                positionEnX += tuileWidth + tailleCoulis;
                i++;
            }
            coordYduBond += revetement.getLongueurTuile() + tailleCoulis;
            j++;
        }
    }

    private void genererChevron(int nbTuilesY, int coordXduBound, int nbTuilesX, int tailleCoulis, int tuileWidth,
                                int tuileHeight, int coordYduBond, ArrayList<Tuile> newListeTuiles ){
        int j = 0;
        while (j <= nbTuilesY) {
            int positionEnX = coordXduBound;
            int i = 0;
            if(j%4==1) {
                positionEnX -=revetement.getHauteurTuile() + tailleCoulis ;
            }
            if(j%4==2) {
                i=1;
            }
            else if(j%4==3) {
                i = 1;
                positionEnX -=  revetement.getHauteurTuile() + tailleCoulis ;
            }
            while (i <= nbTuilesX +2) {
                if (j == 0 && i%2 == 1){
                    tuileWidth = revetement.getHauteurTuile();
                    tuileHeight = revetement.getHauteurTuile();
                    newListeTuiles.add(new Tuile(genereSommetsPolygon(positionEnX + tuileWidth + tailleCoulis, coordYduBond, tuileWidth, tuileHeight)));
                }
                if(i%2 ==0){
                    tuileWidth = revetement.getLongueurTuile();
                    tuileHeight = revetement.getHauteurTuile();
                    newListeTuiles.add(new Tuile(genereSommetsPolygon(positionEnX, coordYduBond, tuileWidth, tuileHeight)));
                }
                else{
                    tuileWidth = revetement.getHauteurTuile();
                    tuileHeight = revetement.getLongueurTuile();
                    newListeTuiles.add(new Tuile(genereSommetsPolygon(positionEnX, coordYduBond, tuileWidth, tuileHeight)));
                }
                positionEnX += revetement.getLongueurTuile() + tailleCoulis;
                i++;
            }
            coordYduBond += revetement.getHauteurTuile() + tailleCoulis;
            j++;
        }
    }



    private ArrayList<Tuile> IntersectionTuiles(ArrayList<Tuile> ListeDetuiles){ // TODO ne pas toucher plz
        // pour taille du coulis, on va générer un nouveau polygone plus petit qui va contenir seulement les tuiles
        // On peut utiliser la méthode "setDimension" pour changer la taille de la surface
        // On va extraire une méthode plus générale de set dimension pour ne pas changer la taille du polygone
        // On va alors calculer l'intersections avec le polygone SurfaceTuile plutôt que le polygone lui même
        // pour les trous, on pourrait ajouter la taille du coulis à sa taille pour l'intersection, mais pas au trou lui-même
        ArrayList<Tuile> newListeTuiles = new ArrayList<>();
        Area areaSurface = new Area(polygone);
        // pour la liste de trous, substrac a l'area du polygone
        for(Surface trou : trous){
            Area areaTrou = new Area(trou.polygone);
            areaSurface.subtract(areaTrou);
        }
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

    public void majListeTuiles(){
        // ensemble des méthodes utilisées pour la génération et l'ajout de tuiles
        // utilie genere pointsSurface tuile pour creer nouvelle surface plus petite
        // changeLespoints de la surfaceTuile
        // sera utilisé aussi pour changer la taille de la surface tuillée
        setListeTuiles(genererListeDeTuiles());
    }

    private void genererSurfaceTuilee(){ // TODO ne pas toucher plz
        int largeur = surfaceTuilee.getBounds().width - (2*tailleDuCoulis);
        int hauteur = surfaceTuilee.getBounds().height - (2*tailleDuCoulis);
        Rectangle limites = surfaceTuilee.getBounds();
        ArrayList<Point> points = getListePoints();
        points = generePoints(hauteur, largeur, points, limites, limites.x, limites.y);
        changerPointsSurfaceTuile(points);
    }


    public Tuile getTuileAtPoint(Point point){
        for (Tuile tuile : listeTuiles){
            if(tuile.getPolygone().contains(point)){
                return tuile;
            }
        }
        return new Tuile(new Polygon());
    }

    // TODO ramener dans Outils
    public Point getPointMilieu(int hauteur, int largeur, int positionX, int positionY){
        int coordMilieuX = positionX + (largeur/2);
        int coordMilieuY = positionY + (hauteur/2);
        return new Point(coordMilieuX, coordMilieuY);
    }

    // TODO ramener dans Outils
    public ArrayList<Point> rotationListeDePoints(ArrayList<Point> points, Point pointMilieu, double angle){
        Point2D[] pointsOriginal = new Point2D[points.size()]; //transform a besoin de Point2D[]
        for (int i = 0; i < points.size(); i++){
            pointsOriginal[i] = points.get(i);
        }

        Point2D[] pointRotate = new Point2D[points.size()];//transform a besoin de Point2D[]

        // rotation de la liste des points de la tuile en fonction de leur point milieu
        AffineTransform.getRotateInstance
                (Math.toRadians(angle), pointMilieu.x, pointMilieu.y)
                .transform(pointsOriginal,0,pointRotate,0,points.size());

        // On retourne ça en Arraylist pour être compatible avec le reste du programme
        ArrayList<Point> nouveauxPoints = new ArrayList<>();
        for(Point2D point : pointRotate){
            nouveauxPoints.add(new Point((int)point.getX(), (int)point.getY()));
        }
        return nouveauxPoints;
    }



    // TODO ramener dans Outils
    public ArrayList<Point> genereSommetsPolygon(int x, int y, int width, int height){
        ArrayList<Point> listeSommets = new ArrayList<Point>();
        listeSommets.add(new Point(x,y));
        listeSommets.add(new Point(x, y + height));
        listeSommets.add(new Point(x + width, y + height));
        listeSommets.add(new Point(x + width, y));
        listeSommets = rotationListeDePoints(listeSommets, pointMilieu, this.revetement.getAngleMotif());
        return listeSommets;
    }

    public void setRevetement(Revetement revetement) {
        this.revetement = revetement;
        majListeTuiles();
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
        majListeTuiles();
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
    public void setOffset(int offset){
        this.offset = offset;
        majListeTuiles();
    }
    public int getOffset(){
        return this.offset;
    }
}

