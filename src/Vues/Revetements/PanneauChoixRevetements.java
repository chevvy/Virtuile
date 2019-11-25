package Vues.Revetements;

import MVC.Controller;
import MVC.Observer;

import java.awt.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class PanneauChoixRevetements extends JPanel implements Observer {


    private Controller controller;
    private JList<Object> listeRevetements;
    public String nomRevetementSelectionne;


    PanneauChoixRevetements(Controller controller) {
        controller.addObserver(this);

        this.controller = controller;
        setUpUI();
    }

    private void setUpUI() {
        DefaultListModel model = new DefaultListModel();

        controller.getNomRevetements().forEach(nom -> model.addElement(nom));

        JList listeRevetements = new JList(model);
        listeRevetements.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        listeRevetements.setLayoutOrientation(JList.VERTICAL_WRAP);
        listeRevetements.setVisibleRowCount(-1);
        
        listeRevetements.setPreferredSize(new Dimension(150, 350));
        listeRevetements.setLayout(null);
        listeRevetements.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent arg){
                if(!arg.getValueIsAdjusting()){
                    System.out.println(listeRevetements.getSelectedValue().toString());
                }
            }
                                                  });

        JScrollPane listScroller = new JScrollPane(listeRevetements);

        this.add(listeRevetements, BorderLayout.CENTER);

    }

    @Override
    public void update() {


    }
    //private int getIndexSelectionner() {
        //return listeRevetements.getSelectedIndex();
    //}
}