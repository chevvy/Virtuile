package Vues.Alignement;

import MVC.Controller;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class PanneauAlignementHorizontalImage extends JPanel {
    private BufferedImage imageGauche01;

    public PanneauAlignementHorizontalImage() {
        SetUpUi();
    }

    private void SetUpUi() {
        this.setSize(new Dimension(1100, 400));

        try {
            BufferedImage imageGauche01 = ImageIO.read(new File("src/Ressources/gaucheExt.png"));
            JLabel picLabel = new JLabel(new ImageIcon(imageGauche01));
            this.add(picLabel);


        } catch (IOException ex) {
            System.out.println("Pas d'image :(");
        }
        try {
            BufferedImage imageGauche01 = ImageIO.read(new File("src/Ressources/gaucheInt.png"));
            JLabel picLabel = new JLabel(new ImageIcon(imageGauche01));
            this.add(picLabel);


        } catch (IOException ex) {
            System.out.println("Pas d'image :(");
        }
        try {
            BufferedImage imageGauche01 = ImageIO.read(new File("src/Ressources/centreHorizontal.png"));
            JLabel picLabel = new JLabel(new ImageIcon(imageGauche01));
            this.add(picLabel);


        } catch (IOException ex) {
            System.out.println("Pas d'image :(");
        }
        try {
            BufferedImage imageGauche01 = ImageIO.read(new File("src/Ressources/droiteInt.png"));
            JLabel picLabel = new JLabel(new ImageIcon(imageGauche01));
            this.add(picLabel);


        } catch (IOException ex) {
            System.out.println("Pas d'image :(");
        }
        try {
            BufferedImage imageGauche01 = ImageIO.read(new File("src/Ressources/droiteExt.png"));
            JLabel picLabel = new JLabel(new ImageIcon(imageGauche01));
            this.add(picLabel);


        } catch (IOException ex) {
            System.out.println("Pas d'image :(");
        }
    }
}