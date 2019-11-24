package Domaine;

import javafx.util.Pair;

import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.PathIterator;
import java.lang.reflect.Array;
import java.nio.file.Path;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class Surface {

    public Polygon polygone;
    public boolean estUnTrou;
    public ArrayList<Surface> trous;
    private Revetement revetement;
    private ArrayList<Tuile> listeTuiles = new ArrayList<>();

    public Surface(List<Point> listePoints, boolean trou) {
        int[] coords_x = listePoints.stream().mapToInt(point -> point.x).toArray();
        int[] coords_y = listePoints.stream().mapToInt(point -> point.y).toArray();
        polygone = new Polygon(coords_x, coords_y, listePoints.size());
        this.estUnTrou = trou;
        // liste de tuiles
        revetement = new Revetement();
        setListeTuiles(genererListeDeTuiles());
    }

    //méthode permettant de déplacer une surface selon le vecteur de déplacement reçu
    public void deplacerSurface(int deplacement_x, int deplacement_y){
        int[] nouveaux_x = Arrays.stream(polygone.xpoints).map(x -> x + deplacement_x).toArray();
        int[] nouveaux_y = Arrays.stream(polygone.ypoints).map(x -> x + deplacement_y).toArray();
        polygone = new Polygon(nouveaux_x, nouveaux_y, polygone.npoints);
        setListeTuiles(genererListeDeTuiles());
    }


    public void modifierSommets(ArrayList<Point> coordonneesNouvellesCardinalites){

    }
    public void modifierSurface(){

    }
    public void modifierTypeSurface(){

    }

    public ArrayList<Point> getListePoints(){
        ArrayList<Point> listePoints = new ArrayList<Point>();
        for(int i = 0; i < polygone.npoints; i++){
            listePoints.add(new Point(polygone.xpoints[i], polygone.ypoints[i]));
        }
        return listePoints;
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
        return true;
    }

    // TODO regrouper les setteures/getteures ensemble ?
    public void setRevetement(Revetement revetement) {
        this.revetement = revetement;
    }

    public Revetement getRevetement() {
        return revetement;
    }

    public ArrayList<Tuile> genererListeDeTuiles(){
        // reçoit un motif en argument  test
        int tailleCoulis = revetement.getTailleDuCoulis();
        Color couleurCoulis = revetement.getCouleurCoulis();

        int coordXduBound = polygone.getBounds().x; int coordYduBond = polygone.getBounds().y;
        int boundsWidth = polygone.getBounds().width; int boundsHeight = polygone.getBounds().height;
        int tuileWidth = revetement.getLongueurTuile() ; int tuileHeight = revetement.getHauteurTuile() ;
        int nbTuilesX = (boundsWidth / (tuileWidth + tailleCoulis)); int nbTuilesY = (boundsHeight / (tuileHeight + tailleCoulis));
        ArrayList<Tuile> newListeTuiles = new ArrayList<>();

        int j = 0;
        while (j <= nbTuilesY){
            int i = 0;
            int positionEnX = coordXduBound;
            while (i <= nbTuilesX ) {
                newListeTuiles.add(new Tuile(genereSommetsTuile(positionEnX, coordYduBond, tuileWidth, tuileHeight)));
                positionEnX += tuileWidth + tailleCoulis;
                i++;
            }
            coordYduBond += tuileHeight + tailleCoulis;
            positionEnX = coordXduBound;
            j++;
        } ;
        return intersectionTuiles(newListeTuiles);
        // return  newListeTuiles;
    }

    private ArrayList<Tuile> intersectionTuiles(ArrayList<Tuile> ListeDetuiles){
        ArrayList<Tuile> newListeTuiles = new ArrayList<>();
        int xMax = getMaxValue(polygone.xpoints);
        int yMax = getMaxValue(polygone.ypoints);
        for (Tuile tuile : ListeDetuiles){
            PathIterator iterSTuile = tuile.getPolygone().getPathIterator(null);
            double[] coords = new double[6];
            Polygon newPoly = new Polygon();
            while(!iterSTuile.isDone()){
                int type = iterSTuile.currentSegment(coords);
                int x = (int) coords[0];
                int y = (int) coords[1];
                if ( x > xMax){x = xMax;}
                if (y > yMax){y = yMax;}
                newPoly.addPoint(x, y);
                iterSTuile.next();
            }
            newListeTuiles.add(new Tuile(newPoly));
        }

        return newListeTuiles;
    }



    public ArrayList<Tuile> getListeTuiles() {
        return listeTuiles;
    }

    public void setListeTuiles(ArrayList<Tuile> listeTuiles) {
        this.listeTuiles = listeTuiles;
    }

    private ArrayList<Point> genereSommetsTuile(int x, int y, int width, int height){
        ArrayList<Point> listeSommets = new ArrayList<Point>();
        listeSommets.add(new Point(x,y));
        listeSommets.add(new Point(x, y + height));
        listeSommets.add(new Point(x + width, y + height));
        listeSommets.add(new Point(x + width, y));
        return listeSommets;
    }

    private ArrayList<Tuile> newIntersectionTuiles(ArrayList<Tuile> ListeDetuiles){
        // sera utilisé pour le calcul des intersections à partir de ligne pour forme irreguliere
        ArrayList<Tuile> newListeTuiles = new ArrayList<>();
        int xMaxSurface = getMaxValue(polygone.xpoints);
        int yMaxSurface = getMaxValue(polygone.ypoints);
        for (Tuile tuile : ListeDetuiles){
            PathIterator iterSTuile = tuile.getPolygone().getPathIterator(null);
            double[] coordsTuile = new double[6];
            Polygon newPoly = new Polygon();
            while(!iterSTuile.isDone()){
                int typeSegmentTuile = iterSTuile.currentSegment(coordsTuile);
                int xTuile = (int) coordsTuile[0];
                int yTuile = (int) coordsTuile[1];

                PathIterator iterSurface = polygone.getPathIterator(null);
                double[] coordsSurface = new double[6];



                while (!iterSurface.isDone()){
                    int typeSegmentSurface = iterSurface.currentSegment(coordsSurface);
                    int xSurface = (int) coordsSurface[0];
                    int ySurface = (int) coordsSurface[1];


                    //else if ( xTuile > xMaxSurface){xTuile = xMaxSurface;}
                    // if (yTuile > yMaxSurface){yTuile = yMaxSurface;}
                }
                newPoly.addPoint(xTuile, yTuile);
                iterSTuile.next();
            }
            newListeTuiles.add(new Tuile(newPoly));
        }

        return newListeTuiles;
    }

    public static int getMaxValue(int[] listeNombre){
        int maxValue = listeNombre[0];
        for(int i=1;i < listeNombre.length;i++){
            if(listeNombre[i] > maxValue){
                maxValue = listeNombre[i];
            }
        }
        return maxValue;
    }
    public static int getMinValue(int[] listeNombre) {
        int minValue = listeNombre[0];
        for (int i = 1; i < listeNombre.length; i++) {
            if (listeNombre[i] < minValue) {
                minValue = listeNombre[i];
            }
        }
        return minValue;
    }

    // TODO deplacer dans package Outils
    public Optional<Point> CalculIntersection(double m1,double b1,double m2,double b2) {

        if (m1 == m2) {
            return Optional.empty();
        }

        double x = (b2 - b1) / (m1 - m2);
        double y = m1 * x + b1;

        Point point = new Point();
        point.setLocation(x, y);
        return Optional.of(point);
    }

    }
