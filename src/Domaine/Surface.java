package Domaine;

import javafx.util.Pair;

import java.awt.Point;
import java.util.ArrayList;
import java.awt.Polygon;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Surface {

    public Polygon polygone;
    private Revetement revetement;

    public boolean valide;

    public Surface(List<Point> listePoints) {
        int[] coords_x = listePoints.stream().mapToInt(point -> point.x).toArray();
        int[] coords_y = listePoints.stream().mapToInt(point -> point.y).toArray();
        polygone = new Polygon(coords_x, coords_y, listePoints.size());
        valide = true;
        // liste de tuiles
        revetement = new Revetement();
    }
    //méthode contains de Polygon
    //return True si le point est dans la surface
    public boolean estDansSurface(Point point){
        return false;
    }
    //méthode permettant de déplacer une surface selon le vecteur de déplacement reçu
    public void deplacerSurface(int deplacement_x, int deplacement_y){
        int[] nouveaux_x = Arrays.stream(polygone.xpoints).map(x -> x + deplacement_x).toArray();
        int[] nouveaux_y = Arrays.stream(polygone.ypoints).map(x -> x + deplacement_y).toArray();
        polygone = new Polygon(nouveaux_x, nouveaux_y, polygone.npoints);
    }
    //méthode permettant de modifier un point d'une surface
    //on lui fournit une liste de tous les nouveaux points
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

    public boolean intersecte(ArrayList<Surface> surfaces){

        for(Surface surface : surfaces){
            if(!(surface == this)){
                ArrayList<Point> points = surface.getListePoints(); //commentaire
                Point pointPrecedent = points.get(points.size() - 1);
                for(Point point : points){
                    ArrayList<Point> pointsSelect = this.getListePoints();
                    Point pointPrecedentSelect = pointsSelect.get(pointsSelect.size() - 1); //commentaire
                    for(Point pointSelect : pointsSelect) {
                        if(segmentsIntersectent(pointPrecedentSelect, pointSelect, pointPrecedent, point)){
                            return true;
                        }
                        pointPrecedentSelect = pointSelect;
                    }
                    pointPrecedent = point;
                }
                if(surface.polygone.contains(this.getListePoints().get(0)) ||
                        this.polygone.contains(surface.getListePoints().get(0))){
                    return true; //Cas où une surface est entièrement dans une autre
                }
            }
        }
        return false;
    }

    private boolean segmentsIntersectent(Point s1p1, Point s1p2, Point s2p1, Point s2p2){
        double t = (double)((s1p1.x - s2p1.x)*(s2p1.y - s2p2.y) - (s1p1.y - s2p1.y)*(s2p1.x - s2p2.x))/
                ((s1p1.x - s1p2.x)*(s2p1.y - s2p2.y) - (s1p1.y - s1p2.y)*(s2p1.x - s2p2.x));
        if(t<0||t>1.0||Double.isNaN(t)){ //le code fait des choses
            return false;
        }
        double u = (double)-((s1p1.x - s1p2.x)*(s1p1.y - s2p1.y) - (s1p1.y - s1p2.y)*(s1p1.x - s2p1.x))/
                ((s1p1.x - s1p2.x)*(s2p1.y - s2p2.y) - (s1p1.y - s1p2.y)*(s2p1.x - s2p2.x));
        if(u<0||u>1.0||Double.isNaN(u)){
            return false; //aussi ici
        }else{
            return true;
        }
    }

    public void rendreInvalide(){
        valide = false;
    }

    public void rendreValide(){
        valide = true;
    }

    public void getInfoSurface(){

    }
    public void editerAlligmentSurface(){

    }
    public void deplacerModifierMotif(){

    }
    public void editerMotif(){

    }

    // TODO regrouper les setters/getters ensemble ?
    public void setRevetement(Revetement revetement) {
        this.revetement = revetement;
    }

    public Revetement getRevetement() {
        return revetement;
    }
}
