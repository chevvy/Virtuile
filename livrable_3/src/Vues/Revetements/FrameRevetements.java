package Vues.Revetements;
import MVC.Controller;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class FrameRevetements extends JFrame{
    private Controller controller;
    private FrameRevetements frame;
    public JFrame original;

    public FrameRevetements(Controller controller, JFrame original) {
        this.controller = controller;
        this.original = original;
        this.setSize(800, 500);
        this.setLocation(100,100);
        this.setTitle("Propriétés des matériaux");
        this.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        PanneauChoixRevetements panelChoixRevetements = new PanneauChoixRevetements(this.controller);
        this.add(panelChoixRevetements, BorderLayout.WEST);
        PanneauInformationsRevetement panelInformationsRevetements = new PanneauInformationsRevetement(this.controller, this);
        this.add(panelInformationsRevetements, BorderLayout.EAST);
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                original.setEnabled(true);
            }
        });
    }
}
