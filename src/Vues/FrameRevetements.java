package Vues;
import MVC.Controller;
import javax.swing.*;
import java.awt.*;

public class FrameRevetements extends JFrame{
    private PanneauRevetements panelVueRevetements;
    private Controller controller;

    public FrameRevetements() {
        this.setSize(500, 500);
        this.setLocation(100,100);

        this.setTitle("Propriétés des revêtements");
        this.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        panelVueRevetements = new PanneauRevetements(controller);
        this.add(panelVueRevetements, BorderLayout.CENTER);
    }


}
