package Vues.Revetements;

import MVC.Controller;
import MVC.Etat;
import MVC.Observer;

import javax.swing.*;
import java.awt.*;

public class PanneauActions extends JPanel implements Observer {
    private Controller controller;
    private JButton boutonAjouter, boutonAjouterSurfaceVide, boutonSupprimer, boutonAlligment, boutonFusionner, boutonAjouterEspace;

    public PanneauActions(Controller controller){
        this.controller = controller;
        controller.addObserver(this);
        this.setLayout(null);
        this.setPreferredSize(new Dimension(800, 50));
        this.setBorder(BorderFactory.createLineBorder(Color.black.brighter(), 1));
        setUpUI();
    }

    private void setUpUI(){
        ImageIcon imageAjouter = new ImageIcon("src/Ressources/ajouterSurface.png");
        boutonAjouter = new JButton(imageAjouter);
        boutonAjouter.setMargin(new Insets(0, 0, 0, 0));
        boutonAjouter.setLayout(null);
        boutonAjouter.setSize(40, 40);
        boutonAjouter.setLocation(10, 5);
        boutonAjouter.addActionListener(e -> {
            controller.setTrou(false);
            setCreateShape();
        });
        this.add(boutonAjouter);

        ImageIcon imageAjouterVide = new ImageIcon("src/Ressources/ajouterSurfaceVide.png");
        boutonAjouterSurfaceVide = new JButton(imageAjouterVide);
        boutonAjouterSurfaceVide.setMargin(new Insets(0, 0, 0, 0));
        boutonAjouterSurfaceVide.setLayout(null);
        boutonAjouterSurfaceVide.setSize(40, 40);
        boutonAjouterSurfaceVide.setLocation(60, 5);
        boutonAjouterSurfaceVide.addActionListener(e -> {
            controller.setTrou(true);
            setCreateShape();
        });
        this.add(boutonAjouterSurfaceVide);

        ImageIcon imageSupprimer = new ImageIcon("src/Ressources/suprimerSurface.png");
        boutonSupprimer = new JButton(imageSupprimer);
        boutonSupprimer.setMargin(new Insets(0, 0, 0, 0));
        boutonSupprimer.setLayout(null);
        boutonSupprimer.setSize(40, 40);
        boutonSupprimer.setLocation(110, 5);
        boutonSupprimer.addActionListener(e -> controller.supprimerSurface());
        this.add(boutonSupprimer);

        ImageIcon imageAlligner = new ImageIcon("src/Ressources/allignerSurface.png");
        boutonAlligment = new JButton(imageAlligner);
        boutonAlligment.setMargin(new Insets(0, 0, 0, 0));
        boutonAlligment.setLayout(null);
        boutonAlligment.setSize(40, 40);
        boutonAlligment.setLocation(160, 5);
        boutonAlligment.addActionListener(e -> controller.selectionnerAligner());
        this.add(boutonAlligment);

        ImageIcon imageFusionner = new ImageIcon("src/Ressources/fusionnerSurface.png");
        boutonFusionner = new JButton(imageFusionner);
        boutonFusionner.setMargin(new Insets(0, 0, 0, 0));
        boutonFusionner.setLayout(null);
        boutonFusionner.setSize(40, 40);
        boutonFusionner.setLocation(210, 5);
        boutonFusionner.addActionListener(e -> controller.setEtat(Etat.FUSIONNER));
        this.add(boutonFusionner);

        ImageIcon imageAjouterEspace = new ImageIcon("src/Ressources/ajouterEspace.png");
        boutonAjouterEspace = new JButton(imageAjouterEspace);
        boutonAjouterEspace.setMargin(new Insets(0, 0, 0, 0));
        boutonAjouterEspace.setLayout(null);
        boutonAjouterEspace.setSize(40, 40);
        boutonAjouterEspace.setLocation(260, 5);
        boutonAjouterEspace.addActionListener(e -> {
            controller.selectionnerEspacer();
        });
        this.add(boutonAjouterEspace);
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

    }
}
