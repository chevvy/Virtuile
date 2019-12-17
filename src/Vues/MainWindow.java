package Vues;

import MVC.Controller;
import MVC.Etat;
import MVC.Observer;
import Vues.Alignement.FrameAlignement;
import Vues.Espacement.FrameEspacement;
import Vues.Materiaux.FrameMateriau;
import Vues.Revetements.FrameRevetements;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class MainWindow extends JFrame implements Observer {

    private JMenuBar menuBar;
    private JMenu menuFichier, menuEdition;
    private JMenuItem menuItemSauvegarder, menuItemCharger, menuItemTailleGrilleMagnetique;
    private JCheckBoxMenuItem menuCheckboxMagnetiser, menuCheckboxImperial;
    private PanneauConfiguration panelVueInfo;
    private Canvas panelVuePlan;
    private PanneauActions panneauActions;
    private Controller controller;
    public MainWindow mainWindow;
    private JFileChooser fileChooser;
    private MainWindow ref;

    public MainWindow(Controller controller){
        mainWindow = this;
        ref = this;
        controller.addObserver(this);
        this.controller = controller;
        this.setSize(500, 500);
        this.setLocation(100,100);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        this.setTitle("VirtuTuile");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        fileChooser = new JFileChooser();

        menuBar = new JMenuBar();
        menuFichier = new JMenu("Fichier");
        menuEdition = new JMenu("Edition");
        menuItemSauvegarder = new JMenuItem("Sauvegarder");
        menuItemSauvegarder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                fileChooser.setDialogTitle("Sélectionnez le dossier où sauvegarder");
                fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                int returnVal = fileChooser.showSaveDialog(ref);

                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    String path = fileChooser.getSelectedFile().getPath();
                    if (!path.contains(".data")){
                        path += ".data";
                    }
                    controller.saveProject(path);
                }
            }
        });
        menuItemCharger = new JMenuItem("Charger");
        menuItemCharger.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                fileChooser.setDialogTitle("Sélectionnez le fichier à ouvrir");
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                int returnVal = fileChooser.showOpenDialog(ref);

                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    controller.loadProject(file.getPath());
                }
            }
        });
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

        menuCheckboxImperial = new JCheckBoxMenuItem("Mesures Impériales");
        menuCheckboxImperial.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.setModeImperial(menuCheckboxImperial.getState());
            }
        });


        menuFichier.add(menuItemSauvegarder);
        menuFichier.add(menuItemCharger);

        JMenuItem menuMateriaux = new JMenuItem("Propriétés des matériaux");
        menuEdition.add(menuMateriaux);
        menuMateriaux.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                mainWindow.setEnabled(false);
                new FrameRevetements(controller, mainWindow).setVisible(true);
            }
        });
        JMenuItem menuTypeMateriau = new JMenuItem("Ajouter un type de matériau");
        menuEdition.add(menuTypeMateriau);
        menuTypeMateriau.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                mainWindow.setEnabled(false);
                new FrameMateriau(controller, mainWindow).setVisible(true);
            }
        });

        menuEdition.addSeparator();
        menuEdition.add(menuCheckboxMagnetiser);
        menuEdition.add(menuItemTailleGrilleMagnetique);
        menuEdition.add(menuCheckboxImperial);
        menuBar.add(menuFichier);
        menuBar.add(menuEdition);
        this.setJMenuBar(menuBar);

        panelVuePlan = new Canvas(controller);
        panelVueInfo = new PanneauConfiguration(controller, this);
        panneauActions = new PanneauActions(controller);

        JScrollPane scrollPane = new JScrollPane(panelVueInfo);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(300, 500));
        scrollPane.getVerticalScrollBar().setUnitIncrement(20);

        this.add(panelVuePlan, BorderLayout.CENTER);
        this.add(scrollPane, BorderLayout.EAST);
        this.add(panneauActions, BorderLayout.NORTH);

        this.setVisible(true);
    }

    @Override
    public void update() {
        if( controller.getEtat() == Etat.OUVRIR_FENETRE_ALIGNER){
            new FrameAlignement(controller, this).setVisible(true);
            controller.setEtat(Etat.ALIGNER);
            this.setEnabled(false);
        }
        if(controller.getEtat() == Etat.OUVRIR_FENETRE_ESPACER){
            new FrameEspacement(controller, this).setVisible(true);
            controller.setEtat(Etat.ESPACER);
            this.setEnabled(false);
        }
    }
}
