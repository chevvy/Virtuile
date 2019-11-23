package Vues.Revetements;
import Domaine.Revetement;
import MVC.Controller;

import java.awt.*;

import javax.swing.*;

public class PanneauChoixRevetements extends JPanel {

    /*
    pour retirer un élement de ta liste tu fais
    int index = list.getSelectedIndex();
    listModel.remove(index);
    * */

    public PanneauChoixRevetements(Controller controller) {
        DefaultListModel model = new DefaultListModel();
        controller.plan.ajouter15Revetement(); //TODO à retirer

        for(Revetement revetement : controller.plan.getListeRevetements())
        {
            model.addElement(revetement.getNomDuRevetement());
        }

        // for Surface in controller.getPlan()
            // model.addElement(surface.getRevetement())
            // est-ce que ça consiste à du tx intelligent
        JList listeRevetements = new JList(model);
        listeRevetements.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        listeRevetements.setLayoutOrientation(JList.VERTICAL_WRAP);
        listeRevetements.setVisibleRowCount(-1);

        listeRevetements.setPreferredSize(new Dimension(150, 350));
        listeRevetements.setLayout(null);
        JScrollPane listScroller = new JScrollPane(listeRevetements);
        this.add(listScroller, BorderLayout.CENTER);
    }
}