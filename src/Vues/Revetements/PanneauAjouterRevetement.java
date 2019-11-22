package Vues.Revetements;

import MVC.Controller;

import javax.swing.*;
import java.awt.*;

public class PanneauAjouterRevetement extends JPanel{

    private JButton boutonAjouter;
    private Controller controller;
    public PanneauAjouterRevetement(Controller controller) {
        SetUpUi();
    }
    private void SetUpUi() {

        JButton boutonAjouter = new JButton("Ajouter le revêtement");
        boutonAjouter.setSize(150, 40);
        boutonAjouter.setLocation(10, 20);
        this.add(boutonAjouter);
        this.setVisible(true);
    }

}