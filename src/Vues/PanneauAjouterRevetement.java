package Vues;

import MVC.Controller;

import javax.swing.*;
import java.awt.*;

public class PanneauAjouterRevetement extends JPanel{

    private JButton boutonAjouter;
    private Controller controller;
    public PanneauAjouterRevetement(Controller controller) {
        this.setLayout(null);
        SetUpUi();
    }
    private void SetUpUi() {

        JButton boutonAjouter = new JButton("Ajouter un revêtement");
        boutonAjouter.setSize(150, 40);
        boutonAjouter.setLocation(10, 20);
        this.add(boutonAjouter);
        this.setVisible(true);
    }

}
