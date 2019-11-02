package Domaine;

import MVC.Etat;

import java.awt.*;
import java.util.ArrayList;

public class Plan {

    private ArrayList<Surface> listeSurfaces = new ArrayList<>();
    public Surface surfaceSelectionnee;
    private Point premierPoint;

    public Plan(){
    }

    public Etat selectionner(Point position){
        for(Surface surface : listeSurfaces){
            if(surface.polygone.contains(position)){
                surfaceSelectionnee = surface;
                return Etat.LECTURE;
            }
        }
        surfaceSelectionnee = null;
        return Etat.LECTURE;
    }

    public Etat initialiserSurface(Point position){
        premierPoint = position;

        surfaceSelectionnee = new Surface(new ArrayList<>());
        listeSurfaces.add(surfaceSelectionnee);
        return Etat.ETIRER_SURFACE;
    }

    public void etirerSurface(Point position){
        int x  = position.x;
        int y = position.y;
        ArrayList<Point> points = new ArrayList<>();
        points.add(premierPoint);
        points.add(new Point(x ,premierPoint.y));
        points.add(new Point(x ,y));
        points.add(new Point(premierPoint.x,y));
        Surface nouvelleSurface = new Surface(points);
        listeSurfaces.remove(surfaceSelectionnee);
        listeSurfaces.add(nouvelleSurface);
        if(nouvelleSurface.intersecte(listeSurfaces)){
            nouvelleSurface.rendreInvalide();
        }
        surfaceSelectionnee = nouvelleSurface;
    }

    public Etat confirmerSurface(){
        if(!surfaceSelectionnee.valide){
            listeSurfaces.remove(surfaceSelectionnee);
        }
        return Etat.LECTURE;
    }

    public ArrayList<Surface> recupererSurfaces(){
        return listeSurfaces;
    }
}
