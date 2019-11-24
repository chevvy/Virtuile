package Vues.Materiaux;

import MVC.Controller;

import javax.swing.*;
import java.awt.*;

public class FrameCouleur extends JFrame{

    private Controller controller;

    public FrameCouleur frameCouleur;

    public FrameCouleur(Controller controller) {
        frameCouleur = this;
        frameCouleur.setSize(330, 100);
        frameCouleur.setLocation(400,300);
        frameCouleur.setTitle("Ajouter une nouvelle couleur");
        frameCouleur.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);

        PanneauCouleur panelCouleur = new PanneauCouleur(controller, this);
        frameCouleur.add(panelCouleur) ;

    }
}