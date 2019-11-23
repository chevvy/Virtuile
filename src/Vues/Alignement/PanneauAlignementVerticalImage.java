package Vues.Alignement;

import MVC.Controller;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class PanneauAlignementVerticalImage extends JPanel {

    public PanneauAlignementVerticalImage() {
        SetUpUi();
    }

    private void SetUpUi() {

        //hautExt
        try {
            BufferedImage imageHaut01 = ImageIO.read(new File("src/Ressources/hautExt.png"));
            JLabel image = new JLabel(new ImageIcon(imageHaut01));
            this.add(image);

        } catch (IOException ex) {
            System.out.println("Pas d'image :(");
        }

        //hautInt
        try {
            BufferedImage imageHaut02 = ImageIO.read(new File("src/Ressources/hautInt.png"));
            JLabel image = new JLabel(new ImageIcon(imageHaut02));
            this.add(image);

        } catch (IOException ex) {
            System.out.println("Pas d'image :(");
        }

        //centre vertical
        try {
            BufferedImage imageCentre = ImageIO.read(new File("src/Ressources/centreVertical.png"));
            JLabel image = new JLabel(new ImageIcon(imageCentre));
            this.add(image);

        } catch (IOException ex) {
            System.out.println("Pas d'image :(");
        }

        //bas int
        try {
            BufferedImage imageBas01 = ImageIO.read(new File("src/Ressources/basInt.png"));
            JLabel image = new JLabel(new ImageIcon(imageBas01));
            this.add(image);

        } catch (IOException ex) {
            System.out.println("Pas d'image :(");
        }

        //bas ext
        try {
            BufferedImage imageBas02 = ImageIO.read(new File("src/Ressources/basExt.png"));
            JLabel image = new JLabel(new ImageIcon(imageBas02));
            this.add(image);

        } catch (IOException ex) {
            System.out.println("Pas d'image :(");
        }

        //pas d'alignement vertical
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