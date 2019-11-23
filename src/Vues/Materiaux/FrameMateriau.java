package Vues.Materiaux;

import MVC.Controller;

import javax.swing.*;
import java.awt.*;

public class FrameMateriau extends JFrame{

    private Controller controller;

    public FrameMateriau frameMateriau;

    public FrameMateriau(Controller controller) {
        frameMateriau = this;
        frameMateriau.setSize(330, 100);
        frameMateriau.setLocation(400,300);
        frameMateriau.setTitle("Ajouter une nouvelle couleur");
        frameMateriau.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);

        PanneauMateriau panelMateriau = new PanneauMateriau(controller, this);
        frameMateriau.add(panelMateriau) ;

    }
}