package Vues.Alignement;

import MVC.Controller;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
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

        //Gauche/Extérieur
        JRadioButton boutonGauche01 = new JRadioButton("     ");
        this.add(boutonGauche01);
        buttonGroupHorizontal.add(boutonGauche01);
        boutonGauche01.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e1) { controller.aligner("gaucheExt"); }
        });

        //Gauche/Intérieur
        JRadioButton boutonGauche02 = new JRadioButton("     ");
        this.add(boutonGauche02);
        buttonGroupHorizontal.add(boutonGauche02);
        boutonGauche02.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e1) { controller.aligner("gaucheInt"); }
        });
        //Centré horizontalement
        JRadioButton boutonCentreHorizontal = new JRadioButton("     ");
        this.add(boutonCentreHorizontal);
        buttonGroupHorizontal.add(boutonCentreHorizontal);
        boutonCentreHorizontal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e1) { controller.aligner("centreHorizontal"); }
        });

        //Centré horizontalement
        JRadioButton boutonDroite01 = new JRadioButton("     ");
        this.add(boutonDroite01);
        buttonGroupHorizontal.add(boutonDroite01);
        boutonDroite01.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e1) { controller.aligner("droiteInt"); }
        });

        //Droite/Extérieur
        JRadioButton boutonDroite02 = new JRadioButton("     ");
        this.add(boutonDroite02);
        buttonGroupHorizontal.add(boutonDroite02);
        boutonDroite02.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e1) { controller.aligner("droiteExt"); }
        });

        //Aucun alignement horizontal
        JRadioButton aucunAlligmentHorizontal = new JRadioButton("     ");
        aucunAlligmentHorizontal.setSelected(true);
        this.add(aucunAlligmentHorizontal);
        buttonGroupHorizontal.add(aucunAlligmentHorizontal);
        aucunAlligmentHorizontal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e1) { controller.aligner("rienHorizontal"); }
        });

        this.setVisible(true);


    }
}
