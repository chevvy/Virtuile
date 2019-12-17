package Vues;

import MVC.Controller;
import MVC.Etat;
import MVC.Observer;
import Services.Historique;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class PanneauActions extends JPanel implements Observer {
    private Controller controller;
    private JButton boutonAjouter, boutonAjouterSurfaceVide, boutonSupprimer, boutonAlligment, boutonFusionner, boutonAjouterEspace,
        boutonForward, boutonBackward;

    public PanneauActions(Controller controller){
        this.controller = controller;
        controller.addObserver(this);
        this.setLayout(null);
        this.setPreferredSize(new Dimension(800, 50));
        this.setBorder(BorderFactory.createLineBorder(Color.black.brighter(), 1));
        setUpUI();
        update();
    }

    private void setUpUI(){
        // Ajouter une surface
        URL url = getClass().getResource("src/Ressources/ajouterSurface.png");
        ImageIcon imageAjouter = new ImageIcon(url);
        boutonAjouter = new JButton(imageAjouter);
        boutonAjouter.setMargin(new Insets(0, 0, 0, 0));
        boutonAjouter.setLayout(null);
        boutonAjouter.setSize(40, 40);
        boutonAjouter.setLocation(10, 5);
        boutonAjouter.setToolTipText("Ajouter une surface");
        boutonAjouter.addActionListener(e -> {
            controller.setTrou(false);
            setCreateShape();
        });
        this.add(boutonAjouter);

        // Ajouter une surface vide
        ImageIcon imageAjouterVide = new ImageIcon("src/Ressources/ajouterSurfaceVide.png");
        boutonAjouterSurfaceVide = new JButton(imageAjouterVide);
        boutonAjouterSurfaceVide.setMargin(new Insets(0, 0, 0, 0));
        boutonAjouterSurfaceVide.setLayout(null);
        boutonAjouterSurfaceVide.setSize(40, 40);
        boutonAjouterSurfaceVide.setLocation(60, 5);
        boutonAjouterSurfaceVide.setToolTipText("Ajouter une surface vide");
        boutonAjouterSurfaceVide.addActionListener(e -> {
            controller.setTrou(true);
            setCreateShape();
        });
        this.add(boutonAjouterSurfaceVide);

        // Supprimer une surface
        ImageIcon imageSupprimer = new ImageIcon("src/Ressources/suprimerSurface.png");
        boutonSupprimer = new JButton(imageSupprimer);
        boutonSupprimer.setMargin(new Insets(0, 0, 0, 0));
        boutonSupprimer.setLayout(null);
        boutonSupprimer.setSize(40, 40);
        boutonSupprimer.setLocation(110, 5);
        boutonSupprimer.setToolTipText("Suprimer une surface");
        boutonSupprimer.addActionListener(e -> controller.supprimerSurface());
        this.add(boutonSupprimer);

        // Alligner une surface
        ImageIcon imageAlligner = new ImageIcon("src/Ressources/allignerSurface.png");
        boutonAlligment = new JButton(imageAlligner);
        boutonAlligment.setMargin(new Insets(0, 0, 0, 0));
        boutonAlligment.setLayout(null);
        boutonAlligment.setSize(40, 40);
        boutonAlligment.setLocation(160, 5);
        boutonAlligment.setToolTipText("Alligner deux surfaces");
        boutonAlligment.addActionListener(e -> controller.selectionnerAligner());
        this.add(boutonAlligment);

        // Fusionner une surface
        ImageIcon imageFusionner = new ImageIcon("src/Ressources/fusionnerSurface.png");
        boutonFusionner = new JButton(imageFusionner);
        boutonFusionner.setMargin(new Insets(0, 0, 0, 0));
        boutonFusionner.setLayout(null);
        boutonFusionner.setSize(40, 40);
        boutonFusionner.setLocation(210, 5);
        boutonFusionner.setToolTipText("Fusionner deux surfaces");
        boutonFusionner.addActionListener(e -> controller.setEtat(Etat.FUSIONNER));
        this.add(boutonFusionner);

        // Espacer surface
        ImageIcon imageAjouterEspace = new ImageIcon("src/Ressources/ajouterEspace.png");
        boutonAjouterEspace = new JButton(imageAjouterEspace);
        boutonAjouterEspace.setMargin(new Insets(0, 0, 0, 0));
        boutonAjouterEspace.setLayout(null);
        boutonAjouterEspace.setSize(40, 40);
        boutonAjouterEspace.setLocation(260, 5);
        boutonAjouterEspace.setToolTipText("Ajouter un espace");
        boutonAjouterEspace.addActionListener(e -> {
            controller.selectionnerEspacer();
        });
        this.add(boutonAjouterEspace);

        // Undo
        ImageIcon imageBackward = new ImageIcon("src/Ressources/backward.png");
        boutonBackward = new JButton(imageBackward);
        boutonBackward.setMargin(new Insets(0, 0, 0, 0));
        boutonBackward.setLayout(null);
        boutonBackward.setSize(40, 40);
        boutonBackward.setLocation(310, 5);
        boutonBackward.setToolTipText("Revenir");
        boutonBackward.addActionListener(e -> {
            controller.goBackward();
        });
        this.add(boutonBackward);

        // Redo
        ImageIcon imageForward = new ImageIcon("src/Ressources/forward.png");
        boutonForward = new JButton(imageForward);
        boutonForward.setMargin(new Insets(0, 0, 0, 0));
        boutonForward.setLayout(null);
        boutonForward.setSize(40, 40);
        boutonForward.setLocation(360, 5);
        boutonForward.setToolTipText("Rétablir");
        boutonForward.addActionListener(e -> {
            controller.goForward();
        });
        this.add(boutonForward);
    }

    private void setCreateShape(){
        Object[] options = { "Carré", "Triangle", "Forme libre", "Pentagone"};
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
        boutonForward.setEnabled(Historique.canGoForward());
        boutonBackward.setEnabled(Historique.canGobackward());
    }
}
