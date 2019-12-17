package Vues.Alignement;
import MVC.Controller;

import javax.swing.*;
import java.awt.*;

public class FrameAlignement extends JFrame{

    public FrameAlignement frame;

    private Controller controller;
    public JFrame original;

    public FrameAlignement(Controller controller, JFrame original) {
        frame = this;
        frame.setSize(750, 350);
        frame.setLocation(100,100);
        frame.setTitle("Alignement des surfaces");
        frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        this.original = original;


        PanneauAlignement panelAlignement = new PanneauAlignement(controller, this);
        frame.add(panelAlignement);

        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                original.setEnabled(true);
            }
        });
    }

}