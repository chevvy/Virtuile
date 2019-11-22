package MVC;

import Domaine.Plan;
import Domaine.Surface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Controller {
    private ArrayList<Observer> observers;

    public Plan plan;
    public ArrayList<Point> patronForme;
    private Etat etat = Etat.LECTURE;
    public String nomRevetementSelectionne = "";

    public Controller(){
        observers = new ArrayList<>();
        plan = new Plan();
    }

    public Etat getEtat(){
        return etat;
    }

    public void setEtat(Etat etat){
        this.etat = etat;
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
                        add(new Point(1, 2));
                    }
                };
                etat = Etat.AJOUTER_SURFACE;
                break;
            case 2:
                etat = Etat.CREER_FORME_LIBRE;
                plan.initialiserSurfaceLibre();
                break;
            case 3: // Pentagramme
                patronForme = new ArrayList<Point>() {
                    {
                        add(new Point(5, 0));
                        add(new Point(0, 3));
                        add(new Point(2, 8));
                        add(new Point(8, 8));
                        add(new Point(10, 3));
                    }
                };
                etat = Etat.AJOUTER_SURFACE;
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

    public void clic(Point p){
        switch(etat){
            case AJOUTER_SURFACE:
                etat = plan.initialiserSurface(p, patronForme);
                break;
            case LECTURE:
                etat = plan.selectionner(p);
                break;
            case CREER_FORME_LIBRE:
                etat = plan.ajouterPointSurfaceLibre(p);
                break;
            case SELECTIONNER_ALIGNER:
                etat = plan.selectionnerAligner(p);
            default:
                break;
        }
        notifyObservers();
    }

    public void glisser(Point p){
        switch(etat){
            case ETIRER_SURFACE:
                plan.etirerSurface(p);
                break;
            case DEPLACER_SURFACE:
                plan.deplacerSurface(p);
                break;
            default:
                break;
        }
        notifyObservers();
    }

    public void relacher(){
        switch(etat){
            case ETIRER_SURFACE:
            case ALIGNER:
            case DEPLACER_SURFACE:
                etat = Etat.LECTURE;
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
                value = "Déplacez la forme avec la souris";
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
            g.setColor(Color.blue.brighter());
            g.fillPolygon(surfaceSelectionnee.polygone);

            g.setColor(Color.yellow);
            g.drawPolygon(plan.surfaceSelectionnee.polygone);
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

    public void setGridSize(int size){
        this.plan.setGridSize(size);
        notifyObservers();
    }

    public int getGridSize(){
        return this.plan.getGridSize();
    }

    public void setGrilleMagnetiqueActive(boolean active){
        this.plan.setGrilleMagnetiqueActive(active);
    }

    public Plan getPlan() {
        return plan;
    }
}
