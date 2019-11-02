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

    public boolean valide;

    public Surface(List<Point> listePoints) {
        int[] coords_x = listePoints.stream().mapToInt(point -> point.x).toArray();
        int[] coords_y = listePoints.stream().mapToInt(point -> point.y).toArray();
        polygone = new Polygon(coords_x, coords_y, listePoints.size());
        valide = true;
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
                for(Point point : surface.getListePoints()){
                    if(polygone.contains(point)){return true;}
                }
                for(Point point : getListePoints()){
                    if(surface.polygone.contains(point)){return true;}
                }
            }
        }
        return false;
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
}
