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


public class PanneauAlignementVerticalImage extends JPanel {

    private Controller controller;

    public PanneauAlignementVerticalImage(Controller controller) {
        this.controller = controller;
        SetUpUi();
    }

    private void SetUpUi() {

        //hautExt
        try {
            BufferedImage imageHaut01 = ImageIO.read(new File("src/Ressources/hautExt.png"));
            JButton image = new JButton(new ImageIcon(imageHaut01));
            image.setMargin(new Insets(0, 0, 0, 0));
            image.setLayout(null);
            image.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e1) { controller.aligner("hautExt"); }
            });
            this.add(image);

        } catch (IOException ex) {
            System.out.println("Pas d'image :(");
        }

        //hautInt
        try {
            BufferedImage imageHaut02 = ImageIO.read(new File("src/Ressources/hautInt.png"));
            JButton image = new JButton(new ImageIcon(imageHaut02));
            image.setMargin(new Insets(0, 0, 0, 0));
            image.setLayout(null);
            image.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e1) { controller.aligner("hautInt"); }
            });
            this.add(image);

        } catch (IOException ex) {
            System.out.println("Pas d'image :(");
        }

        //centre vertical
        try {
            BufferedImage imageCentre = ImageIO.read(new File("src/Ressources/centreVertical.png"));
            JButton image = new JButton(new ImageIcon(imageCentre));
            image.setMargin(new Insets(0, 0, 0, 0));
            image.setLayout(null);
            image.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e1) { controller.aligner("centreVertical"); }
            });
            this.add(image);

        } catch (IOException ex) {
            System.out.println("Pas d'image :(");
        }

        //bas int
        try {
            BufferedImage imageBas01 = ImageIO.read(new File("src/Ressources/basInt.png"));
            JButton image = new JButton(new ImageIcon(imageBas01));
            image.setMargin(new Insets(0, 0, 0, 0));
            image.setLayout(null);
            image.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e1) { controller.aligner("basInt"); }
            });
            this.add(image);

        } catch (IOException ex) {
            System.out.println("Pas d'image :(");
        }

        //bas ext
        try {
            BufferedImage imageBas02 = ImageIO.read(new File("src/Ressources/basExt.png"));
            JButton image = new JButton(new ImageIcon(imageBas02));
            image.setMargin(new Insets(0, 0, 0, 0));
            image.setLayout(null);
            image.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e1) { controller.aligner("basExt"); }
            });
            this.add(image);

        } catch (IOException ex) {
            System.out.println("Pas d'image :(");
        }

        //pas d'alignement vertical
        //vide
        try {
            BufferedImage imageNoAlignement = ImageIO.read(new File("src/Ressources/non.png"));
            JButton image = new JButton(new ImageIcon(imageNoAlignement));
            image.setMargin(new Insets(0, 0, 0, 0));
            image.setLayout(null);
            image.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e1) { controller.aligner("rienVertical"); }
            });
            this.add(image);

        } catch (IOException ex) {
            System.out.println("Pas d'image :(");
        }
    }
}