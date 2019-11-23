package Domaine;

import javafx.util.Pair;

import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.PathIterator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class Surface {

    public Polygon polygone;
    private Revetement revetement;
    private ArrayList<Tuile> listeTuiles = new ArrayList<>();

    public boolean valide;

    public Surface(List<Point> listePoints) {
        int[] coords_x = listePoints.stream().mapToInt(point -> point.x).toArray();
        int[] coords_y = listePoints.stream().mapToInt(point -> point.y).toArray();
        polygone = new Polygon(coords_x, coords_y, listePoints.size());
        valide = true;
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
        return true;
    }

    public void rendreInvalide(){
        valide = false;
    }

    public void rendreValide(){
        valide = true;
    }


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
        return newListeTuiles;
    }

    public ArrayList<Tuile> getListeTuiles() {
        return listeTuiles;
    }

    public void setListeTuiles(ArrayList<Tuile> listeTuiles) {
        this.listeTuiles = listeTuiles;
    }

    private int [] genererListePoints(int pointOrigine, int longueur){
        int [] listePoints = new int[2];
        listePoints[0] = pointOrigine;
        listePoints[1] = pointOrigine + longueur;
        return listePoints;
    }

    private ArrayList<Point> genereSommetsTuile(int x, int y, int width, int height){
        ArrayList<Point> listeSommets = new ArrayList<Point>();
        listeSommets.add(new Point(x,y));
        listeSommets.add(new Point(x, y + height));
        listeSommets.add(new Point(x + width, y + height));
        listeSommets.add(new Point(x + width, y));
        return listeSommets;
    }

}
