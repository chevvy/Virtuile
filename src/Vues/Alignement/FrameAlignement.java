package Vues.Alignement;
import MVC.Controller;

import javax.swing.*;
import java.awt.*;

public class FrameAlignement extends JFrame{

    public FrameAlignement frame;

    private Controller controller;

    public FrameAlignement(Controller controller) {
        frame = this;
        frame.setSize(680, 350);
        frame.setLocation(100,100);
        frame.setTitle("Alignement des surfaces");
        frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);


        PanneauAlignement panelAlignement = new PanneauAlignement(controller, this);
        frame.add(panelAlignement);

    }
}