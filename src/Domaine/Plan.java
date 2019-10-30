package Domaine;

import MVC.Etat;

import java.awt.*;
import java.util.ArrayList;

public class Plan {

    private ArrayList<Surface> listeSurfaces = new ArrayList<>();
    private Surface surfaceSelectionnee;
    private Point premierPoint;

    public Plan(){
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
        surfaceSelectionnee = nouvelleSurface;
    }

    public Etat confirmerSurface(){
        return Etat.LECTURE;
    }

    public ArrayList<Surface> recupererSurfaces(){
        return listeSurfaces;
    }
}
