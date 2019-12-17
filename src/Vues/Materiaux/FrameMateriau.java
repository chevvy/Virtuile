package Vues.Materiaux;

import MVC.Controller;

import javax.swing.*;
import java.awt.*;

public class FrameMateriau extends JFrame{

    private Controller controller;
    public JFrame original;
    public FrameMateriau frameMateriau;

    public FrameMateriau(Controller controller, JFrame original) {
        frameMateriau = this;
        this.original = original;
        frameMateriau.setSize(330, 100);
        frameMateriau.setLocation(400,300);
        frameMateriau.setTitle("Ajouter un nouveau type de matériau");
        frameMateriau.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);

        PanneauMateriau panelMateriau = new PanneauMateriau(controller, this);
        frameMateriau.add(panelMateriau) ;

        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                original.setEnabled(true);
            }
        });
    }
}