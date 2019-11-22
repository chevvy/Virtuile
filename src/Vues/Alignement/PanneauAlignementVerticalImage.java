package Vues.Alignement;

import MVC.Controller;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class PanneauAlignementVerticalImage extends JPanel {
    private BufferedImage imageGauche01;

    public PanneauAlignementVerticalImage() {
        SetUpUi();
    }

    private void SetUpUi() {
        try {
            String currentDirectory = System.getProperty("user.dir");
            System.out.println("The current working directory is " + currentDirectory);
            BufferedImage imageGauche01 = ImageIO.read(new File("src/Ressources/gaucheExt.png"));
            JLabel picLabel = new JLabel(new ImageIcon(imageGauche01));
            picLabel.setBounds(25, 250, 40, 40);
            //this.add(picLabel);

        } catch (IOException ex) {
            System.out.println("Pas d'image :(");
        }
    }
}