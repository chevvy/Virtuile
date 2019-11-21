package Vues.Alignement;

import MVC.Controller;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanneauAlignementVertical extends JPanel{
    private ButtonGroup buttonGroupVertical;
    private JRadioButton boutonHaut01, boutonHaut02, boutonCentreVertical, boutonBas01, boutonBas02, aucunAlligmentVertical;
    private Controller controller;
    public PanneauAlignementVertical(Controller controller) {
        SetUpUi();
    }
    private void SetUpUi () {
        //Groupe Vertical

        //Bouton01 - Haut Ext
        buttonGroupVertical = new ButtonGroup();
        JRadioButton boutonHaut01 = new JRadioButton("Haut - Extérieur  ");
        this.add(boutonHaut01);
        buttonGroupVertical.add(boutonHaut01);
        boutonHaut01.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e1) { controller.aligner("hautExt"); }
        });

        //Bouton02 - Haut Int
        JRadioButton boutonHaut02 = new JRadioButton("Haut - Intérieur  ");
        this.add(boutonHaut02);
        buttonGroupVertical.add(boutonHaut02);
        boutonHaut02.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e2) { controller.aligner("hautInt"); }
        });

        //Bouton03 - Centre vert
        JRadioButton boutonCentreVertical = new JRadioButton("Centré verticalement");
        buttonGroupVertical.add(boutonCentreVertical);
        boutonCentreVertical.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e3) { controller.aligner("centreVertical"); }
        });

        //bouton 04 - bas int
        JRadioButton boutonBas01 = new JRadioButton("Bas - Intérieur   ");
        this.add(boutonBas01);
        buttonGroupVertical.add(boutonBas01);
        boutonBas01.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e4) { controller.aligner("basInt"); }
        });

        //Bouton 05 - Bas ext
        JRadioButton boutonBas02 = new JRadioButton("Bas - Extérieur   ");
        this.add(boutonBas02);
        buttonGroupVertical.add(boutonBas02);
        boutonBas02.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e5) { controller.aligner("basExt"); }
        });

        //Bouton 06 - rien vertical
        JRadioButton aucunAlligmentVertical = new JRadioButton("Aucun alignement vertical");
        aucunAlligmentVertical.setSelected(true);
        this.add(aucunAlligmentVertical);
        buttonGroupVertical.add(aucunAlligmentVertical);
        aucunAlligmentVertical.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e6) { controller.aligner("rienVertical"); }
        });
        this.setVisible(true);


    }
}

//call controler selectionner aligmner quand on clique sur le bouton aligner/coller ( entre deux surfaces)
//apres update
//si on clique sur radio - call controler.aligner(le nom de l'image)
//caller confirmerdeplacement()
//sinon (annuler ou fermer fenetre) annulerAligner()