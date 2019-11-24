package Vues.Alignement;
import MVC.Controller;
import MVC.Etat;
import Vues.Revetements.FrameRevetements;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanneauAlignementConfirmerAnnuler extends JPanel {
    private Controller controller;
    public FrameAlignement frame;
    public PanneauAlignementConfirmerAnnuler(Controller controller, FrameAlignement frame) {

        SetUpUi();
        this.controller = controller;
        this.frame = frame;
    }
    private void SetUpUi() {

        JButton boutonOk = new JButton("Ok");
        boutonOk.setSize(80, 40);
        boutonOk.setLocation(20, 20);
        this.add(boutonOk);
        boutonOk.addActionListener(e -> {
            controller.relacher();
            frame.dispose();
        });
        JButton boutonAnnuler = new JButton("Annuler");
        boutonAnnuler.setSize(80, 40);
        boutonAnnuler.setLocation(140, 20);
        this.add(boutonAnnuler);
        boutonAnnuler.addActionListener(e -> {
            controller.annulerAligner();
            frame.dispose();
        });
        this.setVisible(true);
    }
}
