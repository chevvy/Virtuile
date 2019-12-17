package Vues.Alignement;
import MVC.Controller;

import javax.swing.*;
import java.awt.*;

public class PanneauAlignement extends JPanel{

    public PanneauAlignement(Controller controller, FrameAlignement frame) {

        this.setSize(1100, 400);

        PanneauAlignementHorizontalImage panelAlignementHorizontalImage = new PanneauAlignementHorizontalImage(controller);
        panelAlignementHorizontalImage.setLayout(new GridLayout(1,6));
        this.add(panelAlignementHorizontalImage);

        PanneauAlignementVerticalImage panelAlignementVerticalImage = new PanneauAlignementVerticalImage(controller);
        panelAlignementVerticalImage.setLayout(new GridLayout(1,6));
        this.add(panelAlignementVerticalImage);

        PanneauAlignementConfirmerAnnuler panelConfirmerAnnuler = new PanneauAlignementConfirmerAnnuler(controller, frame);
        this.add(panelConfirmerAnnuler);
    }
}