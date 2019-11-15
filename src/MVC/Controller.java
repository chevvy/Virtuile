package MVC;

import Domaine.Plan;
import Domaine.Surface;

import java.awt.*;
import java.util.ArrayList;

public class Controller {
    private ArrayList<Observer> observers;

    public Plan plan;
    public ArrayList<Point> patronForme;
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
            case 0: //Rectangle
                patronForme = new ArrayList<Point>() {
                    {
                        add(new Point(0, 0));
                        add(new Point(0, 1));
                        add(new Point(1, 1));
                        add(new Point(1, 0));
                    }
                };
                etat = Etat.AJOUTER_SURFACE;
                break;
            case 1: //Triangle
                patronForme = new ArrayList<Point>() {
                    {
                        add(new Point(0, 0));
                        add(new Point(2, 0));
                        add(new Point(1, 1));
                    }
                };
                etat = Etat.AJOUTER_SURFACE;
                break;
            case 2:
                etat = Etat.CREER_FORME_LIBRE;
                plan.initialiserSurfaceLibre();
                break;
        }
        notifyObservers();
    }

    public void selectionnerAligner(){
        etat = Etat.SELECTIONNER_ALIGNER;
    }

    public void aligner(String alignement){
        plan.aligner(alignement);
    }

    public void annulerAligner(){
        plan.annulerAligner();
    }

    public void clic(int x, int y){
        switch(etat){
            case AJOUTER_SURFACE:
                etat = plan.initialiserSurface(new Point(x, y), patronForme);
                break;
            case LECTURE:
                etat = plan.selectionner(new Point(x, y));
                break;
            case CREER_FORME_LIBRE:
                etat = plan.ajouterPointSurfaceLibre(new Point(x, y));
                break;
            case SELECTIONNER_ALIGNER:
                etat = plan.selectionnerAligner(new Point(x, y));
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

    public void paintCanevas(Graphics g, Point mouse){
        Surface surfaceSelectionnee = plan.surfaceSelectionnee;
        for(Surface surface : plan.recupererSurfaces()){
            g.setColor(surface.valide?Color.blue:Color.red);
            g.fillPolygon(surface.polygone);
        }

        if(surfaceSelectionnee != null){
            g.setColor(surfaceSelectionnee.valide?Color.blue.brighter():Color.red);
            g.fillPolygon(surfaceSelectionnee.polygone);

            g.setColor(Color.yellow);
            if(surfaceSelectionnee.valide){
                g.setColor(Color.yellow);
                g.drawPolygon(plan.surfaceSelectionnee.polygone);
            }
        }
        g.setColor(Color.RED);
        if(etat == Etat.AJOUTER_SURFACE || etat == Etat.CREER_FORME_LIBRE){
            g.drawOval(mouse.x-5, mouse.y-5, 10, 10);
        }
        ArrayList<Point> surfaceLibre = plan.getSurfaceLibre();
        if(etat == Etat.CREER_FORME_LIBRE && surfaceLibre.size()>1){
            g.drawOval(surfaceLibre.get(0).x-5, surfaceLibre.get(0).y-5, 10, 10);
            for (int i = 0; i < surfaceLibre.size()-1; i++) {
                Point p1 = surfaceLibre.get(i);
                Point p2 = surfaceLibre.get(i+1);
                g.drawLine(p1.x, p1.y, p2.x, p2.y);
                g.drawOval(p2.x-5, p2.y-5, 10, 10);
            }
            Point last = surfaceLibre.get(surfaceLibre.size()-1);
            g.drawLine(last.x, last.y, mouse.x, mouse.y);
        }
        else if (etat == Etat.CREER_FORME_LIBRE && surfaceLibre.size() == 1){
            g.drawOval(surfaceLibre.get(0).x-5, surfaceLibre.get(0).y-5, 10, 10);
            g.drawLine(surfaceLibre.get(0).x, surfaceLibre.get(0).y, mouse.x, mouse.y);
        }
    }

    public Plan getPlan() {
        return plan;
    }
}
