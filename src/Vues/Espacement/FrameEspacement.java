package Vues.Espacement;
import MVC.Controller;

import javax.swing.*;
import java.awt.*;

public class FrameEspacement extends JFrame{

    public FrameEspacement frame;

    private Controller controller;

    public FrameEspacement(Controller controller) {
        frame = this;
        frame.setSize(340, 130);
        frame.setLocation(100,100);
        frame.setTitle("Espacement entre deux surfaces");
        frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);


        PanneauEspacement panelEspacement = new PanneauEspacement(controller, this);
        frame.add(panelEspacement);

    }
}