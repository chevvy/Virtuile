package Vues;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {

    private JMenuBar menuBar;
    private JMenu menuFichier, menuEdition, menuVue;
    private JMenuItem menuItemSauvegarder, menuItemCharger;
    private PaneauConfiguration panelVueInfo;
    private Plan panelVuePlan;

    public MainWindow(){
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

        menuFichier.add(menuItemSauvegarder);
        menuFichier.add(menuItemCharger);
        menuEdition.add(new JMenuItem("Ajouter une surface"));
        menuEdition.add(new JMenuItem("Modifier les sommets"));
        menuEdition.add(new JMenuItem("Menu des materiaux"));
        menuBar.add(menuFichier);
        menuBar.add(menuEdition);
        menuBar.add(menuVue);
        this.setJMenuBar(menuBar);

        panelVuePlan = new Plan();
        panelVueInfo = new PaneauConfiguration();


        this.add(panelVuePlan, BorderLayout.CENTER);
        this.add(panelVueInfo, BorderLayout.EAST);

        this.setVisible(true);
    }
}
