package Vues.Revetements;

import MVC.Controller;
import MVC.Observer;

import javax.swing.*;
import java.awt.*;

public class PanneauActions extends JPanel implements Observer {
    private Controller controller;
    private JButton boutonAjouter, boutonAjouterSurfaceVide, boutonSupprimer, boutonAlligment, boutonFusionner;

    public PanneauActions(Controller controller){
        this.controller = controller;
        controller.addObserver(this);
        this.setLayout(null);
        this.setPreferredSize(new Dimension(800, 40));
        this.setBorder(BorderFactory.createLineBorder(Color.black.brighter(), 1));
        setUpUI();
    }

    private void setUpUI(){
        boutonAjouter = new JButton("Ajouter une surface");
        boutonAjouter.setSize(150, 30);
        boutonAjouter.setLocation(10, 5);
        this.add(boutonAjouter);

        boutonAjouterSurfaceVide = new JButton("Ajouter une surface vide");
        boutonAjouterSurfaceVide.setSize(180, 30);
        boutonAjouterSurfaceVide.setLocation(170, 5);
        this.add(boutonAjouterSurfaceVide);

        boutonSupprimer = new JButton("Supprimer la surface");
        boutonSupprimer.setSize(150, 30);
        boutonSupprimer.setLocation(330, 5);
        this.add(boutonSupprimer);

        boutonAlligment = new JButton("Alligner/Coller");
        boutonAlligment.setSize(150, 30);
        boutonAlligment.setLocation(490, 5);
        this.add(boutonAlligment);

        boutonFusionner = new JButton("Fusionner");
        boutonFusionner.setSize(150, 30);
        boutonFusionner.setLocation(650, 5);
        this.add(boutonFusionner);
    }

    @Override
    public void update() {

    }
}
