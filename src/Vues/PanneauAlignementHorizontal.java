package Vues;

import MVC.Controller;
import javax.swing.*;
import java.awt.image.BufferedImage;

public class PanneauAlignementHorizontal extends JPanel{
    private ButtonGroup buttonGroupHorizontal;
    private JRadioButton boutonHaut01, boutonHaut02, boutonCentreHorizontal, boutonBas01, boutonBas02, aucunAlligmentHorizontal;
    private Controller controller;
    private BufferedImage imageGauche01;
    public PanneauAlignementHorizontal(Controller controller) {
        SetUpUi();
    }
    private void SetUpUi () {
       /* try {
            String currentDirectory = System.getProperty("user.dir");
            System.out.println("The current working directory is " + currentDirectory);
            BufferedImage imageGauche01 = ImageIO.read(new File("src/Ressources/gaucheExt.png"));
            JLabel picLabel = new JLabel(new ImageIcon(imageGauche01));
            picLabel.setBounds(25, 250, 50, 50);
            add(picLabel);


        } catch (IOException ex) {
            System.out.println("Pas d'image :(");
        }*/
        //Groupe Vertical
        //Groupe Horizontal
        buttonGroupHorizontal = new ButtonGroup();
        JRadioButton boutonHaut01 = new JRadioButton("Haut - Extérieur  ");
        this.add(boutonHaut01);
        buttonGroupHorizontal.add(boutonHaut01);
        JRadioButton boutonHaut02 = new JRadioButton("Haut - Intérieur  ");
        this.add(boutonHaut02);
        buttonGroupHorizontal.add(boutonHaut02);
        JRadioButton boutonCentreHorizontal = new JRadioButton("Centré            ");
        this.add(boutonCentreHorizontal);
        buttonGroupHorizontal.add(boutonCentreHorizontal);
        JRadioButton boutonBas01 = new JRadioButton("Bas - Intérieur   ");
        this.add(boutonBas01);
        buttonGroupHorizontal.add(boutonBas01);
        JRadioButton boutonBas02 = new JRadioButton("Bas - Extérieur   ");
        this.add(boutonBas02);
        buttonGroupHorizontal.add(boutonBas02);
        JRadioButton aucunAlligmentHorizontal = new JRadioButton("Aucun allignement horizontal");
        aucunAlligmentHorizontal.setSelected(true);
        this.add(aucunAlligmentHorizontal);
        buttonGroupHorizontal.add(aucunAlligmentHorizontal);

        this.setVisible(true);


    }
}