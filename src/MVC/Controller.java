package MVC;

import Domaine.Plan;
import Domaine.Surface;
import Domaine.Tuile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Controller {
    private ArrayList<Observer> observers;

    public Plan plan;
    private boolean trou;
    public ArrayList<Point> patronForme;
    private Etat etat = Etat.LECTURE;

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

    public void setTrou(boolean trou) {
        this.trou = trou;
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
                trou = false;
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
                trou = false;
                etat = Etat.AJOUTER_SURFACE;
                break;
            case 2:
                trou = false;
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
                trou = false;
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
        notifyObservers();
    }

    public void annulerAligner(){
        plan.annulerAligner();
        etat = Etat.LECTURE;
        notifyObservers();
    }

    public void clic(Point p){
        switch(etat){
            case AJOUTER_SURFACE:
                etat = plan.initialiserSurface(p, patronForme, trou);
                break;
            case LECTURE:
                etat = plan.selectionner(p);
                notifyObservers();
                break;
            case CREER_FORME_LIBRE:
                etat = plan.ajouterPointSurfaceLibre(p, trou);
                break;
            case FUSIONNER:
                plan.fusionner(p);
                etat = Etat.LECTURE;
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
        etat = Etat.LECTURE;
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
            g.setColor(surface.estUnTrou?Color.white:Color.blue);
            g.fillPolygon(surface.polygone);
            g.setColor(Color.white);
            for(Surface trou : surface.trous){
                g.fillPolygon(trou.polygone);
            }
        }

        if(surfaceSelectionnee != null){
            g.setColor(Color.gray.darker());
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

        for(Surface surface : plan.recupererSurfaces()){
            for (Tuile tuile : surface.getListeTuiles()){
                g.setColor(new Color(203, 65, 84));
                g.fillPolygon(tuile.getPolygone());
                g.drawPolygon(tuile.getPolygone());
            }
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

    //Couleurs des tuiles
    public void ajouterCouleur(String couleur){

        plan.ajouterCouleur(couleur);
    }

    public ArrayList<String> getCouleurs(){ return plan.getListeCouleur(); }


    //Type de matériaux
    public void ajouterTypeMateriau(String typeMateriau){

        plan.ajouterTypeMateriau(typeMateriau);
    }

    public ArrayList<String> getTypeMatériaux(){ return plan.getListeTypeMateriau(); }

    //Type de motifs de tuiles
    public ArrayList<String> getMotifs(){ return plan.getListeMotifs(); }


}
