package Domaine;

import MVC.Etat;

import java.awt.*;
import java.util.ArrayList;

public class Plan {

    private ArrayList<Surface> listeSurfaces = new ArrayList<>();

    public Plan(){
    }

    public Etat ajouterSurface(Point position){

        int x  = position.x;
        int y = position.y;
        ArrayList<Point> points = new ArrayList<Point>();
        points.add(new Point(x,y));
        points.add(new Point(x + 75,y));
        points.add(new Point(x + 75,y + 40));
        points.add(new Point(x,y + 40));

        listeSurfaces.add(new Surface(points));
        return Etat.LECTURE;
    }

    public ArrayList<Surface> recupererSurfaces(){
        return listeSurfaces;
    }
}
