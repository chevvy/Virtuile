package Vues;

import MVC.Controller;
import MVC.Observer;

import javax.naming.ldap.Control;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Canvas extends JPanel implements Observer{

    private Controller controller;
    private Point dragOrigine;
    private boolean isLeftClicked = true;

    public Canvas(Controller controller){
        controller.addObserver(this);
        this.controller = controller;
        dragOrigine = new Point();
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                mousedDraggedEvent(e);
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                mouseMovedEvent(e);
            }
        });
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                mousePressedEvent(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                mouseReleasedEvent(e);
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }

    private void mouseMovedEvent(MouseEvent e){
    }

    private void mousedDraggedEvent(MouseEvent e){
    }

    private void mousePressedEvent(MouseEvent e){
        switch (e.getButton()){
            case 1:
                //Left click
                break;
            case 2:
                //Wheel click
                break;
            case 3:
                //Left click
                dragOrigine = e.getPoint();
                isLeftClicked = true;
                break;
        }
    }

    private void mouseReleasedEvent(MouseEvent e){
    }

    @Override
    public void paint(Graphics g) {
        super.paintComponent(g);
        this.controller.paintCanevas(g, this.getHeight(), this.getWidth());
    }

    @Override
    public void update() {
        repaint();
    }
}
