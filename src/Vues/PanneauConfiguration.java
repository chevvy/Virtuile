package Vues;

import MVC.Controller;
import MVC.Etat;
import Vues.Revetements.FrameRevetements;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Set;
import java.util.Arrays;

import MVC.Observer;


public class PanneauConfiguration extends JScrollPane implements Observer{

    private JButton boutonAjouter, boutonSupprimer, boutonMenuRevetement, boutonAlligment;
    private JRadioButton radioSurface, radioVide;
    private JComboBox listeAlignement, revetementSurfaceSelectionnee;
    private JTextField positionSouris;

    private Controller controller;

    public PanneauConfiguration(Controller controller){
        controller.addObserver(this);
        this.setBackground(Color.gray);
        this.setPreferredSize(new Dimension(300, 500));
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        this.setLayout(null);
        this.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        this.controller = controller;
        SetUpUi();

    }

    private void SetUpUi(){

        String uniteMesure = "mètres";
        boutonAjouter = new JButton("Ajouter une surface");
        boutonAjouter.setSize(200, 30);
        boutonAjouter.setLocation(50,20);
        boutonAjouter.addActionListener(e -> {
            controller.setTrou(false);
            setCreateShape();;
        });

        //ajouter option vide
        JButton boutonAjouterSurfaceVide = new JButton("Ajouter une surface vide");
        boutonAjouterSurfaceVide.setSize(200, 30);
        boutonAjouterSurfaceVide.setLocation(50,50);
        boutonAjouterSurfaceVide.addActionListener(e -> {
            controller.setTrou(true);
            setCreateShape();;
        });

        boutonSupprimer = new JButton("Supprimer la surface");
        boutonSupprimer.setSize(200, 30);
        boutonSupprimer.setLocation(50,80);
        boutonSupprimer.addActionListener(e -> controller.supprimerSurface());

        boutonAlligment = new JButton("Alligner/Coller");
        boutonAlligment.setSize(200, 30);
        boutonAlligment.setLocation(50,110);
        boutonAlligment.addActionListener(e -> controller.selectionnerAligner());

        JButton boutonTestFusionner;
        boutonTestFusionner = new JButton("Fusionner");
        boutonTestFusionner.setSize(200,30);
        boutonTestFusionner.setLocation(50, 140);
        boutonTestFusionner.addActionListener(e -> controller.setEtat(Etat.FUSIONNER));

        JLabel line = new JLabel("_______________________________________");
        line.setSize(300,25);
        line.setLocation(10,160);

        JLabel InfoSurface = new JLabel("Information sur la surface");
        InfoSurface.setSize(250,25);
        InfoSurface.setLocation(20,180);

        // Premier element (rangee 1)
        radioSurface = new JRadioButton("Surface");
        radioSurface.setSelected(true);
        radioSurface.setBackground(Color.gray);
        radioSurface.setSize(100, 20);
        radioSurface.setLocation(50, 210);

        // Premier element (range 2)
        radioVide = new JRadioButton("Vide");
        radioVide.setBackground(Color.gray);
        radioVide.setSize(100, 20);
        radioVide.setLocation(155, 210);

        ButtonGroup group = new ButtonGroup();
        group.add(radioSurface);
        group.add(radioVide);

        // Hauteur Surface
        JLabel labelHauteurSurface = new JLabel("Hauteur : ");
        labelHauteurSurface.setSize(100, 30);
        labelHauteurSurface.setLocation(15, 240);

        JTextField hauteurSurfaceText = new JTextField(10);
        hauteurSurfaceText.setBounds(100,240,90,30);
        hauteurSurfaceText.setEditable(false);
        this.add(hauteurSurfaceText);

        JLabel labelUniteMesureHauteurSurface = new JLabel(uniteMesure);
        labelUniteMesureHauteurSurface.setSize(70, 30);
        labelUniteMesureHauteurSurface.setLocation(200, 240);
        this.add(labelUniteMesureHauteurSurface);

        // Largeur Surface
        JLabel labelLargeurSurface = new JLabel("Largeur : ");
        labelLargeurSurface.setSize(70, 30);
        labelLargeurSurface.setLocation(15, 270);

        JTextField largeurSurfaceText = new JTextField(10);
        largeurSurfaceText.setBounds(100,270,90,30);
        largeurSurfaceText.setEditable(false);
        this.add(largeurSurfaceText);

        JLabel labelUniteMesureLargeurSurface = new JLabel(uniteMesure);
        labelUniteMesureLargeurSurface.setSize(70, 30);
        labelUniteMesureLargeurSurface.setLocation(200, 270);
        this.add(labelUniteMesureLargeurSurface);

        // Nom Revêtement
        JLabel labelMateriau = new JLabel("Matériau : ");
        labelMateriau.setSize(70, 30);
        labelMateriau.setLocation(15, 300);

        ArrayList model = new ArrayList();
        controller.getNomRevetements().forEach(nom -> model.add(nom));
        JComboBox revetementSurfaceSelectionnee = new JComboBox(model.toArray());
        revetementSurfaceSelectionnee.setSelectedItem("revetement par defaut");
        revetementSurfaceSelectionnee.setSize(180, 30);
        revetementSurfaceSelectionnee.setLocation(100, 300);

        // Type Matériau
        JLabel labelTypeMateriau = new JLabel("Type : ");
        labelTypeMateriau.setSize(70, 30);
        labelTypeMateriau.setLocation(15, 330);

        JTextField typeMateriauText = new JTextField(20);
        typeMateriauText.setBounds(100,330,180,30);
        typeMateriauText.setEditable(false);
        this.add(typeMateriauText);

        // Couleur Matériau
        JLabel labelCouleurMateriau = new JLabel("Couleur : ");
        labelCouleurMateriau.setSize(70, 30);
        labelCouleurMateriau.setLocation(15, 360);

        JTextField couleurMateriauText = new JTextField(20);
        couleurMateriauText.setBounds(100,360,180,30);
        couleurMateriauText.setEditable(false);
        this.add(couleurMateriauText);

        // Motif Tuile
        JLabel labelMotif = new JLabel("Motif : ");
        labelMotif.setSize(70, 30);
        labelMotif.setLocation(15, 390);

        JTextField motifTuileText = new JTextField(20);
        motifTuileText.setBounds(100,390,180,30);
        motifTuileText.setEditable(false);
        this.add(motifTuileText);

        // Taille des Tuiles
        JLabel labelTailleTuiles = new JLabel("Taille des tuiles ");
        labelTailleTuiles.setSize(200, 30);
        labelTailleTuiles.setLocation(15, 420);

        // Hauteur des tuiles
        JLabel labelHauteurTuiles = new JLabel("Hauteur : ");
        labelHauteurTuiles.setSize(70, 30);
        labelHauteurTuiles.setLocation(25, 445);

        JTextField hauteurTuileText = new JTextField(10);
        hauteurTuileText.setBounds(100,445,90,30);
        hauteurTuileText.setEditable(false);
        this.add(hauteurTuileText);

        JLabel labelUniteMesureHauteurTuile = new JLabel(uniteMesure);
        labelUniteMesureHauteurTuile.setSize(70, 30);
        labelUniteMesureHauteurTuile.setLocation(200, 445);
        this.add(labelUniteMesureHauteurTuile);

        //Largeur des tuiles
        JLabel labelLargeurTuiles = new JLabel("Largeur : ");
        labelLargeurTuiles.setSize(70, 30);
        labelLargeurTuiles.setLocation(25, 470);

        JTextField largeurTuileText = new JTextField(10);
        largeurTuileText.setBounds(100,470,90,30);
        largeurTuileText.setEditable(false);
        this.add(largeurTuileText);

        JLabel labelUniteMesureLargeurTuile = new JLabel(uniteMesure);
        labelUniteMesureLargeurTuile.setSize(70, 30);
        labelUniteMesureLargeurTuile.setLocation(200, 470);
        this.add(labelUniteMesureLargeurTuile);

        //Largeur des tuiles
        JLabel labelNbTuilesBoite = new JLabel("Nb. tuiles/boite : ");
        labelNbTuilesBoite.setSize(120, 30);
        labelNbTuilesBoite.setLocation(25, 500);

        JTextField nbTuilesBoiteText = new JTextField(20);
        nbTuilesBoiteText.setBounds(150,500,120,30);
        nbTuilesBoiteText.setEditable(false);
        this.add(nbTuilesBoiteText);

        // 3eme element
        boutonMenuRevetement = new JButton("Édition matériaux");
        boutonMenuRevetement.setSize(200, 25);
        boutonMenuRevetement.setLocation(50,535);
        boutonMenuRevetement.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {new FrameRevetements(controller).setVisible(true);}
        });


        //Couleur coulis
        JLabel couleurCoulisLabel = new JLabel("Couleur du coulis :");
        couleurCoulisLabel.setBounds(15,560,120,30);
        this.add(couleurCoulisLabel);

        String [] couleurCoulis = {"Rouge", "Blanc", "Gris"};
        JComboBox<String> couleurCoulisCombo = new JComboBox<>(couleurCoulis); //this.controller.getListeMateriaux()
        couleurCoulisCombo.setSize(130, 30);
        couleurCoulisCombo.setLocation(150, 560);
        this.add(couleurCoulisCombo);

        //Epaisseur coulis
        JLabel epaisseurCoulisLabel = new JLabel("Épaisseur du coulis :");
        epaisseurCoulisLabel.setBounds(15,590,150,30);
        this.add(epaisseurCoulisLabel);

        JTextField epaisseurCoulisText = new JTextField(20);
        epaisseurCoulisText.setBounds(150,590,50,30);
        this.add(epaisseurCoulisText);

        JLabel labelUniteMesureLCoulis = new JLabel(uniteMesure);
        labelUniteMesureLCoulis.setSize(60, 30);
        labelUniteMesureLCoulis.setLocation(205, 590);
        this.add(labelUniteMesureLCoulis);

        JButton modifierEpaisseurCoulis = new JButton("+");
        modifierEpaisseurCoulis.setSize(25, 25);
        modifierEpaisseurCoulis.setLocation(255,590);
        modifierEpaisseurCoulis.addActionListener(e -> {
            setCreateShape();;
        });
        this.add(modifierEpaisseurCoulis);





        this.add(boutonAjouter);
        this.add(boutonAjouterSurfaceVide);
        this.add(boutonSupprimer);
        this.add(line);
        this.add(InfoSurface);
        this.add(radioSurface);
        this.add(radioVide);
        this.add(labelHauteurSurface);
        this.add(labelLargeurSurface);
        this.add(labelMateriau);
        this.add(labelTypeMateriau);
        this.add(labelCouleurMateriau);
        this.add(labelMotif);
        this.add(labelHauteurSurface);
        this.add(labelLargeurSurface);
        this.add(labelTailleTuiles);
        this.add(labelHauteurTuiles);
        this.add(labelLargeurTuiles);
        this.add(labelNbTuilesBoite);
        this.add(revetementSurfaceSelectionnee);
        this.add(boutonMenuRevetement);
        this.add(boutonAlligment);
        this.add(boutonTestFusionner);
        this.setVisible(true);

    }

    private void setCreateShape(){
        Object[] options = { "Carré", "Triangle", "Forme libre", "Pentagramme"};
        String res = (String)JOptionPane.showInputDialog(null, "Forme pour la création", "Forme à créer",
                JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
        int i = -1;
        for (int j = 0; j < options.length; j++) {
            if (options[j] == (String)res){ i = j; }
        }
        controller.ajouterSurface(i);
    }

    @Override
    public void update() {
        if (controller.getPlan().surfaceSelectionnee != null )
        {
            if (controller.getEtat().equals(Etat.LECTURE)) {
                revetementSurfaceSelectionnee.setSelectedItem(controller.getInfosRevetementSelect().get("Nom Revêtement"));
            }
        }
//        if (controller.getPlan().surfaceSelectionnee != null){ TODO a remettre quand cath a fini panel config
//            int [] xpointsTuile = controller.getPlan().surfaceSelectionnee.getTuileAtPoint(controller.getPositionSourisActuelle()).getPolygone().xpoints;
//            int [] ypointsTuile = controller.getPlan().surfaceSelectionnee.getTuileAtPoint(controller.getPositionSourisActuelle()).getPolygone().xpoints;
//            positionSouris.setText(" Points de la tuile actuelle : x = " + Arrays.toString(xpointsTuile) + " y = " + Arrays.toString(ypointsTuile));
//        }

    }

    private class PanelSommet extends JFrame{

        public PanelSommet(Point point){

        }
    }
}
