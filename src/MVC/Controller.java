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

    public void ajouterSurface(Point position){
        etat = Etat.AJOUTER_SURFACE;
    }

    public void clic(int x, int y){
        switch(etat){
            case AJOUTER_SURFACE:
                etat = plan.initialiserSurface(new Point(x, y));
                break;
            case LECTURE:
                etat = plan.selectionner(new Point(x, y));
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
    }
}
