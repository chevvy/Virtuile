package Vues.Revetements;

import MVC.Controller;
import MVC.Observer;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

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

        controller.getNomRevetements().forEach(nom -> model.addElement(nom));

        this.listeRevetements = new JList(model);
        listeRevetements.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        listeRevetements.setLayoutOrientation(JList.VERTICAL_WRAP);
        listeRevetements.setVisibleRowCount(-1);
        
        listeRevetements.setPreferredSize(new Dimension(150, 350));
        listeRevetements.setLayout(null);
        listeRevetements.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent arg){
                if(!arg.getValueIsAdjusting()){
                    controller.gestionnaireRevetements.setRevetementSelectionnee(listeRevetements.getSelectedValue().toString());
                    controller.ClicMenu();
                }
            }
        });


        JScrollPane listScroller = new JScrollPane(listeRevetements);
        this.add(listScroller, BorderLayout.CENTER);


    }

    @Override
    public void update() {
        if(controller.gestionnaireRevetements.getRevetementSelectionnee() != null)
        {
            String revSelectionnee = controller.gestionnaireRevetements.getRevetementSelectionnee();
            int index = controller.gestionnaireRevetements.getPositionDansSet(controller.gestionnaireRevetements.getNomRevetements(), revSelectionnee);
            listeRevetements.setSelectedIndex(index);
        }

    }
    //private int getIndexSelectionner() {
        //return listeRevetements.getSelectedIndex();
    //}
}