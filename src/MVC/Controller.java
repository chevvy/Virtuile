package MVC;

import java.util.ArrayList;

public class Controller {
    private ArrayList<Observer> observers;

    public void notifyObservers(){
        for (Observer observer: observers) {
            observer.update();
        }
    }
}
