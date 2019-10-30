package Domaine;

import java.awt.*;
import java.util.ArrayList;

public class Plan {

    private ArrayList<Surface> listeSurfaces = new ArrayList<>();

    public Plan(){
    }

    public void ajouterSurface(Point position){

        int x  = position.x;
        int y = position.y;
        ArrayList<Point> points = new ArrayList<Point>();
        points.add(new Point(x,y));
        points.add(new Point(x + 10,y));
        points.add(new Point(x + 10,y + 5));
        points.add(new Point(x,y + 5));

        listeSurfaces.add(new Surface(points));
    }

    public ArrayList<Surface> recupererSurfaces(){
        return listeSurfaces;
    }
}
