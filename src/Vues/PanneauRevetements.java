package Vues;
import MVC.Controller;

import javax.swing.*;
import java.awt.*;

public class PanneauRevetements extends JScrollPane {
    private PanneauChoixRevetements panelChoixRevetements;
    private PanneauAjouterRevetement panelAjouterRevenement;
    private PanneauInformationsRevetement panelInformationsRevetements;


    private Controller controller;

    public PanneauRevetements(Controller controller) {
        this.setLayout(null);
        this.controller = controller;
        panelChoixRevetements = new PanneauChoixRevetements(controller);
        this.add(panelChoixRevetements);
        panelChoixRevetements.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelAjouterRevenement = new PanneauAjouterRevetement(controller);
        this.add(panelAjouterRevenement);
        panelChoixRevetements.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelInformationsRevetements = new PanneauInformationsRevetement(controller);
        this.add(panelInformationsRevetements);
        panelChoixRevetements.setAlignmentX(Component.RIGHT_ALIGNMENT);
    }
}
