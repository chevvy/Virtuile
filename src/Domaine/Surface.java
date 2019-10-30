package Domaine;

import javafx.util.Pair;

import java.awt.Point;
import java.util.ArrayList;
import java.awt.Polygon;
import java.util.List;
import java.util.stream.Collectors;

public class Surface {

    public Polygon polygone;

    public Surface(List<Point> listePoints) {
        int[] coords_x = listePoints.stream().mapToInt(point -> point.x).toArray();
        int[] coords_y = listePoints.stream().mapToInt(point -> point.y).toArray();
        polygone = new Polygon(coords_x, coords_y, listePoints.size());
    }
    //méthode contains de Polygon
    //return True si le point est dans la surface
    public boolean estDansSurface(Point point){
        return false;
    }
    //méthode permettant de déplacer une surface selon le vecteur de déplacement reçu
    public void deplacerSurface(Pair<Double, Double> deplacement){

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
            listePoints.add(new Point(polygone.xpoints[i], polygone.xpoints[i]));
        }
        return listePoints;
    }
    public void getInfoSurface(){

    }
    public void editerAlligmentSurface(){

    }
    public void deplacerModifierMotif(){

    }
    public void editerMotif(){

    }
}
