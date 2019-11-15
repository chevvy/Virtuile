package Vues;
import MVC.Controller;

import java.awt.*;

import javax.swing.*;

public class PanneauChoixRevetements extends JPanel {

    private JList listeRevetements;
    private DefaultListModel model;
    private Controller controller;

    /*
    pour retirer un élement de ta liste tu fais
    int index = list.getSelectedIndex();
    listModel.remove(index);
    * */

    public PanneauChoixRevetements(Controller controller) {
        // Model c'est ça qui va contenir les éléments de ta liste
        model = new DefaultListModel();
        for (int i = 0; i < 15; i++) {
            // addElement pour ajouter un élément à la liste
            model.addElement("Revètement "+(i+1));
        }
        listeRevetements = new JList(model);
        listeRevetements.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        listeRevetements.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        listeRevetements.setVisibleRowCount(-1);
        this.controller = controller;
        listeRevetements.setPreferredSize(new Dimension(180, 400));
        listeRevetements.setLayout(null);
        JScrollPane listScroller = new JScrollPane(listeRevetements);
        this.add(listScroller, BorderLayout.CENTER);
    }
}