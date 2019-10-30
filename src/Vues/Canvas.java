package Vues;

import MVC.Controller;
import MVC.Observer;

import javax.naming.ldap.Control;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class Canvas extends JPanel implements Observer{

    private Controller controller;

    public Canvas(Controller controller){
        controller.addObserver(this);
        this.controller = controller;
    }

    @Override
    public void paint(Graphics g) {
        this.controller.paintCanevas(g, this.getHeight(), this.getWidth());
    }

    @Override
    public void update() {
        repaint();
    }
}
