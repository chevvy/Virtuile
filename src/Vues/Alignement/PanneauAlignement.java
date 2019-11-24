package Vues.Alignement;
import MVC.Controller;

import javax.swing.*;
import java.awt.*;

public class PanneauAlignement extends JPanel{

    public PanneauAlignement(Controller controller, FrameAlignement frame) {

        this.setSize(1100, 400);

        PanneauAlignementHorizontalImage panelAlignementHorizontalImage = new PanneauAlignementHorizontalImage();
        panelAlignementHorizontalImage.setLayout(new GridLayout(1,6));
        this.add(panelAlignementHorizontalImage);

        PanneauAlignementHorizontal panelAlignementVertical = new PanneauAlignementHorizontal(controller);
        panelAlignementVertical.setLayout(new GridLayout(1,6));
        this.add(panelAlignementVertical);

        PanneauAlignementVerticalImage panelAlignementVerticalImage = new PanneauAlignementVerticalImage();
        panelAlignementVerticalImage.setLayout(new GridLayout(1,6));
        this.add(panelAlignementVerticalImage);

        PanneauAlignementVertical panelAlignementHorizontal = new PanneauAlignementVertical(controller);
        panelAlignementHorizontal.setLayout(new GridLayout(1,6));
        this.add(panelAlignementHorizontal);

        PanneauAlignementConfirmerAnnuler panelConfirmerAnnuler = new PanneauAlignementConfirmerAnnuler(controller, frame);
        this.add(panelConfirmerAnnuler);
    }
}