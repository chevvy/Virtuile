package Vues;

import MVC.Controller;

import javax.swing.*;

public class PanneauInformationsRevetement extends JPanel{
    private JButton boutonAjouter;
    Controller controller;
    public PanneauInformationsRevetement(Controller controller) {
        this.setLayout(null);
        SetUpUi();
    }

    private void SetUpUi() {

        JButton boutonAjouter = new JButton("informations bouton");
        boutonAjouter.setSize(150, 40);
        boutonAjouter.setLocation(10, 20);
        this.add(boutonAjouter);
        this.setVisible(true);
    }

}