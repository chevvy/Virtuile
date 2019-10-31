package Vues;

import MVC.Controller;
import MVC.Observer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame implements Observer {

    private JMenuBar menuBar;
    private JMenu menuFichier, menuEdition, menuVue;
    private JMenuItem menuItemSauvegarder, menuItemCharger;
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

        menuFichier.add(menuItemSauvegarder);
        menuFichier.add(menuItemCharger);

        JMenuItem menuMateriaux = new JMenuItem("Menu des materiaux");
        menuEdition.add(menuMateriaux);
        menuMateriaux.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                new FrameRevetements().setVisible(true);
            }
        });
        menuEdition.add(new JMenuItem("Ajouter une surface"));
        menuEdition.add(new JMenuItem("Modifier les sommets"));
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

    }
}
