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
                etat = plan.ajouterSurface(new Point(x, y));
                break;
            case LECTURE:
                break;
        }

        notifyObservers();
    }

    private void notifyObservers(){
        for (Observer observer: observers) {
            observer.update();
        }
    }

    public void paintCanevas(Graphics g, int pageHeight, int pageWidth){
        g.setColor(Color.gray);
        for(int i = 0; i < pageWidth-1; i += 50){
            for(int j = 0; j < pageHeight-1; j+=50){
                g.drawRect(i, j, 50, 50);
            }
        }
        g.setColor(Color.blue);
        for(Surface surface : plan.recupererSurfaces()){
            g.drawPolygon(surface.polygone);
        }
    }
}
