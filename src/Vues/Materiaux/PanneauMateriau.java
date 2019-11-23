package Vues.Materiaux;

import MVC.Controller;
import Vues.Alignement.FrameAlignement;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanneauMateriau extends JPanel{
    private Controller controller;
    public FrameMateriau frame;

    public PanneauMateriau(Controller controller, FrameMateriau frame) {

        SetUpUi();
        this.controller = controller;
        this.frame = frame;

    }
    private void SetUpUi() {
        //Nouvelle couleur
        JLabel nomRevetementLabel = new JLabel("Nouveau type de matériaux :");
        nomRevetementLabel.setBounds(10,10,130,25);
        this.add(nomRevetementLabel);

        JTextField nomRevetementField = new JTextField(14);
        nomRevetementField.setBounds(180,10,40,25);
        this.add(nomRevetementField);


        JButton boutonOk = new JButton("Ajouter");
        boutonOk.setSize(80, 40);
        boutonOk.setLocation(50, 20);
        this.add(boutonOk);
        boutonOk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //controller.ajouterCouleur();
                frame.dispose();
            }
        });
    }
}
