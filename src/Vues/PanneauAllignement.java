package Vues;

import MVC.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;


public class PanneauAllignement extends JPanel{
    private ButtonGroup buttonGroupVertical, buttonGroupHorizontal;
    private JRadioButton boutonGauche01, boutonGauche02, boutonCentreVertical, boutonDroite01, boutonDroite02, boutonHaut01, boutonHaut02, boutonCentreHorizontal, boutonBas01, boutonBas02, aucunAlligmentHorizontal;
    private Controller controller;
    private BufferedImage imageGauche01;
    public PanneauAllignement(Controller controller) {
        SetUpUi();
    }
    private void SetUpUi () {
        try {
            BufferedImage imageGauche01 = ImageIO.read(new File("path-to-file"));
            JLabel picLabel = new JLabel(new ImageIcon(imageGauche01));
            add(picLabel);
        } catch (IOException ex) {
            System.out.println("Pas d'image :(");
        }
        //Groupe Vertical
        buttonGroupVertical = new ButtonGroup();
        JRadioButton boutonGauche01 = new JRadioButton("Gauche - Extérieur");
        this.add(boutonGauche01);
        buttonGroupVertical.add(boutonGauche01);
        JRadioButton boutonGauche02 = new JRadioButton("Gauche - Intérieur");
        this.add(boutonGauche02);
        buttonGroupVertical.add(boutonGauche02);
        JRadioButton boutonCentreVertical = new JRadioButton(" Centré           ");
        this.add(boutonCentreVertical);
        buttonGroupVertical.add(boutonCentreVertical);
        JRadioButton boutonDroite01 = new JRadioButton("Droite - Intérieur");
        this.add(boutonDroite01);
        buttonGroupVertical.add(boutonDroite01);
        JRadioButton boutonDroite02 = new JRadioButton("Droite - Extérieur");
        this.add(boutonDroite02);
        buttonGroupVertical.add(boutonDroite02);
        JRadioButton aucunAlligmentVertical = new JRadioButton("Aucun allignement vertical");
        aucunAlligmentVertical.setSelected(true);
        this.add(aucunAlligmentVertical);
        buttonGroupVertical.add(aucunAlligmentVertical);

        //Groupe Horizontal
        buttonGroupHorizontal = new ButtonGroup();
        JRadioButton boutonHaut01 = new JRadioButton("Haut - Extérieur ");
        buttonGroupHorizontal.add(boutonHaut01);
        JRadioButton boutonHaut02 = new JRadioButton("Haut - Intérieur");
        buttonGroupHorizontal.add(boutonHaut02);
        JRadioButton boutonCentreHorizontal = new JRadioButton("Centré");
        buttonGroupHorizontal.add(boutonCentreHorizontal);
        JRadioButton boutonBas01 = new JRadioButton("Bas - Intérieur");
        buttonGroupHorizontal.add(boutonBas01);
        JRadioButton boutonBas02 = new JRadioButton("Bas - Extérieur");
        buttonGroupHorizontal.add(boutonBas02);
        JRadioButton aucunAlligmentHorizontal = new JRadioButton("Aucun allignement horizontal");
        aucunAlligmentHorizontal.setSelected(true);
        buttonGroupVertical.add(aucunAlligmentHorizontal);

        this.setVisible(true);


    }
}
