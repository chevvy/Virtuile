package MVC;

import Domaine.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public class Controller {
    private ArrayList<Observer> observers;

    public Plan plan;
    public GestionnaireRevetements gestionnaireRevetements;
    private boolean trou;
    public ArrayList<Point> patronForme;
    private Etat etat = Etat.LECTURE;
    private Point positionSourisActuelle = new Point();

    public Controller(){
        observers = new ArrayList<>();
        plan = new Plan();
        gestionnaireRevetements = new GestionnaireRevetements();
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

    public void setDimensionsSurface(int hauteur, int largeur){
        plan.setDimensionsSurface(hauteur, largeur);
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

    public void bouger(Point p){
        switch (etat){
            case LECTURE:
                this.positionSourisActuelle =  p;
                break;
            default:
                break;
        }
        notifyObservers();
    }

    public void relacher(){
        switch (etat){
            case CREER_FORME_LIBRE:
                break;
            default:
                etat = Etat.LECTURE;
                break;
        }

        notifyObservers();
    }

    public void ClicMenu(){
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
            case SELECTIONNER_ALIGNER:
                value = "Cliquer sur la forme avec laquelle vous désirez aligner";
                break;
        }
        return value;
    }

    public void paintCanevas(Graphics g, Point mouse){
        Surface surfaceSelectionnee = plan.surfaceSelectionnee;
        for(Surface surface : plan.recupererSurfaces()){
            g.setClip(surface.getAireSansTrou());
            g.setColor(surface.estUnTrou?Color.white:surface.getCouleurCoulis().darker());
            g.fillPolygon(surface.polygone);
            if(!surface.estUnTrou){
                for (Tuile tuile : surface.getListeTuiles()){
                    g.setColor(surface.getRevetement().getCouleurTuile().darker());
                    g.fillPolygon(tuile.getPolygone());
                }
            }
            g.setClip(null);
        }

        if(surfaceSelectionnee != null){
            g.setColor(surfaceSelectionnee.estUnTrou?Color.green:surfaceSelectionnee.getCouleurCoulis());
            g.fillPolygon(surfaceSelectionnee.polygone);
            if(!surfaceSelectionnee.estUnTrou){
                g.setColor(surfaceSelectionnee.getRevetement().getCouleurTuile());
                for (Tuile tuile : surfaceSelectionnee.getListeTuiles()){
                    g.fillPolygon(tuile.getPolygone());
                }
            }
            g.setColor(Color.green);
            surfaceSelectionnee.trous.forEach(trou -> g.fillPolygon(trou.polygone));
            g.setColor(Color.black);
            Rectangle limites = plan.surfaceSelectionnee.polygone.getBounds();
            g.drawRect(limites.x, limites.y, limites.width, limites.height);
            if (etat == Etat.LECTURE){
                g.drawRect(limites.x, limites.y, limites.width, limites.height);
                g.drawOval(limites.x-5, limites.y-5, 10, 10);
                g.drawOval(limites.x+limites.width-5, limites.y-5, 10, 10);
                g.drawOval(limites.x+limites.width-5, limites.y+limites.height-5, 10, 10);
                g.drawOval(limites.x-5, limites.y+limites.height-5, 10, 10);
            }
            g.setColor(Color.yellow);
            g.drawPolygon(plan.surfaceSelectionnee.polygone);
        }

        g.setColor(new Color(145, 7, 12));
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


    //Type de matériaux
    public void ajouterTypeMateriau(String typeMateriau){

        plan.ajouterTypeMateriau(typeMateriau);
    }

    public ArrayList<String> getTypeMateriaux(){ return plan.getListeTypeMateriau(); }

    //Type de motifs de tuiles
    public ArrayList<String> getMotifs(){ return plan.getListeMotifs(); }

    //Revêtements
    public Map<String, Revetement> getRevetements(){return gestionnaireRevetements.getMapRevetements();}

    public Set getNomRevetements(){return gestionnaireRevetements.getNomRevetements();}

    public Map<String, String> getInfosRevetements(String nom){return gestionnaireRevetements.getInfosRevetement(nom);}

    public Map<String, String> getInfosRevetementSelect() {
        String nom = plan.surfaceSelectionnee.getRevetement().getNomDuRevetement();
        return gestionnaireRevetements.getInfosRevetement(nom);}

    public void ajouterRevetement(String nomRevetement, String typeMateriauTuile, Color couleurTuile,
                                                 String motifTuile, int hauteurTuile, int longueurTuile, int nbTuilesBoite){
        Revetement revetement = new Revetement(nomRevetement, typeMateriauTuile, couleurTuile,
                motifTuile, hauteurTuile, longueurTuile, nbTuilesBoite);
        gestionnaireRevetements.ajouterRevetement(nomRevetement, revetement);
    }

    public Point getPositionSourisActuelle() {
        return positionSourisActuelle;
    }

    public int getHauteurTuile(){
        return getPlan().surfaceSelectionnee.getTuileAtPoint(getPositionSourisActuelle()).getHeight();
    }

    public int getLargeurTuile(){
        return getPlan().surfaceSelectionnee.getTuileAtPoint(getPositionSourisActuelle()).getLength();
    }

    public void setEpaisseurCoulis(int epaisseur){
        if (plan.surfaceSelectionnee != null) {
            plan.surfaceSelectionnee.setTailleDuCoulis(epaisseur);
        }
        notifyObservers();
    }

    public Map<String, String> getInfosSurfaceSelect() {
        return plan.getInfosSurface(plan.surfaceSelectionnee);}

    public void setRevetement(Revetement revetement) {
        if(this.plan.surfaceSelectionnee != null) {
            this.plan.surfaceSelectionnee.setRevetement(revetement);
        }
        notifyObservers();
    }

    public void setCouleurCoulis(Color couleurCoulis) {
        if(plan.surfaceSelectionnee != null){
            this.plan.surfaceSelectionnee.setCouleurCoulis(couleurCoulis);
        }
        notifyObservers();
    }
    public Map<String, Integer> getNbBoites(){
        return this.gestionnaireRevetements.getNbBoites(plan.getListeSurfaces());
    }
    public Map<String, Integer> getNbTuilesTotal(){
        return this.gestionnaireRevetements.getNbTuilesTotal(plan.getListeSurfaces());
    }
}

