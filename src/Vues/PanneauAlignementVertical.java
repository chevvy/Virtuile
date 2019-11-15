package Vues;

import MVC.Controller;
import javax.swing.*;
import java.awt.image.BufferedImage;


public class PanneauAlignementVertical extends JPanel{
    private ButtonGroup buttonGroupVertical;
    private JRadioButton boutonGauche01, boutonGauche02, boutonCentreVertical, boutonDroite01, boutonDroite02, aucunAlligmentVertical;
    private Controller controller;
    private BufferedImage imageGauche01;
    public PanneauAlignementVertical(Controller controller) {
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
        buttonGroupVertical = new ButtonGroup();
        JRadioButton boutonGauche01 = new JRadioButton("Gauche - Extérieur");
        this.add(boutonGauche01);
        buttonGroupVertical.add(boutonGauche01);
        JRadioButton boutonGauche02 = new JRadioButton("Gauche - Intérieur");
        this.add(boutonGauche02);
        buttonGroupVertical.add(boutonGauche02);
        JRadioButton boutonCentreVertical = new JRadioButton(" Centré           ");
        this.add(boutonCentreVertical);
        buttonGroupVertical.add(boutonCentreVertical);
        JRadioButton boutonDroite01 = new JRadioButton("Droite - Intérieur");
        this.add(boutonDroite01);
        buttonGroupVertical.add(boutonDroite01);
        JRadioButton boutonDroite02 = new JRadioButton("Droite - Extérieur");
        this.add(boutonDroite02);
        buttonGroupVertical.add(boutonDroite02);
        JRadioButton aucunAlligmentVertical = new JRadioButton("Aucun allignement vertical  ");
        aucunAlligmentVertical.setSelected(true);
        this.add(aucunAlligmentVertical);
        buttonGroupVertical.add(aucunAlligmentVertical);
        this.setVisible(true);


    }
}
