package Vues;

import Domaine.Surface;
import MVC.Controller;
import MVC.Etat;
import Vues.Revetements.FrameRevetements;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import MVC.Observer;


public class PanneauConfiguration extends JPanel implements Observer{

    private JButton boutonMenuRevetement, boutonAjouterCouleurCoulis;
    private JRadioButton radioSurface, radioVide;
    private ActionListener selectRevetementAction, selectTailleCoulis;
    private JComboBox revetementSurfaceSelectionnee;
    private JTextField hauteurSurfaceText, largeurSurfaceText, typeMateriauText, couleurMateriauText, motifTuileText,
            hauteurTuileText, largeurTuileText, nbTuilesBoiteText, epaisseurCoulisText, hauteurTuileSelectText,
            largeurTuileSelectText, couleurCoulisText;
    private ButtonGroup groupeRadioSurface;
    private JSpinner decallageSpinner;
    private JSlider angleDuMotif;
    Surface surfaceSelectionnee;

    private Controller controller;
    public MainWindow mainWindow;

    public PanneauConfiguration(Controller controller, MainWindow mainWindow){
        this.mainWindow = mainWindow;
        controller.addObserver(this);
        this.setBackground(Color.gray);
        this.setPreferredSize(new Dimension(300, 600));
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        this.setLayout(null);
        this.controller = controller;
        SetUpUi();

        selectRevetementAction = actionEvent -> {
            String s = (String) revetementSurfaceSelectionnee.getSelectedItem();
            controller.setRevetement(controller.gestionnaireRevetements.getRevetementFromNom(s));
        };
    }

