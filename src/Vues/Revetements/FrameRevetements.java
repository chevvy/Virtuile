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

    public FrameRevetements(Controller controller) {

        this.controller = controller;
        this.setSize(800, 430);
        this.setLocation(100,100);
        this.setTitle("Propriétés des matériaux");
        this.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        PanneauChoixRevetements panelChoixRevetements = new PanneauChoixRevetements(this.controller);
        this.add(panelChoixRevetements, BorderLayout.WEST);
        PanneauInformationsRevetement panelInformationsRevetements = new PanneauInformationsRevetement(this.controller, this);
        this.add(panelInformationsRevetements, BorderLayout.EAST);

    }
}
