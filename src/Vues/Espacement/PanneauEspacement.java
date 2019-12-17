package Vues.Espacement;
import MVC.Controller;
import MVC.Etat;
import Vues.Espacement.FrameEspacement;
import Vues.ImperialLabel;
import Vues.Revetements.FrameRevetements;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanneauEspacement extends JPanel {
    private Controller controller;
    private FrameEspacement frame;
    public PanneauEspacement(Controller controller, FrameEspacement frame) {
        this.controller = controller;
        this.frame = frame;
        SetUpUi();
    }
    private void SetUpUi() {
        this.setLayout(null);
        JLabel espacementHorizontal = new JLabel("Ajoutez une distance horizontale");
        espacementHorizontal.setSize(200, 30);
        espacementHorizontal.setLocation(20, 20);
        this.add(espacementHorizontal);

        JSpinner espacementHorizontalSpinner = new JSpinner(new SpinnerNumberModel(0, -1000, 1000, 1));
        espacementHorizontalSpinner.setBounds(210,20, 70,25);
        espacementHorizontalSpinner.setVisible(!controller.getModeImperial());
        this.add(espacementHorizontalSpinner);

        ImperialLabel espacementHorizontalImperial = new ImperialLabel(true);
        espacementHorizontalImperial.setLocation(210, 20);
        espacementHorizontalImperial.setVisible(controller.getModeImperial());
        espacementHorizontalImperial.setVallues(0);
        this.add(espacementHorizontalImperial);

        JLabel espacementVertical = new JLabel("Ajoutez une distance verticale");
        espacementVertical.setSize(200, 30);
        espacementVertical.setLocation(20, 50);
        this.add(espacementVertical);

        JSpinner espacementVerticalSpinner = new JSpinner(new SpinnerNumberModel(0, -1000, 1000, 1));
        espacementVerticalSpinner.setBounds(210,50, 70,25);
        espacementVerticalSpinner.setVisible(!controller.getModeImperial());
        this.add(espacementVerticalSpinner);

        ImperialLabel espacementVerticalImperial = new ImperialLabel(true);
        espacementVerticalImperial.setLocation(210, 50);
        espacementVerticalImperial.setVisible(controller.getModeImperial());
        espacementVerticalImperial.setVallues(0);
        this.add(espacementVerticalImperial);


        JButton boutonOk = new JButton("Ok");
        boutonOk.setSize(80, 40);
        boutonOk.setLocation(20, 80);
        this.add(boutonOk);
        boutonOk.addActionListener(e -> {
            try{
                if(controller.getModeImperial()){
                    controller.espacer((int) espacementHorizontalImperial.getValue(),
                            (int) espacementVerticalImperial.getValue());
                }else{
                    controller.espacer((int) espacementHorizontalSpinner.getValue(),
                            (int) espacementVerticalSpinner.getValue());

                }
                controller.relacher();
                frame.original.setEnabled(true);
                frame.dispose();
            } catch (Exception ex){}
        });
        JButton boutonAnnuler = new JButton("Annuler");
        boutonAnnuler.setSize(80, 40);
        boutonAnnuler.setLocation(140, 80);
        this.add(boutonAnnuler);
        boutonAnnuler.addActionListener(e -> {
            controller.annulerAligner();
            frame.original.setEnabled(true);
            frame.dispose();
        });
        this.setVisible(true);
    }
}
