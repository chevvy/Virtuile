package Vues;
import MVC.Controller;
import MVC.Etat;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanneauConfirmerAnnuler extends JPanel {
    private Controller controller;
    public PanneauConfirmerAnnuler(Controller controller) {
        SetUpUi();
    }
    private void SetUpUi() {

        JButton boutonOk = new JButton("Ok");
        boutonOk.setSize(80, 40);
        boutonOk.setLocation(20, 20);
        this.add(boutonOk);
        boutonOk.addActionListener(actionEvent -> controller.plan.confirmerDeplacement());

        JButton boutonAnnuler = new JButton("Annuler");
        boutonAnnuler.setSize(80, 40);
        boutonAnnuler.setLocation(140, 20);
        this.add(boutonAnnuler);
        boutonOk.addActionListener(actionEvent -> controller.plan.annulerAligner());
        this.setVisible(true);
    }
}
