package MVC;

import Domaine.Plan;
import Domaine.Surface;

import java.awt.*;
import java.util.ArrayList;

public class Controller {
    private ArrayList<Observer> observers;

    public Plan plan;
    private Etat etat = Etat.LECTURE;

    public Controller(){
        observers = new ArrayList<>();
        plan = new Plan();
    }

    public void addObserver(Observer observer){
        observers.add(observer);
    }

    public void supprimerSurface(){
        plan.supprimerSurface();
        notifyObservers();
    }

    public void ajouterSurface(int value){
        switch(value){
            case 0:
                etat = Etat.AJOUTER_SURFACE;
                break;
            case 1:
                //forme = Forme.TRIANGLE;
                break;
            case 2:
                etat = Etat.CREER_FORME_LIBRE;
                plan.initialiserSufraceLibre();
                break;
        }
        notifyObservers();
    }

    public void clic(int x, int y){
        switch(etat){
            case AJOUTER_SURFACE:
                etat = plan.initialiserSurfaceCarre(new Point(x, y));
                break;
            case LECTURE:
                etat = plan.selectionner(new Point(x, y));
                break;
            case CREER_FORME_LIBRE:
                if(plan.surfaceLibreIsFirst(new Point(x, y))){
                    plan.terminerSurfaceLibre();
                    etat = Etat.LECTURE;
                }
                else{
                    plan.ajouterPointSurfaceLibre(new Point(x, y));
                }
                break;
            default:
                break;
        }
        notifyObservers();
    }

    public void glisser(int x, int y){
        switch(etat){
            case ETIRER_SURFACE:
                plan.etirerSurface(new Point(x, y));
                break;
            case DEPLACER_SURFACE:
                plan.deplacerSurface(new Point(x, y));
                break;
            default:
                break;
        }
        notifyObservers();
    }

    public void relacher(){
        switch(etat){
            case ETIRER_SURFACE:
                etat = plan.confirmerSurface();
                break;
            case DEPLACER_SURFACE:
                etat = plan.confirmerDeplacement();
                break;
            default:
                break;
        }
        notifyObservers();
    }

    private void notifyObservers(){
        for (Observer observer: observers) {
            observer.update();
        }
    }

    public String getStatusString(){
        String value = "";
        switch (etat){
            case LECTURE:
                value = "";
                break;
            case CREER_FORME_LIBRE:
                value = "Cliquez pour ajouter un point";
                break;
            case AJOUTER_SURFACE:
                value = "Cliquez pour débuter la surface";
                break;
            case ETIRER_SURFACE:
                value = "Relachez pour créer la forme";
                break;
            case DEPLACER_SURFACE:
                value = "Déplacez la forme avec la sourie";
                break;
        }
        return value;
    }

    public void paintCanevas(Graphics g){
        Surface surfaceSelectionnee = plan.surfaceSelectionnee;
        for(Surface surface : plan.recupererSurfaces()){
            Color couleur = surface.valide?Color.blue:Color.red;
            g.setColor(surface == surfaceSelectionnee?couleur.brighter():couleur.darker());
            g.fillPolygon(surface.polygone);
        }
        g.setColor(Color.yellow);
        if(surfaceSelectionnee != null && surfaceSelectionnee.valide) {
            g.drawPolygon(plan.surfaceSelectionnee.polygone);
        }
        g.setColor(Color.RED);
        ArrayList<Point> surfaceLibre = plan.getSurfaceLibre();
        if(etat == Etat.CREER_FORME_LIBRE && surfaceLibre.size()>1){
            g.drawOval(surfaceLibre.get(0).x-5, surfaceLibre.get(0).y-5, 10, 10);
            for (int i = 0; i < surfaceLibre.size()-1; i++) {
                Point p1 = surfaceLibre.get(i);
                Point p2 = surfaceLibre.get(i+1);
                g.drawLine(p1.x, p1.y, p2.x, p2.y);
                g.drawOval(p2.x-5, p2.y-5, 10, 10);
            }
        }
        else if (etat == Etat.CREER_FORME_LIBRE && surfaceLibre.size() == 1){
            g.drawOval(surfaceLibre.get(0).x-5, surfaceLibre.get(0).y-5, 10, 10);
        }
    }
}
