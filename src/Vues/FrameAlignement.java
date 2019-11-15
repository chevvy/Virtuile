package Vues;
import MVC.Controller;
import javax.swing.*;
import java.awt.*;

public class FrameAlignement extends JFrame{
    private PanneauAlignementHorizontal panelAlignementHorizontal;
    private PanneauAlignementVertical panelAlignementVertical;

    private Controller controller;

    public FrameAlignement(Controller controller) {
        this.setSize(1000, 400);
        this.setLocation(100,100);
        this.setTitle("Alligment des surfaces");
        this.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        panelAlignementVertical = new PanneauAlignementVertical(controller);
        this.add(panelAlignementVertical, BorderLayout.NORTH);
        panelAlignementHorizontal = new PanneauAlignementHorizontal(controller);
        this.add(panelAlignementHorizontal, BorderLayout.SOUTH);

    }
}