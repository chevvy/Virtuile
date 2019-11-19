package Vues;

import MVC.Controller;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class PanneauAlignementHorizontalImage extends JPanel {
    private BufferedImage imageGauche01;

    public PanneauAlignementHorizontalImage() {
        SetUpUi();
    }

    private void SetUpUi() {
        try {
            BufferedImage imageGauche01 = ImageIO.read(new File("src/Ressources/gaucheExt.png"));
            JLabel picLabel = new JLabel(new ImageIcon(imageGauche01));
            picLabel.setBounds(25, 250, 50, 50);
            add(picLabel);


        } catch (IOException ex) {
            System.out.println("Pas d'image :(");
        }
    }
}