package Vues;

import MVC.Controller;

import javax.swing.*;

public class PanneauAllignement extends JPanel{
    private JButton boutonAjouter;
    private Controller controller;
    public PanneauAllignement(Controller controller) {
        SetUpUi();
    }
    private void SetUpUi () {
        JButton boutonAjouter = new JButton("Ajouter le revêtement");
        boutonAjouter.setSize(150, 40);
        boutonAjouter.setLocation(10, 20);
        this.add(boutonAjouter);
        this.setVisible(true);
    }
}
