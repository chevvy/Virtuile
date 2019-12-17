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
            ImageIcon imageGauche01 = new ImageIcon(getClass().getResource("/Ressources/gaucheExt.png"));
            JButton image = new JButton(imageGauche01);
            image.setMargin(new Insets(0, 0, 0, 0));
            image.setLayout(null);
            image.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e1) { controller.aligner("gaucheExt"); }
            });
            this.add(image);


        } catch (Exception ex) {
            System.out.println("Pas d'image :(");
        }

        //gaucheInt
        try {
            ImageIcon imageGauche02 = new ImageIcon(getClass().getResource("/Ressources/gaucheInt.png"));
            JButton image = new JButton(imageGauche02);
            image.setMargin(new Insets(0, 0, 0, 0));
            image.setLayout(null);
            image.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e1) { controller.aligner("gaucheInt"); }
            });
            this.add(image);

        } catch (Exception ex) {
            System.out.println("Pas d'image :(");
        }

        //centreHorizontal
        try {
            ImageIcon imageCentre = new ImageIcon(getClass().getResource("/Ressources/centreHorizontal.png"));
            JButton image = new JButton(imageCentre);
            image.setMargin(new Insets(0, 0, 0, 0));
            image.setLayout(null);
            image.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e1) { controller.aligner("centreHorizontal"); }
            });
            this.add(image);

        } catch (Exception ex) {
            System.out.println("Pas d'image :(");
        }

        //droiteInt
        try {
            ImageIcon imageDroite01 = new ImageIcon(getClass().getResource("/Ressources/droiteInt.png"));
            JButton image = new JButton(imageDroite01);
            image.setMargin(new Insets(0, 0, 0, 0));
            image.setLayout(null);
            image.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e1) { controller.aligner("droiteInt"); }
            });
            this.add(image);

        } catch (Exception ex) {
            System.out.println("Pas d'image :(");
        }

        //droiteExt
        try {
            ImageIcon imagDroite02 = new ImageIcon(getClass().getResource("/Ressources/droiteExt.png"));
            JButton image = new JButton(imagDroite02);
            image.setMargin(new Insets(0, 0, 0, 0));
            image.setLayout(null);
            image.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e1) { controller.aligner("droiteExt"); }
            });
            this.add(image);

        } catch (Exception ex) {
            System.out.println("Pas d'image :(");
        }

        //vide
        try {
            ImageIcon imageNoAlignement = new ImageIcon(getClass().getResource("/Ressources/non.png"));
            JButton image = new JButton(imageNoAlignement);
            image.setMargin(new Insets(0, 0, 0, 0));
            image.setLayout(null);
            image.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e1) { controller.aligner("rienHorizontal"); }
            });
            this.add(image);

        } catch (Exception ex) {
            System.out.println("Pas d'image :(");
        }

    }
}