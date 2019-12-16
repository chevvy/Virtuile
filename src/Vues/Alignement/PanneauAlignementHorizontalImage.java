package Vues.Alignement;

import MVC.Controller;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class PanneauAlignementHorizontalImage extends JPanel {

    private Controller controller;
    public PanneauAlignementHorizontalImage(Controller controller) {
        this.controller = controller;
        SetUpUi();
    }

    private void SetUpUi() {
        setLayout(new GridLayout(1,6));

        //gaucheExt
        try {
            BufferedImage imageGauche01 = ImageIO.read(new File("src/Ressources/gaucheExt.png"));
            JButton image = new JButton(new ImageIcon(imageGauche01));
            image.setMargin(new Insets(0, 0, 0, 0));
            image.setLayout(null);
            image.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e1) { controller.aligner("gaucheExt"); }
            });
            this.add(image);


        } catch (IOException ex) {
            System.out.println("Pas d'image :(");
        }

        //gaucheInt
        try {
            BufferedImage imageGauche02 = ImageIO.read(new File("src/Ressources/gaucheInt.png"));
            JButton image = new JButton(new ImageIcon(imageGauche02));
            image.setMargin(new Insets(0, 0, 0, 0));
            image.setLayout(null);
            image.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e1) { controller.aligner("gaucheInt"); }
            });
            this.add(image);

        } catch (IOException ex) {
            System.out.println("Pas d'image :(");
        }

        //centreHorizontal
        try {
            BufferedImage imageCentre = ImageIO.read(new File("src/Ressources/centreHorizontal.png"));
            JButton image = new JButton(new ImageIcon(imageCentre));
            image.setMargin(new Insets(0, 0, 0, 0));
            image.setLayout(null);
            image.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e1) { controller.aligner("centreHorizontal"); }
            });
            this.add(image);

        } catch (IOException ex) {
            System.out.println("Pas d'image :(");
        }

        //droiteInt
        try {
            BufferedImage imageDroite01 = ImageIO.read(new File("src/Ressources/droiteInt.png"));
            JButton image = new JButton(new ImageIcon(imageDroite01));
            image.setMargin(new Insets(0, 0, 0, 0));
            image.setLayout(null);
            image.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e1) { controller.aligner("droiteInt"); }
            });
            this.add(image);

        } catch (IOException ex) {
            System.out.println("Pas d'image :(");
        }

        //droiteExt
        try {
            BufferedImage imagDroite02 = ImageIO.read(new File("src/Ressources/droiteExt.png"));
            JButton image = new JButton(new ImageIcon(imagDroite02));
            image.setMargin(new Insets(0, 0, 0, 0));
            image.setLayout(null);
            image.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e1) { controller.aligner("droiteExt"); }
            });
            this.add(image);

        } catch (IOException ex) {
            System.out.println("Pas d'image :(");
        }

        //vide
        try {
            BufferedImage imageNoAlignement = ImageIO.read(new File("src/Ressources/non.png"));
            JButton image = new JButton(new ImageIcon(imageNoAlignement));
            image.setMargin(new Insets(0, 0, 0, 0));
            image.setLayout(null);
            image.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e1) { controller.aligner("rienHorizontal"); }
            });
            this.add(image);

        } catch (IOException ex) {
            System.out.println("Pas d'image :(");
        }

    }
}