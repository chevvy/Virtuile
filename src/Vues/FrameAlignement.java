package Vues;
import MVC.Controller;
import javax.swing.*;
import java.awt.*;

public class FrameAlignement extends JFrame{
    private PanneauAlignementVertical panelAlignementHorizontal;
    private PanneauAlignementHorizontal panelAlignementVertical;
    private PanneauAlignementVerticalImage panelAlignementVerticalImage;
    private PanneauAlignementHorizontalImage panelAlignementHorizontalImage;
    private PanneauConfirmerAnnuler panelConfirmerAnnuler;

    private Controller controller;

    public FrameAlignement(Controller controller) {
        this.setSize(1100, 400);
        this.setLocation(100,100);
        this.setTitle("Aligment des surfaces");
        this.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        this.setLayout(new GridLayout(5,1));
        panelAlignementVerticalImage = new PanneauAlignementVerticalImage();
        this.add(panelAlignementVerticalImage);
        panelAlignementVertical = new PanneauAlignementHorizontal(controller);
        this.add(panelAlignementVertical);
        panelAlignementHorizontalImage = new PanneauAlignementHorizontalImage();
        this.add(panelAlignementHorizontalImage);
        panelAlignementHorizontal = new PanneauAlignementVertical(controller);
        this.add(panelAlignementHorizontal);
        panelConfirmerAnnuler = new PanneauConfirmerAnnuler(controller);
        this.add(panelConfirmerAnnuler);
    }
}