package Vues.Materiaux;

import MVC.Controller;
import Vues.Alignement.FrameAlignement;
import Vues.Revetements.FrameRevetements;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanneauCouleur extends JPanel{
    private Controller controller;
    public FrameCouleur frame;

    public PanneauCouleur(Controller controller, FrameCouleur frame) {

        SetUpUi();
        this.controller = controller;
        this.frame = frame;

        }
        private void SetUpUi() {
            //Nouvelle couleur
            JLabel couleurNom= new JLabel("Nouvelle couleur :");
            couleurNom.setBounds(10,10,130,25);
            this.add(couleurNom);

            JTextField couleurField = new JTextField(14);
            couleurField.setBounds(180,10,40,25);
            this.add(couleurField);


            JButton boutonOk = new JButton("Ajouter");
            boutonOk.setSize(80, 40);
            boutonOk.setLocation(50, 20);
            this.add(boutonOk);
            boutonOk.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    controller.ajouterCouleur(couleurField.getText());
                    frame.dispose();
                    new FrameRevetements(controller).setVisible(true);

                }
            });
        }
}
