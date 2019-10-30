package Domaine;

import javafx.util.Pair;

import java.awt.Point;
import java.util.ArrayList;
import java.awt.Polygon;

public class Surface {

    public Polygon polygone;

    public Surface(ArrayList<Point> listPoints) {
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
    public void getInfoSurface(){

    }
    public void editerAlligmentSurface(){

    }
    public void deplacerModifierMotif(){

    }
    public void editerMotif(){

    }
}
