package Vues;

import MVC.Controller;
import MVC.Etat;
import Vues.Revetements.FrameRevetements;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import MVC.Observer;


public class PanneauConfiguration extends JScrollPane implements Observer{

    private JButton boutonAjouter, boutonSupprimer, boutonMenuRevetement, boutonAlligment;
    private JRadioButton radioSurface, radioVide;
    private JComboBox listeAlignement;
    private JTextField revetementSurfaceSelectionnee;

    private Controller controller;

    public PanneauConfiguration(Controller controller){
        controller.addObserver(this);
        this.setBackground(Color.gray);
        this.setPreferredSize(new Dimension(250, 500));
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        this.setLayout(null);
        this.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        SetUpUi();

        this.controller = controller;
    }

    private void SetUpUi(){

        boutonAjouter = new JButton("Ajouter une surface");
        boutonAjouter.setSize(200, 30);
        boutonAjouter.setLocation(25,20);
        boutonAjouter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //controller.setTrou(false);
                setCreateShape();;
            }
        });

        //ajouter option vide
        JButton boutonAjouterSurfaceVide = new JButton("Ajouter une surface vide");
        boutonAjouterSurfaceVide.setSize(200, 30);
        boutonAjouterSurfaceVide.setLocation(25,50);
        boutonAjouterSurfaceVide.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //controller.setTrou(true);
                setCreateShape();;
            }
        });

        boutonSupprimer = new JButton("Supprimer la surface");
        boutonSupprimer.setSize(200, 30);
        boutonSupprimer.setLocation(25,80);
        boutonSupprimer.addActionListener(e -> controller.supprimerSurface());

        boutonAlligment = new JButton("Alligner/Coller");
        boutonAlligment.setSize(200, 30);
        boutonAlligment.setLocation(25,110);
        boutonAlligment.addActionListener(e -> controller.selectionnerAligner());

        JButton boutonTestFusionner;
        boutonTestFusionner = new JButton("Fusionner");
        boutonTestFusionner.setSize(200,30);
        boutonTestFusionner.setLocation(25, 140);
        boutonTestFusionner.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                controller.setEtat(Etat.FUSIONNER);
            }
        });

        JLabel line = new JLabel("_________________________________");
        line.setSize(250,25);
        line.setLocation(10,160);

        JLabel InfoSurface = new JLabel("Surface sélectionnée");
        InfoSurface.setSize(250,25);
        InfoSurface.setLocation(10,180);

        // Premier element (rangee 1)
        radioSurface = new JRadioButton("Surface");
        radioSurface.setSelected(true);
        radioSurface.setBackground(Color.gray);
        radioSurface.setSize(100, 20);
        radioSurface.setLocation(15, 200);

        // Premier element (range 2)
        radioVide = new JRadioButton("Vide");
        radioVide.setBackground(Color.gray);
        radioVide.setSize(100, 20);
        radioVide.setLocation(135, 200);

        ButtonGroup group = new ButtonGroup();
        group.add(radioSurface);
        group.add(radioVide);

        // 2eme element - rangee 1
        JLabel labelMateriau = new JLabel("Revêtement : ");
        labelMateriau.setSize(100, 20);
        labelMateriau.setLocation(15, 230);

        // 2eme element - rangee 2
        revetementSurfaceSelectionnee = new JTextField();
        revetementSurfaceSelectionnee.setText("Revetement 1"); // TODO à remplacer par vrai code pour fecth
        revetementSurfaceSelectionnee.setEditable(false);
        revetementSurfaceSelectionnee.setSize(135, 30);
        revetementSurfaceSelectionnee.setLocation(100, 225);

        // 3eme element
        boutonMenuRevetement = new JButton("Édition revêtement");
        boutonMenuRevetement.setSize(200, 25);
        boutonMenuRevetement.setLocation(25,265);
        boutonMenuRevetement.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {new FrameRevetements(controller).setVisible(true);}
        });

        JLabel labelAlignement = new JLabel("Alignement : ");
        labelAlignement.setSize(100, 20);
        labelAlignement.setLocation(15, 305);

        String [] alignement = {"Aligné", "Pas aligné"};
        listeAlignement = new JComboBox(alignement);
        listeAlignement.setSize(135, 30);
        listeAlignement.setLocation(100, 300);



        this.add(boutonAjouter);
        this.add(boutonAjouterSurfaceVide);
        this.add(boutonSupprimer);
        this.add(line);
        this.add(InfoSurface);
        this.add(radioSurface);
        this.add(radioVide);
        this.add(labelMateriau);
        this.add(revetementSurfaceSelectionnee);
        this.add(labelAlignement);
        this.add(listeAlignement);
        this.add(boutonMenuRevetement);
        this.add(boutonAlligment);
        this.add(boutonTestFusionner);
        this.setVisible(true);

    }

    private void setCreateShape(){
        Object[] options = { "Carré", "Triangle", "Forme libre", "Pentagramme"};
        String res = (String)JOptionPane.showInputDialog(null, "Forme pour la création", "Forme à créer",
                JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
        int i = -1;
        for (int j = 0; j < options.length; j++) {
            if (options[j] == (String)res){ i = j; }
        }
        controller.ajouterSurface(i);
    }

    @Override
    public void update() {
        if (controller.getPlan().surfaceSelectionnee != null && controller.getEtat().equals(Etat.LECTURE)  )
        {
            revetementSurfaceSelectionnee.setText(controller.getPlan().surfaceSelectionnee.getRevetement().getNomDuRevetement());
        }
    }

    private class PanelSommet extends JFrame{

        public PanelSommet(Point point){

        }
    }
}
