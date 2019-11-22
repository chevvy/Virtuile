package Vues;

import MVC.Controller;
import MVC.Etat;
import Vues.Revetements.FrameRevetements;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanneauConfiguration extends JScrollPane {

    private JButton boutonAjouter, boutonSupprimer, boutonMenuRevetement, boutonAlligment;
    private JRadioButton radioSurface, radioVide;
    private JComboBox listeMateriau, listeAlignement;

    private Controller controller;

    public PanneauConfiguration(Controller controller){
        this.setBackground(Color.gray);

        this.setPreferredSize(new Dimension(250, 500));
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        this.setLayout(null);
        this.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        SetUpUi();

        this.controller = controller;
    }

    private void SetUpUi(){
        boutonAjouter = new JButton("Ajouter une Surface");
        boutonAjouter.setSize(200, 50);
        boutonAjouter.setLocation(25,20);
        boutonAjouter.addActionListener(e -> setCreateShape());

        boutonSupprimer = new JButton("Supprimer la surface");
        boutonSupprimer.setSize(200, 50);
        boutonSupprimer.setLocation(25,90);
        boutonSupprimer.addActionListener(e -> controller.supprimerSurface());

        JLabel line = new JLabel("_________________________________");
        line.setSize(250,50);
        line.setLocation(10,150);

        // Premier element
        radioSurface = new JRadioButton("Surface");
        radioSurface.setSelected(true);
        radioSurface.setBackground(Color.gray);
        radioSurface.setSize(100, 20);
        radioSurface.setLocation(15, 190);

        // Premier element
        radioVide = new JRadioButton("Vide");
        radioVide.setBackground(Color.gray);
        radioVide.setSize(100, 20);
        radioVide.setLocation(135, 190);

        ButtonGroup group = new ButtonGroup();
        group.add(radioSurface);
        group.add(radioVide);

        // 2eme element
        JLabel labelMateriau = new JLabel("Revêtement : ");
        labelMateriau.setSize(100, 20);
        labelMateriau.setLocation(15, 230);

        JTextField surfaceSelectionne = new JTextField();
        // surfaceSelectionne.addActionListener(); action -> si surface selectioner, fetch le nom du revetement
        surfaceSelectionne.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("Surface selectionne");
                // TODO créer un custom actionEvent pour la selection d'une surface
                // https://stackoverflow.com/questions/2919828/creating-actionevent-object-for-custombutton-in-java
            }
        });


        String [] materiaux = {"Brique", "Terre"}; // TODO à remplacer par surface Selectionne
        listeMateriau = new JComboBox(materiaux);
        listeMateriau.setSize(135, 30);
        listeMateriau.setLocation(100, 225);

        JLabel labelRevetementDeLaSurface = new JLabel("Revetement : ");
        labelRevetementDeLaSurface.setSize(100, 20);
        labelRevetementDeLaSurface.setLocation(15, 290);


        boutonMenuRevetement = new JButton("Édition revêtement");
        boutonMenuRevetement.setSize(200, 50);
        boutonMenuRevetement.setLocation(25,320);
        boutonMenuRevetement.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {new FrameRevetements(controller).setVisible(true);}
        });

        JLabel labelAlignement = new JLabel("Alignement : ");
        labelAlignement.setSize(100, 20);
        labelAlignement.setLocation(15, 270);

        String [] alignement = {"Aligné", "Pas aligné"};
        listeAlignement = new JComboBox(alignement);
        listeAlignement.setSize(135, 30);
        listeAlignement.setLocation(100, 265);

        boutonAlligment = new JButton("Alligner/Coller");
        boutonAlligment.setSize(200, 50);
        boutonAlligment.setLocation(25,380);
        boutonAlligment.addActionListener(e -> controller.selectionnerAligner());


        this.add(boutonAjouter);
        this.add(boutonSupprimer);
        this.add(line);
        this.add(radioSurface);
        this.add(radioVide);
        this.add(labelMateriau);
        this.add(listeMateriau);
        this.add(labelAlignement);
        this.add(listeAlignement);
        this.add(boutonMenuRevetement);
        this.add(boutonAlligment);
        this.add(labelRevetementDeLaSurface);
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

    private class PanelSommet extends JFrame{

        public PanelSommet(Point point){

        }
    }
}
