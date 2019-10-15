package Vues;

import javafx.scene.control.RadioButton;

import javax.swing.*;
import java.awt.*;

public class PaneauConfiguration extends JScrollPane {

    private JButton boutonAjouter, boutonSupprimer;
    private JRadioButton radioSurface, radioVide;
    private JComboBox listeMateriau, listeAlignement;

    public PaneauConfiguration(){
        this.setBackground(Color.gray);

        this.setPreferredSize(new Dimension(250, 500));
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        this.setLayout(null);
        this.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        SetUpUi();
    }

    private void SetUpUi(){
        boutonAjouter = new JButton("Ajouter une Surface");
        boutonAjouter.setSize(200, 50);
        boutonAjouter.setLocation(25,20);

        boutonSupprimer = new JButton("Supprimer la surface");
        boutonSupprimer.setSize(200, 50);
        boutonSupprimer.setLocation(25,90);

        JLabel line = new JLabel("_________________________________");
        line.setSize(250,50);
        line.setLocation(10,150);

        radioSurface = new JRadioButton("Surface");
        radioSurface.setSelected(true);
        radioSurface.setBackground(Color.gray);
        radioSurface.setSize(100, 20);
        radioSurface.setLocation(15, 190);

        radioVide = new JRadioButton("Vide");
        radioVide.setBackground(Color.gray);
        radioVide.setSize(100, 20);
        radioVide.setLocation(135, 190);

        ButtonGroup group = new ButtonGroup();
        group.add(radioSurface);
        group.add(radioVide);

        JLabel labelMateriau = new JLabel("Materiau : ");
        labelMateriau.setSize(100, 20);
        labelMateriau.setLocation(15, 230);

        String [] materiaux = {"Brique", "Terre"};
        listeMateriau = new JComboBox(materiaux);
        listeMateriau.setSize(135, 30);
        listeMateriau.setLocation(100, 225);

        JLabel labelAlignement = new JLabel("Alignement : ");
        labelAlignement.setSize(100, 20);
        labelAlignement.setLocation(15, 270);

        String [] alignement = {"Aligné", "Pas aligné"};
        listeAlignement = new JComboBox(alignement);
        listeAlignement.setSize(135, 30);
        listeAlignement.setLocation(100, 265);

        this.add(boutonAjouter);
        this.add(boutonSupprimer);
        this.add(line);
        this.add(radioSurface);
        this.add(radioVide);
        this.add(labelMateriau);
        this.add(listeMateriau);
        this.add(labelAlignement);
        this.add(listeAlignement);
        this.setVisible(true);
    }

    private class PanelSommet extends JFrame{

        public PanelSommet(Point point){

        }
    }
}
