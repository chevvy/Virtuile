package Vues;

import MVC.Controller;
import MVC.Etat;
import MVC.Observer;
import Vues.Alignement.FrameAlignement;
import Vues.Materiaux.FrameCouleur;
import Vues.Materiaux.FrameMateriau;
import Vues.Revetements.FrameRevetements;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame implements Observer {

    private JMenuBar menuBar;
    private JMenu menuFichier, menuEdition, menuVue;
    private JMenuItem menuItemSauvegarder, menuItemCharger, menuItemTailleGrilleMagnetique;
    private JCheckBoxMenuItem menuCheckboxMagnetiser;
    private PanneauConfiguration panelVueInfo;
    private Canvas panelVuePlan;
    private Controller controller;

    public MainWindow(Controller controller){
        controller.addObserver(this);
        this.controller = controller;
        this.setSize(500, 500);
        this.setLocation(100,100);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        this.setTitle("VirtuTuile");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        menuBar = new JMenuBar();
        menuFichier = new JMenu("Fichier");
        menuEdition = new JMenu("Edition");
        menuVue = new JMenu("Vue");
        menuItemSauvegarder = new JMenuItem("Sauvegarder");
        menuItemCharger = new JMenuItem("Charger");
        menuCheckboxMagnetiser = new JCheckBoxMenuItem("Grille Magnétique");
        menuCheckboxMagnetiser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.setGrilleMagnetiqueActive(menuCheckboxMagnetiser.getState());
            }
        });
        menuItemTailleGrilleMagnetique = new JMenuItem("Modifier la taille de la grille magnétique");
        menuItemTailleGrilleMagnetique.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    int initial = controller.getGridSize();
                    int size = Integer.parseInt(JOptionPane.showInputDialog("Entrez la taille de la grille magnétique désirée", initial));
                    controller.setGridSize(size);
                }catch(Exception ex){}
            }
        });

        menuFichier.add(menuItemSauvegarder);
        menuFichier.add(menuItemCharger);

        JMenuItem menuMateriaux = new JMenuItem("Propriétés des matériaux");
        menuEdition.add(menuMateriaux);
        menuMateriaux.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                new FrameRevetements(controller).setVisible(true);
            }
        });
        JMenuItem menuTypeMateriau = new JMenuItem("Ajouter un type de matériau");
        menuEdition.add(menuTypeMateriau);
        menuTypeMateriau.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                new FrameMateriau(controller).setVisible(true);
            }
        });
        JMenuItem menuCouleur = new JMenuItem("Ajouter une nouvelle couleur");
        menuEdition.add(menuCouleur);
        menuCouleur.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                new FrameCouleur(controller).setVisible(true);
            }
        });
        menuEdition.add(new JMenuItem("Ajouter une surface"));
        menuEdition.add(new JMenuItem("Modifier les sommets"));
        menuEdition.addSeparator();
        menuEdition.add(menuCheckboxMagnetiser);
        menuEdition.add(menuItemTailleGrilleMagnetique);
        menuBar.add(menuFichier);
        menuBar.add(menuEdition);
        menuBar.add(menuVue);
        this.setJMenuBar(menuBar);

        panelVuePlan = new Canvas(controller);
        panelVueInfo = new PanneauConfiguration(controller);


        this.add(panelVuePlan, BorderLayout.CENTER);
        this.add(panelVueInfo, BorderLayout.EAST);

        this.setVisible(true);
    }

    @Override
    public void update() {
        if( controller.getEtat() == Etat.OUVRIR_FENETRE_ALIGNER){
            new FrameAlignement(controller).setVisible(true);
            controller.setEtat(Etat.ALIGNER);
        }
    }
}
