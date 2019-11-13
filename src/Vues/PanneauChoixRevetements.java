package Vues;
import MVC.Controller;

import java.awt.*;

import javax.swing.*;

public class PanneauChoixRevetements extends JPanel {

    private JList listeRevetements;
    DefaultListModel model;
    private static String[] listItems = {"Revêtement 1", "Revêtement 2", "Revêtement3",
            "Revêtement 4", "Revêtement 4"};
    private Controller controller;

    public PanneauChoixRevetements(Controller controller) {

        setLayout(new BorderLayout());
        model = new DefaultListModel();
        listeRevetements = new JList(model);
        JScrollPane pane = new JScrollPane(listeRevetements);
        listeRevetements.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listeRevetements.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        listeRevetements.setVisibleRowCount(-1);
        for (int i = 0; i < 15; i++)
            model.addElement("Element " + i);
        setVisible(true);
    }
}