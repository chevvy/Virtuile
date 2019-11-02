package Vues;
import MVC.Controller;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class PanneauChoixRevetements extends JPanel {

    private JList listeRevetements;
    private static String[] listItems = {"Revêtement 1", "Revêtement 2", "Revêtement3",
            "Revêtement 4", "Revêtement 4"};
    private Controller controller;

    public PanneauChoixRevetements(Controller controller) {

        setLayout(new FlowLayout());
        listeRevetements = new JList(listItems);
        listeRevetements.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listeRevetements.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        listeRevetements.setVisibleRowCount(-1);
        setVisible(true);
    }
}