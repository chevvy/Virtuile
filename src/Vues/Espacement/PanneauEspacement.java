package Vues.Espacement;
import MVC.Controller;
import MVC.Etat;
import Vues.Espacement.FrameEspacement;
import Vues.Revetements.FrameRevetements;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanneauEspacement extends JPanel {
    private Controller controller;
    private FrameEspacement frame;
    public PanneauEspacement(Controller controller, FrameEspacement frame) {

        SetUpUi();
        this.controller = controller;
        this.frame = frame;
    }
    private void SetUpUi() {

        JLabel espacementHorizontal = new JLabel("Ajoutez une distance verticale");
        espacementHorizontal.setSize(80, 30);
        espacementHorizontal.setLocation(20, 20);
        this.add(espacementHorizontal);

        JSpinner espacementHorizontalSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 1000, 1));
        espacementHorizontalSpinner.setBounds(150,20, 70,25);
        this.add(espacementHorizontalSpinner);

        JLabel espacementVertical = new JLabel("Ajoutez une distance horizontale");
        espacementVertical.setSize(80, 30);
        espacementVertical.setLocation(20, 50);
        this.add(espacementVertical);

        JSpinner espacementVerticalSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 1000, 1));
        espacementVerticalSpinner.setBounds(150,50, 70,25);
        this.add(espacementVerticalSpinner);



        JButton boutonOk = new JButton("Ok");
        boutonOk.setSize(80, 40);
        boutonOk.setLocation(20, 80);
        this.add(boutonOk);
        boutonOk.addActionListener(e -> {
            controller.espacer((Integer) espacementHorizontalSpinner.getValue(),
                    (Integer) espacementVerticalSpinner.getValue());
            controller.relacher();
            frame.dispose();
        });
        JButton boutonAnnuler = new JButton("Annuler");
        boutonAnnuler.setSize(80, 40);
        boutonAnnuler.setLocation(140, 80);
        this.add(boutonAnnuler);
        boutonAnnuler.addActionListener(e -> {
            controller.annulerAligner();
            frame.dispose();
        });
        this.setVisible(true);
    }
}
