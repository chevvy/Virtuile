package Vues;
import Domaine.Revetement;
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
        this.controller = controller;
        model = new DefaultListModel();
        controller.plan.ajouter15Revetement(); //TODO à retirer

        for(Revetement revetement : controller.plan.getListeRevetements())
        {
            model.addElement(revetement.getNom());
        }

        // for Surface in controller.getPlan()
            // model.addElement(surface.getRevetement())
            // est-ce que ça consiste à du tx intelligent
        listeRevetements = new JList(model);
        listeRevetements.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        listeRevetements.setLayoutOrientation(JList.VERTICAL_WRAP);
        listeRevetements.setVisibleRowCount(-1);

        listeRevetements.setPreferredSize(new Dimension(180, 400));
        listeRevetements.setLayout(null);
        JScrollPane listScroller = new JScrollPane(listeRevetements);
        this.add(listScroller, BorderLayout.CENTER);
    }
}