package Vues;
import MVC.Controller;
import javax.swing.*;
import java.awt.*;

public class FrameRevetements extends JFrame{
    private PanneauChoixRevetements panelChoixRevetements;
    private PanneauInformationsRevetement panelInformationsRevetements;
    private Controller controller;

    public FrameRevetements() {
        this.setSize(800, 500);
        this.setLocation(100,100);
        this.setTitle("Propriétés des revêtements");
        this.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        panelChoixRevetements = new PanneauChoixRevetements(controller);
        this.add(panelChoixRevetements, BorderLayout.WEST);
        panelInformationsRevetements = new PanneauInformationsRevetement(controller);
        this.add(panelInformationsRevetements, BorderLayout.EAST);

    }
}
