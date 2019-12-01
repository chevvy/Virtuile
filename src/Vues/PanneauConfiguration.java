package Vues;

import Domaine.Surface;
import MVC.Controller;
import MVC.Etat;
import Vues.Revetements.FrameRevetements;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import MVC.Observer;


public class PanneauConfiguration extends JPanel implements Observer{

    private JButton boutonAjouter, boutonSupprimer, boutonMenuRevetement, boutonAlligment, boutonAjouterCouleurCoulis;
    private JRadioButton radioSurface, radioVide;
    private ActionListener selectRevetementAction, selectTailleCoulis;
    private JComboBox revetementSurfaceSelectionnee;
    private JTextField hauteurSurfaceText, largeurSurfaceText, typeMateriauText, couleurMateriauText, motifTuileText,
            hauteurTuileText, largeurTuileText, nbTuilesBoiteText, epaisseurCoulisText, hauteurTuileSelectText,
            largeurTuileSelectText, couleurCoulisText;
    private ButtonGroup groupeRadioSurface;
    Surface surfaceSelectionnee;

    private Controller controller;

    public PanneauConfiguration(Controller controller){
        controller.addObserver(this);
        this.setBackground(Color.gray);
        this.setPreferredSize(new Dimension(300, 800));
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        this.setLayout(null);
        this.controller = controller;
        SetUpUi();

        selectRevetementAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String s = (String) revetementSurfaceSelectionnee.getSelectedItem();
                controller.setRevetement(controller.gestionnaireRevetements.getRevetementFromNom(s));
            }
        };
        selectTailleCoulis = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                controller.setEpaisseurCoulis(Integer.parseInt(epaisseurCoulisText.getText()));
            }
        };
    }

    private void SetUpUi(){

        String uniteMesure = "cm";
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

        this.groupeRadioSurface = new ButtonGroup();
        groupeRadioSurface.add(radioSurface);
        groupeRadioSurface.add(radioVide);

        // Hauteur Surface
        JLabel labelHauteurSurface = new JLabel("Hauteur : ");
        labelHauteurSurface.setSize(100, 30);
        labelHauteurSurface.setLocation(15, 240);

        hauteurSurfaceText = new JTextField(10);
        hauteurSurfaceText.setBounds(100,240,90,30);
        hauteurSurfaceText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int vieilleLargeur = Integer.parseInt(controller.getInfosSurfaceSelect().get("Longueur surface"));
                controller.setDimensionsSurface(Integer.parseInt(hauteurSurfaceText.getText()), vieilleLargeur);
            }
        });
        this.add(hauteurSurfaceText);

        JLabel labelUniteMesureHauteurSurface = new JLabel(uniteMesure);
        labelUniteMesureHauteurSurface.setSize(70, 30);
        labelUniteMesureHauteurSurface.setLocation(200, 240);
        this.add(labelUniteMesureHauteurSurface);

        // Largeur Surface
        JLabel labelLargeurSurface = new JLabel("Largeur : ");
        labelLargeurSurface.setSize(70, 30);
        labelLargeurSurface.setLocation(15, 270);

        largeurSurfaceText = new JTextField(10);
        largeurSurfaceText.setBounds(100,270,90,30);
        largeurSurfaceText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int vieilleHauteur = Integer.parseInt(controller.getInfosSurfaceSelect().get("Hauteur surface"));
                controller.setDimensionsSurface(vieilleHauteur, Integer.parseInt(largeurSurfaceText.getText()));
            }
        });
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
        revetementSurfaceSelectionnee = new JComboBox(model.toArray());
        revetementSurfaceSelectionnee.setSelectedItem("Revêtement par défaut");
        revetementSurfaceSelectionnee.addActionListener(selectRevetementAction);
        revetementSurfaceSelectionnee.setSize(180, 30);
        revetementSurfaceSelectionnee.setLocation(100, 300);

        // Type Matériau
        JLabel labelTypeMateriau = new JLabel("Type : ");
        labelTypeMateriau.setSize(70, 30);
        labelTypeMateriau.setLocation(15, 330);

        typeMateriauText = new JTextField(20);
        typeMateriauText.setBounds(100,330,180,30);
        typeMateriauText.setEditable(false);
        this.add(typeMateriauText);

        // Couleur Matériau
        JLabel labelCouleurMateriau = new JLabel("Couleur : ");
        labelCouleurMateriau.setSize(70, 30);
        labelCouleurMateriau.setLocation(15, 360);

        couleurMateriauText = new JTextField(20);
        couleurMateriauText.setBounds(100,360,100,30);
        couleurMateriauText.setEditable(false);
        this.add(couleurMateriauText);

        // Motif Tuile
        JLabel labelMotif = new JLabel("Motif : ");
        labelMotif.setSize(70, 30);
        labelMotif.setLocation(15, 390);

        motifTuileText = new JTextField(20);
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

        hauteurTuileText = new JTextField(10);
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

        largeurTuileText = new JTextField(10);
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
        labelNbTuilesBoite.setLocation(15, 500);

        nbTuilesBoiteText = new JTextField(20);
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
        couleurCoulisLabel.setBounds(15,560,150,30);
        this.add(couleurCoulisLabel);

        couleurCoulisText = new JTextField(20);
        couleurCoulisText.setBounds(150,560,50,30);
        this.add(couleurCoulisText);

        boutonAjouterCouleurCoulis = new JButton("Couleur du matériau");
        boutonAjouterCouleurCoulis.setSize(100, 25);
        boutonAjouterCouleurCoulis.setLocation(205, 560);
        this.add(boutonAjouterCouleurCoulis);

        boutonAjouterCouleurCoulis.addActionListener(actionEvent -> {
            Color couleurCoulis = JColorChooser.showDialog(null, "Couleur du coulis", Color.RED);
            if (couleurCoulis != null){
                couleurCoulisText.setBackground(couleurCoulis);
                controller.setCouleurCoulis(couleurCoulis);
            }
        });

        //Epaisseur coulis
        JLabel epaisseurCoulisLabel = new JLabel("Épaisseur du coulis :");
        epaisseurCoulisLabel.setBounds(15,590,150,30);
        this.add(epaisseurCoulisLabel);

        epaisseurCoulisText = new JTextField(20);
        epaisseurCoulisText.setBounds(150,590,50,30);
        epaisseurCoulisText.addActionListener(selectTailleCoulis);
        this.add(epaisseurCoulisText);

        JLabel labelUniteMesureLCoulis = new JLabel(uniteMesure);
        labelUniteMesureLCoulis.setSize(60, 30);
        labelUniteMesureLCoulis.setLocation(205, 590);
        this.add(labelUniteMesureLCoulis);


        JLabel line02 = new JLabel("_______________________________________");
        line02.setBounds(15,610,300,20);
        this.add(line02);

        JLabel infosTuiles = new JLabel("Informations sur la tuile");
        infosTuiles.setBounds(15,640,300,20);
        this.add(infosTuiles);

        // Hauteur des tuiles
        JLabel labelHauteurTuileSelect = new JLabel("Hauteur : ");
        labelHauteurTuileSelect.setSize(70, 30);
        labelHauteurTuileSelect.setLocation(25, 670);
        this.add(labelHauteurTuileSelect);

        this.hauteurTuileSelectText = new JTextField(10);
        hauteurTuileSelectText.setBounds(100,670,90,30);
        hauteurTuileSelectText.setEditable(false);
        this.add(hauteurTuileSelectText);

        JLabel labelUniteMesureHauteurTuileSelect = new JLabel(uniteMesure);
        labelUniteMesureHauteurTuileSelect.setSize(70, 30);
        labelUniteMesureHauteurTuileSelect.setLocation(200, 670);
        this.add(labelUniteMesureHauteurTuileSelect);

        //Largeur des tuiles
        JLabel labelLargeurTuileSelect = new JLabel("Largeur : ");
        labelLargeurTuileSelect.setSize(70, 30);
        labelLargeurTuileSelect.setLocation(25, 700);
        this.add(labelLargeurTuileSelect);

        this.largeurTuileSelectText = new JTextField(10);
        largeurTuileSelectText.setBounds(100,700,90,30);
        largeurTuileSelectText.setEditable(false);
        this.add(largeurTuileSelectText);

        JLabel labelUniteMesureLargeurTuileSelect = new JLabel(uniteMesure);
        labelUniteMesureLargeurTuileSelect.setSize(70, 30);
        labelUniteMesureLargeurTuileSelect.setLocation(200, 700);
        this.add(labelUniteMesureLargeurTuileSelect);

        JCheckBox inspectionTuiles = new JCheckBox("Mode inspection des tuiles");
        inspectionTuiles.setBounds(25,725, 250,50);
        this.add(inspectionTuiles);

        //JCheckBox labelUt = new JCheckBox("Aire minimale des tuiles");
        //inspectionTuiles.setBounds(25,725, 250,50);
        //this.add(inspectionTuiles);

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

        this.surfaceSelectionnee = controller.getPlan().surfaceSelectionnee;


        ArrayList model = new ArrayList();
        controller.getNomRevetements().forEach(nom -> model.add(nom));
        revetementSurfaceSelectionnee.setModel(new DefaultComboBoxModel(model.toArray()));
        if(controller.plan.surfaceSelectionnee != null){
            if ( Boolean.parseBoolean(controller.getInfosSurfaceSelect().get("Est un trou"))){
                groupeRadioSurface.clearSelection();
                radioVide.setSelected(true);
            }
            else{
                groupeRadioSurface.clearSelection();
                radioSurface.setSelected(true);
            }
            hauteurSurfaceText.setText(controller.getInfosSurfaceSelect().get("Hauteur surface")+"");
            largeurSurfaceText.setText(controller.getInfosSurfaceSelect().get("Longueur surface")+"");
            revetementSurfaceSelectionnee.removeActionListener(selectRevetementAction);
            String nom = controller.getInfosRevetementSelect().get("Nom Revêtement");
            revetementSurfaceSelectionnee.setSelectedItem(nom);
            revetementSurfaceSelectionnee.addActionListener(selectRevetementAction);
            typeMateriauText.setText(controller.getInfosRevetementSelect().get("Type matériau")+"");
            couleurMateriauText.setBackground(new Color (Integer.parseInt(controller.getInfosRevetementSelect().get("Couleur tuiles")+"")));
            motifTuileText.setText(controller.getInfosRevetementSelect().get("Motif tuiles"));
            hauteurTuileText.setText(controller.getInfosRevetementSelect().get("Hauteur tuiles")+"");
            largeurTuileText.setText(controller.getInfosRevetementSelect().get("Longueur tuiles")+"");
            nbTuilesBoiteText.setText(controller.getInfosRevetementSelect().get("nb. tuiles par boite")+"");
            couleurCoulisText.setBackground(new Color (Integer.parseInt(controller.getInfosSurfaceSelect().get("Couleur coulis")+"")));
            epaisseurCoulisText.removeActionListener(selectTailleCoulis);
            epaisseurCoulisText.setText(controller.getInfosSurfaceSelect().get("Épaisseur coulis")+"");
            epaisseurCoulisText.addActionListener(selectTailleCoulis);
            hauteurTuileSelectText.setText(Integer.toString(controller.getHauteurTuile()));
            largeurTuileSelectText.setText(Integer.toString(controller.getLargeurTuile()));
        }
        else{
            hauteurSurfaceText.setText("");
            largeurSurfaceText.setText("");
            revetementSurfaceSelectionnee.removeActionListener(selectRevetementAction);
            revetementSurfaceSelectionnee.setSelectedItem("Revêtement par défaut");
            revetementSurfaceSelectionnee.addActionListener(selectRevetementAction);
            typeMateriauText.setText("");
            couleurMateriauText.setBackground(Color.WHITE);
            motifTuileText.setText("");
            hauteurTuileText.setText("");
            largeurTuileText.setText("");
            nbTuilesBoiteText.setText("");
            couleurCoulisText.setBackground(Color.WHITE);
            epaisseurCoulisText.setText("");
            hauteurTuileSelectText.setText("");
            largeurTuileSelectText.setText("");
        }

    }

}
