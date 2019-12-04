package Vues.Revetements;

import Domaine.Revetement;
import MVC.Controller;
import MVC.Observer;
import Vues.Materiaux.FrameMateriau;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PanneauInformationsRevetement extends JPanel implements Observer {

    private Controller controller;
    private FrameRevetements frame;
    private JTextField nomRevetementField, hauteurTuileText, largeurTuileText, nbTuilesBoiteText, couleurMateriauText,
            nbBoiteText, nbTuilesText;
    public String nomRevetementSelectionnee;
    private JComboBox typeMateriauxCombo, motifRecouvrementCombo;
    private JButton boutonAjouterCouleur;

    PanneauInformationsRevetement(Controller controller, FrameRevetements frame) {
        controller.addObserver(this);
        this.controller = controller;
        this.frame = frame;
        SetUpUi();
        this.setPreferredSize(new Dimension(480, 500));
        this.setLayout(null);

    }


    private void SetUpUi() {
        String uniteMesure = "cm.";
        //Nom du revêtement
        JLabel nomRevetementLabel = new JLabel("Nom du matériau :");
        nomRevetementLabel.setBounds(10,10,200,25);
        this.add(nomRevetementLabel);

        this.nomRevetementField = new JTextField(20);
        nomRevetementField.setBounds(220,10,200,25);
        this.add(nomRevetementField);

        //Type matériaux
        JLabel typeMateriauLabel = new JLabel("Type de matériau :");
        typeMateriauLabel.setBounds(10,50,200,25);
        this.add(typeMateriauLabel);

        ArrayList<String> listeMateriaux = controller.getTypeMateriaux();
        this.typeMateriauxCombo = new JComboBox<>(listeMateriaux.toArray());
        typeMateriauxCombo.setSize(130, 25);
        typeMateriauxCombo.setLocation(220, 50);
        this.add(typeMateriauxCombo);

        JButton boutonAjouterMateriau = new JButton("Ajouter");
        boutonAjouterMateriau.setSize(100, 25);
        boutonAjouterMateriau.setLocation(355, 50);
        this.add(boutonAjouterMateriau);

        boutonAjouterMateriau.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new FrameMateriau(controller).setVisible(true);
                frame.dispose();
            }
        });

        //Couleur matériaux
        JLabel couleurMateriauLabel = new JLabel("Couleur du matériau :");
        couleurMateriauLabel.setBounds(10,90,150,25);
        this.add(couleurMateriauLabel);

        couleurMateriauText = new JTextField(20);
        couleurMateriauText.setBounds(220 ,90,130,25);
        this.add(couleurMateriauText);

        boutonAjouterCouleur = new JButton("Modifier");
        boutonAjouterCouleur.setSize(100, 25);
        boutonAjouterCouleur.setLocation(355, 90);
        this.add(boutonAjouterCouleur);

        boutonAjouterCouleur.addActionListener(actionEvent -> {
            Color couleurTuile = JColorChooser.showDialog(null, "Couleur du matériau", Color.RED);
            if (couleurTuile != null){
                couleurMateriauText.setBackground(couleurTuile);
            }
        });

        //Motif recouvrement
        JLabel motifRecouvrementLabel = new JLabel("Motif du recouvrement :");
        motifRecouvrementLabel.setBounds(10,130,200,25);
        this.add(motifRecouvrementLabel);

        ArrayList<String> listeMotif = controller.getMotifs();
        this.motifRecouvrementCombo = new JComboBox<>(listeMotif.toArray());
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

        this.hauteurTuileText = new JTextField(20);
        hauteurTuileText.setBounds(220,200,100,25);
        this.add(hauteurTuileText);

        JLabel hauteurTuileUniteMesure = new JLabel(uniteMesure);
        hauteurTuileUniteMesure.setBounds(330,200,100,25);
        this.add(hauteurTuileUniteMesure);

        //Largeur
        JLabel largeurTuileLabel = new JLabel("Largeur des tuiles :");
        largeurTuileLabel.setBounds(40,230,170,25);
        this.add(largeurTuileLabel);

        this.largeurTuileText = new JTextField(20);
        largeurTuileText.setBounds(220,230,100,25);
        this.add(largeurTuileText);

        JLabel largeurTuileUniteMesure = new JLabel(uniteMesure);
        largeurTuileUniteMesure.setBounds(330,230,100,25);
        this.add(largeurTuileUniteMesure);

        //Nombre de tuiles par boîte
        JLabel nbTuilesBoiteLabel = new JLabel("Nombre de tuiles par boîte :");
        nbTuilesBoiteLabel.setBounds(10,270,200 ,25);
        this.add(nbTuilesBoiteLabel);

        this.nbTuilesBoiteText = new JTextField(20);
        nbTuilesBoiteText.setBounds(220,270,100,25);
        this.add(nbTuilesBoiteText);

        JLabel tuilesParBoite = new JLabel("Tuiles/Boîte");
        tuilesParBoite.setBounds(330,270,100,25);
        this.add(tuilesParBoite);

        //Nombre de tuiles
        JLabel nbTuilesLabel = new JLabel("Nombre de tuiles :");
        nbTuilesLabel.setBounds(10,310,200 ,25);
        this.add(nbTuilesLabel);

        this.nbTuilesText = new JTextField(20);
        nbTuilesText.setBounds(220,310,100,25);
        this.add(nbTuilesText);

        JLabel tuilesLabel = new JLabel("Tuiles");
        tuilesLabel.setBounds(330,310,100,25);
        this.add(tuilesLabel);



        //Nombre de boites necessaires
        JLabel nbBoiteLabel = new JLabel("Nombre de boites nécessaires:");
        nbBoiteLabel.setBounds(10,350,200 ,25);
        this.add(nbBoiteLabel);

        this.nbBoiteText = new JTextField(20);
        nbBoiteText.setBounds(220,350,100,25);
        nbBoiteText.setEditable(false);
        this.add(nbBoiteText);

        JLabel boitesLabel = new JLabel("Boîtes");
        boitesLabel.setBounds(330,350,100,25);
        this.add(boitesLabel);


        JButton boutonAjouter = new JButton("Ajouter un nouveau matériau");
        boutonAjouter.setSize(300, 30);
        boutonAjouter.setLocation(100, 390);
        this.add(boutonAjouter);
        boutonAjouter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String nomRevetement = nomRevetementField.getText();
                String typeMateriauTuile = String.valueOf(typeMateriauxCombo.getSelectedItem());
                Color couleurTuile = couleurMateriauText.getBackground();
                String motifTuile = String.valueOf(motifRecouvrementCombo.getSelectedItem());
                int hauteurTuile = Integer.parseInt(hauteurTuileText.getText());
                int longueurTuile = Integer.parseInt(largeurTuileText.getText());
                int nbTuilesBoite = Integer.parseInt(nbTuilesBoiteText.getText());
                controller.ajouterRevetement(nomRevetement, typeMateriauTuile, couleurTuile,
                        motifTuile, hauteurTuile, longueurTuile, nbTuilesBoite);
                frame.dispose();
                new FrameRevetements(controller).setVisible(true);
            }
        });

        this.setVisible(true);

    }

    @Override
    public void update() {

        this.nomRevetementSelectionnee = controller.gestionnaireRevetements.getRevetementSelectionnee();
        Revetement revetementSelectionnee = controller.gestionnaireRevetements.getMapRevetements().get(this.nomRevetementSelectionnee);

        if (controller != null && revetementSelectionnee != null){
            this.nomRevetementField.setText(revetementSelectionnee.getNomDuRevetement());

            String typeMateriau = revetementSelectionnee.getTypeMateriauTuile();
            int selectionComboMateriau = controller.gestionnaireRevetements.getPositionDansArray(controller.getTypeMateriaux(), typeMateriau);
            if(controller.getTypeMateriaux().size() != typeMateriauxCombo.getItemCount()){
                this.typeMateriauxCombo = new JComboBox<>(controller.getTypeMateriaux().toArray());
                this.add(typeMateriauxCombo);
            }
            this.typeMateriauxCombo.setSelectedIndex(selectionComboMateriau);

            Color couleurMateriau = revetementSelectionnee.getCouleurTuile();
            this.couleurMateriauText.setBackground(couleurMateriau);

            String motifRecouvrement = revetementSelectionnee.getMotifTuiles();
            int selectionComboMotif = controller.gestionnaireRevetements.getPositionDansArray(controller.getMotifs(), motifRecouvrement);
            this.motifRecouvrementCombo.setSelectedIndex(selectionComboMotif);

            this.hauteurTuileText.setText(String.valueOf(revetementSelectionnee.getHauteurTuile()));
            this.largeurTuileText.setText(String.valueOf(revetementSelectionnee.getLongueurTuile()));
            this.nbTuilesBoiteText.setText(String.valueOf(revetementSelectionnee.getNbTuilesBoite()));
            this.nbBoiteText.setText(String.valueOf(controller.getNbBoites().get(nomRevetementSelectionnee)));
            this.nbTuilesText.setText(String.valueOf(controller.getNbTuilesTotal().get(nomRevetementSelectionnee)));


        }






    }
}



/*
(String nomDuRevetement, String typeMateriauTuile, Color couleurTuile, String couleurTuileText, String motifTuile,
                      int hauteurTuile, int longueurTuile, int nbTuilesBoite)
*/