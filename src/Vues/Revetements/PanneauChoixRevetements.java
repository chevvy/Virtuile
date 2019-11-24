package Vues.Revetements;
import Domaine.Revetement;
import MVC.Controller;
import MVC.Observer;

import java.awt.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class PanneauChoixRevetements extends JPanel implements Observer {


    private Controller controller;
    private JList<Object> listeRevetements;


    PanneauChoixRevetements(Controller controller) {
        controller.addObserver(this);
        this.controller = controller;
        setUpUI();
    }

    private void setUpUI() {
        DefaultListModel model = new DefaultListModel();

        for(Revetement revetement : controller.getRevetements())
        {
            model.addElement(revetement.getNomDuRevetement());
        }

        JList listeRevetements = new JList(model);
        listeRevetements.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        listeRevetements.setLayoutOrientation(JList.VERTICAL_WRAP);
        listeRevetements.setVisibleRowCount(-1);


        listeRevetements.setPreferredSize(new Dimension(150, 350));
        listeRevetements.setLayout(null);
        listeRevetements.addListSelectionListener(e -> new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                System.out.println("Revetement selectionne = " + e.toString());
            }
        });
        JScrollPane listScroller = new JScrollPane(listeRevetements);

        this.add(listScroller, BorderLayout.CENTER);

    }

    @Override
    public void update() {
        this.setUpUI();
    }
    //private int getIndexSelectionner() {
        //return listeRevetements.getSelectedIndex();
    //}
}