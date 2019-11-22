package Vues.Alignement;
import MVC.Controller;

import javax.swing.*;
import java.awt.*;

public class PanneauAlignement extends JPanel{
    private PanneauAlignementVertical panelAlignementHorizontal;
    private PanneauAlignementHorizontal panelAlignementVertical;
    private PanneauAlignementVerticalImage panelAlignementVerticalImage;
    private PanneauAlignementHorizontalImage panelAlignementHorizontalImage;
    private PanneauAlignementConfirmerAnnuler panelConfirmerAnnuler;
    private FrameAlignement frame;

    private Controller controller;

    public PanneauAlignement(Controller controller, FrameAlignement frame) {
        this.frame = frame;
        this.controller = controller;

        this.setSize(1100, 400);

        panelAlignementHorizontalImage = new PanneauAlignementHorizontalImage();
        this.add(panelAlignementHorizontalImage);

        panelAlignementVertical = new PanneauAlignementHorizontal(controller);
        this.add(panelAlignementVertical);

        panelAlignementVerticalImage = new PanneauAlignementVerticalImage();
        this.add(panelAlignementVerticalImage);

        panelAlignementHorizontal = new PanneauAlignementVertical(controller);
        this.add(panelAlignementHorizontal);

        panelConfirmerAnnuler = new PanneauAlignementConfirmerAnnuler(controller, frame);
        this.add(panelConfirmerAnnuler);
    }
}