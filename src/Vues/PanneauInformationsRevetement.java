package Vues;

import MVC.Controller;

import javax.swing.*;
import java.awt.*;

public class PanneauInformationsRevetement extends JPanel{
    private JLabel nomRevetementLabel, typeMateriauLabel, couleurMateriauLabel, motifRecouvrementLabel, hauteurTuileLabel, largeurTuileLabel, nbTuilesBoiteLabel, couleurCoulisLabel, epaisseurCoulisLabel ;
    private JTextField nomRevetementField, hauteurTuileText, largeurTuileText, nbTuilesBoiteText, epaisseurCoulisText;
    private JComboBox TypeMateriauxCombo, couleurMateriauCombo, motifRecouvrementCombo, couleurCoulisCombo;
    private Controller controller;

    public PanneauInformationsRevetement(Controller controller) {
        SetUpUi();
        this.controller = controller;
        this.setPreferredSize(new Dimension(400, 500));
        this.setLayout(null);
    }

    private void SetUpUi() {
        //Nom du revêtement
        JLabel nomRevetementLabel = new JLabel("Nom du revêtement :");
        nomRevetementLabel.setBounds(10,10,150,25);
        this.add(nomRevetementLabel);

        JTextField nomRevetementField = new JTextField(20);
        nomRevetementField.setBounds(170,10,165,25);
        this.add(nomRevetementField);

        //Type matériaux
        JLabel typeMateriauLabel = new JLabel("Type de matériau :");
        typeMateriauLabel.setBounds(10,50,150,25);
        this.add(typeMateriauLabel);

        String [] materiaux = {"Céramique", "Composite"};
        JComboBox TypeMateriauxCombo = new JComboBox(materiaux); //this.controller.getListeMateriaux()
        TypeMateriauxCombo.setSize(150, 25);
        TypeMateriauxCombo.setLocation(170, 50);
        this.add(TypeMateriauxCombo);

        //Couleur matériaux
        JLabel couleurMateriauLabel = new JLabel("Couleur du matériau :");
        couleurMateriauLabel.setBounds(10,90,150,25);
        this.add(couleurMateriauLabel);

        String [] couleurMateriaux = {"Gris", "Beige", "Blanc", "Noir"};
        JComboBox couleurMateriauCombo = new JComboBox(couleurMateriaux); //this.controller.getListeMateriaux()
        couleurMateriauCombo.setSize(150, 25);
        couleurMateriauCombo.setLocation(170, 90);
        this.add(couleurMateriauCombo);

        //Motif recouvrement
        JLabel motifRecouvrementLabel = new JLabel("Motif du recouvrement :");
        motifRecouvrementLabel.setBounds(10,130,150,25);
        this.add(motifRecouvrementLabel);

        String [] motifRecouvrement = {"Installation droite", "Installation imitation parquet", "Installation en décallé", "Installation en chevron", "Installation en L"};
        JComboBox motifRecouvrementCombo = new JComboBox(motifRecouvrement); //this.controller.getListeMateriaux()
        motifRecouvrementCombo.setSize(170, 25);
        motifRecouvrementCombo.setLocation(170, 130);
        this.add(motifRecouvrementCombo);

        //Taille tuiles
        JLabel tailleTuileLabel = new JLabel("Taille des tuiles : ");
        tailleTuileLabel.setBounds(10,170,150,25);
        this.add(tailleTuileLabel);

        JLabel hauteurTuileLabel = new JLabel("Hauteur des tuiles :");
        hauteurTuileLabel.setBounds(40,200,150,25);
        this.add(hauteurTuileLabel);

        JTextField hauteurTuileText = new JTextField(20);
        hauteurTuileText.setBounds(200,200,100,25);
        this.add(hauteurTuileText);

        JLabel largeurTuileLabel = new JLabel("Largeur des tuiles :");
        largeurTuileLabel.setBounds(40,230,150,25);
        this.add(largeurTuileLabel);

        JTextField largeurTuileText = new JTextField(20);
        largeurTuileText.setBounds(200,230,100,25);
        this.add(largeurTuileText);

        //Nombre de tuiles par boîte
        JLabel nbTuilesBoiteLabel = new JLabel("Nombre de tuiles par boîte :");
        nbTuilesBoiteLabel.setBounds(10,260,150,25);
        this.add(nbTuilesBoiteLabel);

        JTextField nbTuilesBoiteText = new JTextField(20);
        nbTuilesBoiteText.setBounds(170,260,100,25);
        this.add(nbTuilesBoiteText);

        JLabel tuilesParBoite = new JLabel("Tuiles/Boîte");
        tuilesParBoite.setBounds(280,260,100,25);
        this.add(tuilesParBoite);


        //Couleur coulis
        JLabel couleurCoulisLabel = new JLabel("Couleur du coulis :");
        couleurCoulisLabel.setBounds(10,290,150,25);
        this.add(couleurCoulisLabel);

        String [] couleurCoulis = {"Rouge", "Blanc", "Gris"};
        JComboBox couleurCoulisCombo = new JComboBox(couleurCoulis); //this.controller.getListeMateriaux()
        couleurCoulisCombo.setSize(170, 25);
        couleurCoulisCombo.setLocation(170, 290);
        this.add(couleurCoulisCombo);

        //Epaisseur coulis
        JLabel epaisseurCoulisLabel = new JLabel("Épaisseur du coulis :");
        epaisseurCoulisLabel.setBounds(10,320,150,25);
        this.add(epaisseurCoulisLabel);

        JTextField epaisseurCoulisText = new JTextField(20);
        epaisseurCoulisText.setBounds(170,320,165,25);
        this.add(epaisseurCoulisText);
    }
}