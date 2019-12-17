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
            ImageIcon imageHaut01 = new ImageIcon(getClass().getResource("/Ressources/hautExt.png"));
            JButton image = new JButton(imageHaut01);
            image.setMargin(new Insets(0, 0, 0, 0));
            image.setLayout(null);
            image.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e1) { controller.aligner("hautExt"); }
            });
            this.add(image);

        } catch (Exception ex) {
            System.out.println("Pas d'image :(");
        }

        //hautInt
        try {
            ImageIcon imageHaut02 = new ImageIcon(getClass().getResource("/Ressources/hautInt.png"));
            JButton image = new JButton(imageHaut02);
            image.setMargin(new Insets(0, 0, 0, 0));
            image.setLayout(null);
            image.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e1) { controller.aligner("hautInt"); }
            });
            this.add(image);

        } catch (Exception ex) {
            System.out.println("Pas d'image :(");
        }

        //centre vertical
        try {
            ImageIcon imageCentre = new ImageIcon(getClass().getResource("/Ressources/centreVertical.png"));
            JButton image = new JButton(imageCentre);
            image.setMargin(new Insets(0, 0, 0, 0));
            image.setLayout(null);
            image.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e1) { controller.aligner("centreVertical"); }
            });
            this.add(image);

        } catch (Exception ex) {
            System.out.println("Pas d'image :(");
        }

        //bas int
        try {
            ImageIcon imageBas01 = new ImageIcon(getClass().getResource("/Ressources/basInt.png"));
            JButton image = new JButton(imageBas01);
            image.setMargin(new Insets(0, 0, 0, 0));
            image.setLayout(null);
            image.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e1) { controller.aligner("basInt"); }
            });
            this.add(image);

        } catch (Exception ex) {
            System.out.println("Pas d'image :(");
        }

        //bas ext
        try {
            ImageIcon imageBas02 = new ImageIcon(getClass().getResource("/Ressources/basExt.png"));
            JButton image = new JButton(imageBas02);
            image.setMargin(new Insets(0, 0, 0, 0));
            image.setLayout(null);
            image.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e1) { controller.aligner("basExt"); }
            });
            this.add(image);

        } catch (Exception ex) {
            System.out.println("Pas d'image :(");
        }

        //pas d'alignement vertical
        //vide
        try {
            ImageIcon imageNoAlignement = new ImageIcon(getClass().getResource("/Ressources/non.png"));
            JButton image = new JButton(imageNoAlignement);
            image.setMargin(new Insets(0, 0, 0, 0));
            image.setLayout(null);
            image.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e1) { controller.aligner("rienVertical"); }
            });
            this.add(image);

        } catch (Exception ex) {
            System.out.println("Pas d'image :(");
        }
    }
}