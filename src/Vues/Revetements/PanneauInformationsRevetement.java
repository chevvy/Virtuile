package Vues.Revetements;

import MVC.Controller;
import Vues.Materiaux.FrameCouleur;
import Vues.Materiaux.FrameMateriau;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PanneauInformationsRevetement extends JPanel{


    private Controller controller;

    public PanneauInformationsRevetement(Controller controller) {


        this.controller = controller;
        this.setPreferredSize(new Dimension(480, 500));
        this.setLayout(null);
        SetUpUi();

    }

    private void SetUpUi() {
        //Nom du revêtement
        JLabel nomRevetementLabel = new JLabel("Nom du revêtement :");
        nomRevetementLabel.setBounds(10,10,200,25);
        this.add(nomRevetementLabel);

        JTextField nomRevetementField = new JTextField(20);
        nomRevetementField.setBounds(220,10,200,25);
        this.add(nomRevetementField);

        //Type matériaux
        JLabel typeMateriauLabel = new JLabel("Type de matériau :");
        typeMateriauLabel.setBounds(10,50,200,25);
        this.add(typeMateriauLabel);

        String [] materiaux = {"Céramique", "Composite"};
        JComboBox<String> TypeMateriauxCombo = new JComboBox<>(materiaux); //this.controller.getListeMateriaux()
        TypeMateriauxCombo.setSize(130, 25);
        TypeMateriauxCombo.setLocation(220, 50);
        this.add(TypeMateriauxCombo);

        JButton boutonAjouterMateriau = new JButton("Ajouter");
        boutonAjouterMateriau.setSize(100, 25);
        boutonAjouterMateriau.setLocation(355, 50);
        this.add(boutonAjouterMateriau);

        boutonAjouterMateriau.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {new FrameMateriau(controller).setVisible(true);}
        });

        //Couleur matériaux
        JLabel couleurMateriauLabel = new JLabel("Couleur du matériau :");
        couleurMateriauLabel.setBounds(10,90,200,25);
        this.add(couleurMateriauLabel);

        ArrayList<String> listeCouleurs = controller.getCouleurs();
        JComboBox couleurMateriauCombo = new JComboBox<>(listeCouleurs.toArray());
        couleurMateriauCombo.setSize(130, 25);
        couleurMateriauCombo.setLocation(220, 90);
        this.add(couleurMateriauCombo);

        JButton boutonAjouterCouleur = new JButton("Ajouter");
        boutonAjouterCouleur.setSize(100, 25);
        boutonAjouterCouleur.setLocation(355, 90);
        this.add(boutonAjouterCouleur);

        boutonAjouterCouleur.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {new FrameCouleur(controller).setVisible(true);}
        });

        //Motif recouvrement
        JLabel motifRecouvrementLabel = new JLabel("Motif du recouvrement :");
        motifRecouvrementLabel.setBounds(10,130,200,25);
        this.add(motifRecouvrementLabel);

        String [] motifRecouvrement = {"Installation droite", "Installation imitation parquet", "Installation en décallé", "Installation en chevron", "Installation en L"};
        JComboBox<String> motifRecouvrementCombo = new JComboBox<>(motifRecouvrement); //this.controller.getListeMateriaux()
        motifRecouvrementCombo.setSize(200, 25);
        motifRecouvrementCombo.setLocation(220, 130);
        this.add(motifRecouvrementCombo);

        //Taille tuiles
        JLabel tailleTuileLabel = new JLabel("Taille des tuiles : ");
        tailleTuileLabel.setBounds(10,170,200,25);
        this.add(tailleTuileLabel);

        //Hauteur
        JLabel hauteurTuileLabel = new JLabel("Hauteur des tuiles :");
        hauteurTuileLabel.setBounds(40,200,170,25);
        this.add(hauteurTuileLabel);

        JTextField hauteurTuileText = new JTextField(20);
        hauteurTuileText.setBounds(220,200,150,25);
        this.add(hauteurTuileText);

        //Largeur
        JLabel largeurTuileLabel = new JLabel("Largeur des tuiles :");
        largeurTuileLabel.setBounds(40,230,170,25);
        this.add(largeurTuileLabel);

        JTextField largeurTuileText = new JTextField(20);
        largeurTuileText.setBounds(220,230,150,25);
        this.add(largeurTuileText);

        //Nombre de tuiles par boîte
        JLabel nbTuilesBoiteLabel = new JLabel("Nombre de tuiles par boîte :");
        nbTuilesBoiteLabel.setBounds(10,270,200 ,25);
        this.add(nbTuilesBoiteLabel);

        JTextField nbTuilesBoiteText = new JTextField(20);
        nbTuilesBoiteText.setBounds(220,270,100,25);
        this.add(nbTuilesBoiteText);

        JLabel tuilesParBoite = new JLabel("Tuiles/Boîte");
        tuilesParBoite.setBounds(330,270,100,25);
        this.add(tuilesParBoite);


        //Couleur coulis
        JLabel couleurCoulisLabel = new JLabel("Couleur du coulis :");
        couleurCoulisLabel.setBounds(10,310,200,25);
        this.add(couleurCoulisLabel);

        String [] couleurCoulis = {"Rouge", "Blanc", "Gris"};
        JComboBox<String> couleurCoulisCombo = new JComboBox<>(couleurCoulis); //this.controller.getListeMateriaux()
        couleurCoulisCombo.setSize(200, 25);
        couleurCoulisCombo.setLocation(220, 310);
        this.add(couleurCoulisCombo);

        //Epaisseur coulis
        JLabel epaisseurCoulisLabel = new JLabel("Épaisseur du coulis :");
        epaisseurCoulisLabel.setBounds(10,350,200,25);
        this.add(epaisseurCoulisLabel);

        JTextField epaisseurCoulisText = new JTextField(20);
        epaisseurCoulisText.setBounds(220,350,200,25);
        this.add(epaisseurCoulisText);

        JButton boutonModifier = new JButton("Modifier les propriétés du revêtement");
        boutonModifier.setSize(300, 30);
        boutonModifier.setLocation(100, 390);
        this.add(boutonModifier);

        JButton boutonAjouter = new JButton("Ajouter un nouveau revêtement");
        boutonAjouter.setSize(300, 30);
        boutonAjouter.setLocation(100, 420);
        this.add(boutonAjouter);
        this.setVisible(true);
    }
}