package Vues.Espacement;
import MVC.Controller;

import javax.swing.*;
import java.awt.*;

public class FrameEspacement extends JFrame{

    public FrameEspacement frame;

    private Controller controller;
    public JFrame original;

    public FrameEspacement(Controller controller, JFrame original) {
        frame = this;
        this.original = original;
        frame.setSize(300, 160);
        if(controller.getModeImperial()){
            frame.setSize(450, 160);
        }
        frame.setLocation(100,100);
        frame.setTitle("Espacement entre deux surfaces");
        frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);


        PanneauEspacement panelEspacement = new PanneauEspacement(controller, this);
        frame.add(panelEspacement);
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                original.setEnabled(true);
            }
        });
    }
}