package Vues;
import MVC.Controller;
import javax.swing.*;
import java.awt.*;

public class FrameAllignement extends JFrame{
    private PanneauAllignement panelAllignement;
    private Controller controller;

    public FrameAllignement() {
        this.setSize(300, 300);
        this.setLocation(100,100);
        this.setTitle("Alligment des surfaces");
        this.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        panelAllignement = new PanneauAllignement(controller);
        this.add(panelAllignement, BorderLayout.WEST);

    }
}