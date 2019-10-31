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
        this.add(panelChoixRevetements, BorderLayout.EAST);
        panelAjouterRevenement = new PanneauAjouterRevetement(controller);
        this.add(panelAjouterRevenement, BorderLayout.CENTER);
        panelInformationsRevetements = new PanneauInformationsRevetement(controller);
        this.add(panelInformationsRevetements, BorderLayout.WEST);
    }
}