    private void SetUpUi(){

        String uniteMesure = "cm";

        JLabel InfoSurface = new JLabel("Information sur la surface");
        InfoSurface.setSize(250,25);
        InfoSurface.setLocation(20,0);

        // Premier element (rangee 1)
        radioSurface = new JRadioButton("Surface");
        radioSurface.setSelected(true);
        radioSurface.setBackground(Color.gray);
        radioSurface.setSize(100, 20);
        radioSurface.setLocation(50, 30);

        // Premier element (range 2)
        radioVide = new JRadioButton("Vide");
        radioVide.setBackground(Color.gray);
        radioVide.setSize(100, 20);
        radioVide.setLocation(155, 30);

        this.groupeRadioSurface = new ButtonGroup();
        groupeRadioSurface.add(radioSurface);
        groupeRadioSurface.add(radioVide);

        // Hauteur Surface
        JLabel labelHauteurSurface = new JLabel("Hauteur : ");
        labelHauteurSurface.setSize(100, 30);
        labelHauteurSurface.setLocation(15, 60);

        hauteurSurfaceText = new JTextField(10);
        hauteurSurfaceText.setBounds(100,60,90,30);
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
        labelUniteMesureHauteurSurface.setLocation(200, 60);
        this.add(labelUniteMesureHauteurSurface);

        // Largeur Surface
        JLabel labelLargeurSurface = new JLabel("Largeur : ");
        labelLargeurSurface.setSize(70, 30);
        labelLargeurSurface.setLocation(15, 90);

        largeurSurfaceText = new JTextField(10);
        largeurSurfaceText.setBounds(100,90,90,30);
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
        labelUniteMesureLargeurSurface.setLocation(200, 90);
        this.add(labelUniteMesureLargeurSurface);

        // Nom Revêtement
        JLabel labelMateriau = new JLabel("Matériau : ");
        labelMateriau.setSize(70, 30);
        labelMateriau.setLocation(15, 120);

        ArrayList model = new ArrayList();
        controller.getNomRevetements().forEach(nom -> model.add(nom));
        revetementSurfaceSelectionnee = new JComboBox(model.toArray());
        revetementSurfaceSelectionnee.setSelectedItem("Revêtement par défaut");
        revetementSurfaceSelectionnee.addActionListener(selectRevetementAction);
        revetementSurfaceSelectionnee.setSize(180, 30);
        revetementSurfaceSelectionnee.setLocation(100, 120);

        // Type Matériau
        JLabel labelTypeMateriau = new JLabel("Type : ");
        labelTypeMateriau.setSize(70, 30);
        labelTypeMateriau.setLocation(15, 150);

        typeMateriauText = new JTextField(20);
        typeMateriauText.setBounds(100,150,180,30);
        typeMateriauText.setEditable(false);
        this.add(typeMateriauText);

        // Couleur Matériau
        JLabel labelCouleurMateriau = new JLabel("Couleur : ");
        labelCouleurMateriau.setSize(70, 30);
        labelCouleurMateriau.setLocation(15, 180);

        couleurMateriauText = new JTextField(20);
        couleurMateriauText.setBounds(100,180,100,30);
        couleurMateriauText.setEditable(false);
        this.add(couleurMateriauText);

        // Motif Tuile
        JLabel labelMotif = new JLabel("Motif : ");
        labelMotif.setSize(70, 30);
        labelMotif.setLocation(15, 210);

        motifTuileText = new JTextField(20);
        motifTuileText.setBounds(100,210,180,30);
        motifTuileText.setEditable(false);
        this.add(motifTuileText);

        // Taille des Tuiles
        JLabel labelTailleTuiles = new JLabel("Taille des tuiles ");
        labelTailleTuiles.setSize(200, 30);
        labelTailleTuiles.setLocation(15, 240);

        // Hauteur des tuiles
        JLabel labelHauteurTuiles = new JLabel("Hauteur : ");
        labelHauteurTuiles.setSize(70, 30);
        labelHauteurTuiles.setLocation(25, 265);

        hauteurTuileText = new JTextField(10);
        hauteurTuileText.setBounds(100,265,90,30);
        hauteurTuileText.setEditable(false);
        this.add(hauteurTuileText);

        JLabel labelUniteMesureHauteurTuile = new JLabel(uniteMesure);
        labelUniteMesureHauteurTuile.setSize(70, 30);
        labelUniteMesureHauteurTuile.setLocation(200, 265);
        this.add(labelUniteMesureHauteurTuile);

        //Largeur des tuiles
        JLabel labelLargeurTuiles = new JLabel("Largeur : ");
        labelLargeurTuiles.setSize(70, 30);
        labelLargeurTuiles.setLocation(25, 290);

        largeurTuileText = new JTextField(10);
        largeurTuileText.setBounds(100,290,90,30);
        largeurTuileText.setEditable(false);
        this.add(largeurTuileText);

        JLabel labelUniteMesureLargeurTuile = new JLabel(uniteMesure);
        labelUniteMesureLargeurTuile.setSize(70, 30);
        labelUniteMesureLargeurTuile.setLocation(200, 290);
        this.add(labelUniteMesureLargeurTuile);

        //Largeur des tuiles
        JLabel labelNbTuilesBoite = new JLabel("Nb. tuiles/boite : ");
        labelNbTuilesBoite.setSize(120, 30);
        labelNbTuilesBoite.setLocation(15, 320);

        nbTuilesBoiteText = new JTextField(20);
        nbTuilesBoiteText.setBounds(150,320,120,30);
        nbTuilesBoiteText.setEditable(false);
        this.add(nbTuilesBoiteText);

        // 3eme element
        boutonMenuRevetement = new JButton("Édition matériaux");
        boutonMenuRevetement.setSize(200, 25);
        boutonMenuRevetement.setLocation(50,355);
        boutonMenuRevetement.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                mainWindow.setEnabled(false);
                new FrameRevetements(controller).setVisible(true);
                mainWindow.setEnabled(true);}
        });


        //Couleur coulis
        JLabel couleurCoulisLabel = new JLabel("Couleur du coulis :");
        couleurCoulisLabel.setBounds(15,380,150,30);
        this.add(couleurCoulisLabel);

        couleurCoulisText = new JTextField(20);
        couleurCoulisText.setBounds(150,380,50,30);
        this.add(couleurCoulisText);

        boutonAjouterCouleurCoulis = new JButton("Modifier");
        boutonAjouterCouleurCoulis.setSize(80, 30);
        boutonAjouterCouleurCoulis.setLocation(200, 380);
        this.add(boutonAjouterCouleurCoulis);

        boutonAjouterCouleurCoulis.addActionListener(actionEvent -> {
            Color couleurCoulis = JColorChooser.showDialog(null, "Couleur du matériau",
                    couleurCoulisText.getBackground());
            if (couleurCoulis != null){
                couleurCoulisText.setBackground(couleurCoulis);
                controller.setCouleurCoulis(couleurCoulis);
            }
        });

        //Epaisseur coulis
        JLabel epaisseurCoulisLabel = new JLabel("Épaisseur du coulis :");
        epaisseurCoulisLabel.setBounds(15,410,150,30);
        this.add(epaisseurCoulisLabel);

        epaisseurCoulisText = new JTextField(20);
        epaisseurCoulisText.setBounds(150,410,50,30);
        epaisseurCoulisText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                controller.setEpaisseurCoulis(Integer.parseInt(epaisseurCoulisText.getText()));
            }
        });
        this.add(epaisseurCoulisText);

        JLabel labelUniteMesureLCoulis = new JLabel(uniteMesure);
        labelUniteMesureLCoulis.setSize(60, 30);
        labelUniteMesureLCoulis.setLocation(205, 410);
        this.add(labelUniteMesureLCoulis);

        JLabel labelDecallage = new JLabel("Décallage : ");
        labelDecallage.setSize(60, 30);
        labelDecallage.setLocation(15, 440);
        this.add(labelDecallage);

        decallageSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
        decallageSpinner.setBounds(150,440, 70,25);
        decallageSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if((int)decallageSpinner.getValue()!=0){
                    controller.setOffset((int) decallageSpinner.getValue());
                }
            }
        });
        this.add(decallageSpinner);



        JLabel line02 = new JLabel("_______________________________________");
        line02.setBounds(15,470,300,20);
        this.add(line02);

        JLabel infosTuiles = new JLabel("Informations sur la tuile");
        infosTuiles.setBounds(15,500,300,20);
        this.add(infosTuiles);

        // Hauteur des tuiles
        JLabel labelHauteurTuileSelect = new JLabel("Hauteur : ");
        labelHauteurTuileSelect.setSize(70, 30);
        labelHauteurTuileSelect.setLocation(25, 530);
        this.add(labelHauteurTuileSelect);

        this.hauteurTuileSelectText = new JTextField(10);
        hauteurTuileSelectText.setBounds(100,530,90,30);
        hauteurTuileSelectText.setEditable(false);
        this.add(hauteurTuileSelectText);

        JLabel labelUniteMesureHauteurTuileSelect = new JLabel(uniteMesure);
        labelUniteMesureHauteurTuileSelect.setSize(70, 30);
        labelUniteMesureHauteurTuileSelect.setLocation(200, 530);
        this.add(labelUniteMesureHauteurTuileSelect);

        //Largeur des tuiles
        JLabel labelLargeurTuileSelect = new JLabel("Largeur : ");
        labelLargeurTuileSelect.setSize(70, 30);
        labelLargeurTuileSelect.setLocation(25, 560);
        this.add(labelLargeurTuileSelect);

        this.largeurTuileSelectText = new JTextField(10);
        largeurTuileSelectText.setBounds(100,560,90,30);
        largeurTuileSelectText.setEditable(false);
        this.add(largeurTuileSelectText);

        JLabel labelUniteMesureLargeurTuileSelect = new JLabel(uniteMesure);
        labelUniteMesureLargeurTuileSelect.setSize(70, 30);
        labelUniteMesureLargeurTuileSelect.setLocation(200, 560);
        this.add(labelUniteMesureLargeurTuileSelect);

        //Mode inspection tuiles
        JCheckBox inspectionTuiles = new JCheckBox("Mode inspection des tuiles");
        inspectionTuiles.setBounds(25,590, 250,25);
        inspectionTuiles.addActionListener(actionEvent -> controller.setModeInspection(inspectionTuiles.isSelected()));
        this.add(inspectionTuiles);

        JLabel labelInspectionTuiles = new JLabel("Dimension minimale");
        labelInspectionTuiles.setSize(150, 30);
        labelInspectionTuiles.setLocation(15, 610);
        this.add(labelInspectionTuiles);

        JSpinner inspectionTuilesMin = new JSpinner(new SpinnerNumberModel(1, 0, 1000, 1));
        inspectionTuilesMin.setBounds(150,610, 70,25);
        inspectionTuilesMin.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                controller.setDimensionInspection((int) inspectionTuilesMin.getValue());
            }
        });

        JLabel labelOrientationMotif = new JLabel("Orientation motif");
        labelOrientationMotif.setSize(150, 30);
        labelOrientationMotif.setLocation(15, 645);
        this.add(labelOrientationMotif);


        this.angleDuMotif = new JSlider(JSlider.HORIZONTAL, 0, 180, 0);
        angleDuMotif.setSize(220, 45);
        angleDuMotif.setLocation(15, 670);
        angleDuMotif.setMajorTickSpacing(45);
        angleDuMotif.setMinorTickSpacing(5);
        angleDuMotif.setPaintTicks(true);
        angleDuMotif.setPaintLabels(true);
        angleDuMotif.setSnapToTicks(true);
        angleDuMotif.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent changeEvent) {
                JSlider source = (JSlider) changeEvent.getSource();
                {
                    if (!source.getValueIsAdjusting()){
                        double angleModifie = (double)source.getValue();
                        controller.setAngleMotifSurfaceSelectionnee(angleModifie);
                    }

                }

            }
        });

        this.add(inspectionTuilesMin);
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
        this.add(angleDuMotif);
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
            decallageSpinner.setValue(motifTuileText.getText().equals("Installation en décallé")?controller.getOffset():0);
            decallageSpinner.setEnabled(motifTuileText.getText().equals("Installation en décallé"));
            hauteurTuileSelectText.setText(Integer.toString(controller.getHauteurTuile()));
            largeurTuileSelectText.setText(Integer.toString(controller.getLargeurTuile()));
            angleDuMotif.setValue((int)controller.getAngleMotifSurfaceSelectionne());
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
            decallageSpinner.setValue(0);
            hauteurTuileSelectText.setText("");
            largeurTuileSelectText.setText("");
            this.angleDuMotif.setValue(0);

        }

    }

}
