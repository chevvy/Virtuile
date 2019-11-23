package Vues.Revetements;
import MVC.Controller;
import javax.swing.*;
import java.awt.*;

public class FrameRevetements extends JFrame{

    public FrameRevetements(Controller controller) {
        this.setSize(800, 500);
        this.setLocation(100,100);
        this.setTitle("Propriétés des revêtements");
        this.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        PanneauChoixRevetements panelChoixRevetements = new PanneauChoixRevetements(controller);
        this.add(panelChoixRevetements, BorderLayout.WEST);
        PanneauInformationsRevetement panelInformationsRevetements = new PanneauInformationsRevetement(controller);
        this.add(panelInformationsRevetements, BorderLayout.EAST);

    }
}
