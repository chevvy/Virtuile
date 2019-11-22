package Vues.Alignement;
import MVC.Controller;

import javax.swing.*;
import java.awt.*;

public class FrameAlignement extends JFrame{
    private PanneauAlignement panelAlignement;

    public FrameAlignement frame;

    private Controller controller;

    public FrameAlignement(Controller controller) {
        frame = this;
        frame.setSize(600, 300);
        frame.setLocation(100,100);
        frame.setTitle("Alignement des surfaces");
        frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);


        panelAlignement = new PanneauAlignement(controller);
        frame.add(panelAlignement);

    }
}