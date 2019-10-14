package Vues;

import javax.swing.*;
import javax.swing.event.MenuKeyEvent;
import javax.swing.event.MenuKeyListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame {

    private JMenuBar menuBar;
    private JMenu menuFile, menuEdit, menuView;
    private JMenuItem menuItemSauvegarder, menuItemCharger;
    private JPanel panelVuePlan, panelVueInfo;

    public MainWindow(){
        this.setSize(500, 500);
        this.setLocation(100,100);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        this.setTitle("VirtuTuile");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        menuBar = new JMenuBar();
        menuFile = new JMenu("File");
        menuEdit = new JMenu("Edit");
        menuView = new JMenu("View");
        menuItemSauvegarder = new JMenuItem("Sauvegarder");
        menuItemCharger = new JMenuItem("Charger");

        menuItemSauvegarder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Un event");
            }
        });

        menuFile.add(menuItemSauvegarder);
        menuFile.add(menuItemCharger);
        menuEdit.add(new JMenuItem("Ajouter une surface"));
        menuEdit.add(new JMenuItem("Modifier les sommets"));
        menuBar.add(menuFile);
        menuBar.add(menuEdit);
        menuBar.add(menuView);
        this.setJMenuBar(menuBar);

        panelVuePlan = new Plan();
        panelVueInfo = new JPanel();

        panelVueInfo.setBackground(Color.gray);
        panelVueInfo.add(new JLabel("And I say hey, hey hey, what's going on"));

        this.add(panelVuePlan, BorderLayout.CENTER);
        this.add(panelVueInfo, BorderLayout.EAST);

        this.setVisible(true);
    }
}
