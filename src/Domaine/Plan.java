package Domaine;

import MVC.Etat;

import java.awt.*;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Plan {

    private ArrayList<Surface> listeSurfaces = new ArrayList<>();
    public Surface surfaceOriginale;
    public Surface surfaceSelectionnee;
    private Point premierPoint;
    private Point pointPrecedent;
    private ArrayList<Point> surfaceLibre;
    private boolean isGrilleMagnetiqueActive = false;
    private int grid_size = 50;


    public Plan(){
    }

    public Etat selectionner(Point position){
        if(surfaceSelectionnee != null && surfaceSelectionnee.polygone.contains(position)){
            pointPrecedent = premierPoint = position;
            return Etat.DEPLACER_SURFACE;
        }
        for(Surface surface : listeSurfaces){
            if(surface.polygone.contains(position)){
                surfaceOriginale = surfaceSelectionnee = surface;
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

    public Etat initialiserSurface(Point position, ArrayList<Point> patron){
        premierPoint = convertMouseCoordWithMagnetique(position);

        patron = patron.stream().map(point -> new Point(point.x + position.x, point.y + position.y))
                .collect(Collectors.toCollection(ArrayList::new));
        surfaceOriginale = surfaceSelectionnee = new Surface(patron);
        listeSurfaces.add(surfaceSelectionnee);
        return Etat.ETIRER_SURFACE;
    }

    public void initialiserSurfaceLibre(){
        surfaceLibre = new ArrayList<>();
    }

    private void terminerSurfaceLibre(){
        surfaceSelectionnee = new Surface(surfaceLibre);
        listeSurfaces.add(surfaceSelectionnee);
    }

    public Etat ajouterPointSurfaceLibre(Point point){
        point = convertMouseCoordWithMagnetique(point);
        if(surfaceLibreIsFirst(point)) {
            terminerSurfaceLibre();
            return Etat.LECTURE;
        }
        else{
            surfaceLibre.add(point);
            return Etat.CREER_FORME_LIBRE;
        }
    }

    private boolean surfaceLibreIsFirst(Point point){
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
        if(isGrilleMagnetiqueActive){
            int x = surfaceSelectionnee.polygone.getBounds().x;
            int y = surfaceSelectionnee.polygone.getBounds().y;
            Point top = new Point(x, y);
            Point newTop = convertMouseCoordWithMagnetique(top);
            deplacement_x = newTop.x - top.x;
            deplacement_y = newTop.y - top.y;
            surfaceSelectionnee.deplacerSurface(deplacement_x, deplacement_y);
            position.x = newTop.x - deplacement_x;
            position.y = newTop.y - deplacement_y;
            pointPrecedent = position;
        }
        if(surfaceSelectionnee.intersecte(listeSurfaces)){
            surfaceSelectionnee.rendreInvalide();
        }else{
            surfaceSelectionnee.rendreValide();
        }
    }

    public void etirerSurface(Point position){
        position = convertMouseCoordWithMagnetique(position);
        int x  = position.x - premierPoint.x;
        int y = position.y - premierPoint.y;
        ArrayList<Point> points = surfaceOriginale.getListePoints();
        Rectangle limites = surfaceOriginale.polygone.getBounds();
        if(x != 0 && y!= 0){
            points = points.stream().map(point ->{
                int nouveau_x = (x * (point.x - limites.x) / limites.width) + limites.x;
                int nouveau_y = (y * (point.y - limites.y) / limites.height) + limites.y;
                return new Point(nouveau_x, nouveau_y);
            }).collect(Collectors.toCollection(ArrayList::new));
        }

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

    public void setGridSize(int size){
        grid_size = size;
    }

    public int getGridSize(){
        return grid_size;
    }

    public void setGrilleMagnetiqueActive(boolean active){
        this.isGrilleMagnetiqueActive = active;
    }

    public Point convertMouseCoordWithMagnetique(Point old){
        Point point = new Point(old.x, old.y);
        if (isGrilleMagnetiqueActive){
            int offX = point.x % grid_size;
            int offY = point.y % grid_size;
            point.x = offX < grid_size / 2 ? point.x - offX : point.x - offX + grid_size;
            point.y = offY < grid_size / 2 ? point.y - offY : point.y - offY + grid_size;
        }
        return point;
    }
}
