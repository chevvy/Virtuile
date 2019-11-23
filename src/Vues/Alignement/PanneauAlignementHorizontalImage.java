package Vues.Alignement;

import MVC.Controller;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class PanneauAlignementHorizontalImage extends JPanel {


    public PanneauAlignementHorizontalImage() {
        SetUpUi();
    }

    private void SetUpUi() {
        setLayout(new GridLayout(1,6));

        //gaucheExt
        try {
            BufferedImage imageGauche01 = ImageIO.read(new File("src/Ressources/gaucheExt.png"));
            JLabel image = new JLabel(new ImageIcon(imageGauche01));
            this.add(image);


        } catch (IOException ex) {
            System.out.println("Pas d'image :(");
        }

        //gaucheInt
        try {
            BufferedImage imageGauche02 = ImageIO.read(new File("src/Ressources/gaucheInt.png"));
            JLabel image = new JLabel(new ImageIcon(imageGauche02));
            this.add(image);

        } catch (IOException ex) {
            System.out.println("Pas d'image :(");
        }

        //centreHorizontal
        try {
            BufferedImage imageCentre = ImageIO.read(new File("src/Ressources/gaucheExt.png"));
            JLabel image = new JLabel(new ImageIcon(imageCentre));
            this.add(image);

        } catch (IOException ex) {
            System.out.println("Pas d'image :(");
        }

        //droiteInt
        try {
            BufferedImage imageDroite01 = ImageIO.read(new File("src/Ressources/droiteInt.png"));
            JLabel image = new JLabel(new ImageIcon(imageDroite01));
            this.add(image);

        } catch (IOException ex) {
            System.out.println("Pas d'image :(");
        }

        //droiteExt
        try {
            BufferedImage imagDroite02 = ImageIO.read(new File("src/Ressources/droiteExt.png"));
            JLabel image = new JLabel(new ImageIcon(imagDroite02));
            this.add(image);

        } catch (IOException ex) {
            System.out.println("Pas d'image :(");
        }

        //vide
        try {
            BufferedImage imageNoAlignement = ImageIO.read(new File("src/Ressources/noAlignement.png"));
            JLabel image = new JLabel(new ImageIcon(imageNoAlignement));
            this.add(image);

        } catch (IOException ex) {
            System.out.println("Pas d'image :(");
        }

    }
}