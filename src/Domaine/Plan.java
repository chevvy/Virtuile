package Domaine;

import MVC.Etat;

import java.awt.*;
import java.util.ArrayList;

public class Plan {

    private ArrayList<Surface> listeSurfaces = new ArrayList<>();
    public Surface surfaceSelectionnee;
    private Point premierPoint;
    private Point pointPrecedent;
    private ArrayList<Point> surfaceLibre;


    public Plan(){
    }

    public Etat selectionner(Point position){
        if(surfaceSelectionnee != null && surfaceSelectionnee.polygone.contains(position)){
            pointPrecedent = premierPoint = position;
            return Etat.DEPLACER_SURFACE;
        }
        for(Surface surface : listeSurfaces){
            if(surface.polygone.contains(position)){
                surfaceSelectionnee = surface;
                return Etat.LECTURE;
            }
        }
        surfaceSelectionnee = null;
        return Etat.LECTURE;
    }

    public void supprimerSurface(){
        listeSurfaces.remove(surfaceSelectionnee);
        surfaceSelectionnee = null;
    }

    public Etat initialiserSurfaceCarre(Point position){
        premierPoint = position;

        surfaceSelectionnee = new Surface(new ArrayList<>());
        listeSurfaces.add(surfaceSelectionnee);
        return Etat.ETIRER_SURFACE;
    }

    public void initialiserSufraceLibre(){
        surfaceLibre = new ArrayList<>();
    }

    public void terminerSurfaceLibre(){
        surfaceSelectionnee = new Surface(surfaceLibre);
        listeSurfaces.add(surfaceSelectionnee);
    }

    public void ajouterPointSurfaceLibre(Point point){
        surfaceLibre.add(point);
    }

    public boolean surfaceLibreIsFirst(Point point){
        if (surfaceLibre.size() < 3) { return false; }
        Point first = surfaceLibre.get(0);
        if (point.x < first.x-5 || point.x > first.x+5) {return false;}
        if (point.y < first.y-5 || point.y > first.y+5) {return false;}
        return true;
    }

    public ArrayList<Point> getSurfaceLibre(){
        return  surfaceLibre;
    }

    public void deplacerSurface(Point position){
        int deplacement_x = position.x - pointPrecedent.x;
        int deplacement_y = position.y - pointPrecedent.y;
        surfaceSelectionnee.deplacerSurface(deplacement_x, deplacement_y);
        pointPrecedent = position;
        if(surfaceSelectionnee.intersecte(listeSurfaces)){
            surfaceSelectionnee.rendreInvalide();
        }else{
            surfaceSelectionnee.rendreValide();
        }
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

    public Etat confirmerDeplacement(){
        if(!surfaceSelectionnee.valide){
            deplacerSurface(premierPoint);
        }
        return Etat.LECTURE;
    }

    public ArrayList<Surface> recupererSurfaces(){
        return listeSurfaces;
    }
}
