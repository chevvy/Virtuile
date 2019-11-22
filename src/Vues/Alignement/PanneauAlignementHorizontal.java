package Vues.Alignement;

import MVC.Controller;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;


public class PanneauAlignementHorizontal extends JPanel{
    private JRadioButton boutonGauche01, boutonGauche02, boutonCentreHorinzontal, boutonDroite01, boutonDroite02, aucunAlligmentHorizontal;
    private Controller controller;
    public PanneauAlignementHorizontal(Controller controller) {

        SetUpUi();
        this.controller = controller;
    }
    private void SetUpUi () {

        //Groupe Horizontal
        ButtonGroup buttonGroupHorizontal = new ButtonGroup();


        JRadioButton boutonGauche01 = new JRadioButton("Gauche - Extérieur  ");
        this.add(boutonGauche01);
        buttonGroupHorizontal.add(boutonGauche01);
        boutonGauche01.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e1) { controller.aligner("gaucheExt"); }
        });

        JRadioButton boutonGauche02 = new JRadioButton("Gauche - Intérieur  ");
        this.add(boutonGauche02);
        buttonGroupHorizontal.add(boutonGauche02);
        boutonGauche02.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e1) { controller.aligner("gaucheInt"); }
        });

        JRadioButton boutonCentreHorizontal = new JRadioButton(" Centré horizontalement  ");
        this.add(boutonCentreHorizontal);
        buttonGroupHorizontal.add(boutonCentreHorizontal);
        boutonCentreHorizontal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e1) { controller.aligner("centreHorizontal"); }
        });

        JRadioButton boutonDroite01 = new JRadioButton("Droite - Intérieur  ");
        this.add(boutonDroite01);
        buttonGroupHorizontal.add(boutonDroite01);
        boutonDroite01.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e1) { controller.aligner("droiteInt"); }
        });


        JRadioButton boutonDroite02 = new JRadioButton("Droite - Extérieur  ");
        this.add(boutonDroite02);
        buttonGroupHorizontal.add(boutonDroite02);
        boutonDroite02.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e1) { controller.aligner("droiteExt"); }
        });

        JRadioButton aucunAlligmentHorizontal = new JRadioButton("Aucun allignement vertical    ");
        aucunAlligmentHorizontal.setSelected(true);
        this.add(aucunAlligmentHorizontal);
        buttonGroupHorizontal.add(aucunAlligmentHorizontal);

        this.setVisible(true);


    }
}
