package Vues.Revetements;

import MVC.Controller;
import MVC.Observer;

import java.awt.*;

import javax.swing.*;

public class PanneauChoixRevetements extends JPanel implements Observer {


    private Controller controller;
    private JList<Object> listeRevetements;


    PanneauChoixRevetements(Controller controller) {
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