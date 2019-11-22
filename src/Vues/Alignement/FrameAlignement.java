package Vues.Alignement;
import MVC.Controller;

import javax.swing.*;
import java.awt.*;

public class FrameAlignement extends JFrame{
    private PanneauAlignementVertical panelAlignementHorizontal;
    private PanneauAlignementHorizontal panelAlignementVertical;
    private PanneauAlignementVerticalImage panelAlignementVerticalImage;
    private PanneauAlignementHorizontalImage panelAlignementHorizontalImage;
    private PanneauAlignementConfirmerAnnuler panelConfirmerAnnuler;
    public FrameAlignement frame;

    private Controller controller;

    public FrameAlignement(Controller controller) {
        frame = this;
        frame.setSize(1100, 400);
        frame.setLocation(100,100);
        frame.setTitle("Aligment des surfaces");
        frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        frame.setLayout(new GridLayout(5,1));
        panelAlignementVerticalImage = new PanneauAlignementVerticalImage();
        frame.add(panelAlignementVerticalImage);
        panelAlignementVertical = new PanneauAlignementHorizontal(controller);
        frame.add(panelAlignementVertical);
        panelAlignementHorizontalImage = new PanneauAlignementHorizontalImage();
        frame.add(panelAlignementHorizontalImage);
        panelAlignementHorizontal = new PanneauAlignementVertical(controller);
        frame.add(panelAlignementHorizontal);
        panelConfirmerAnnuler = new PanneauAlignementConfirmerAnnuler(controller, frame);
        frame.add(panelConfirmerAnnuler);
    }
}