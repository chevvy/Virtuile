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
    private Point translate;
    private boolean isLeftClicked = true;
    private final int GRID_SIZE = 50;

    public Canvas(Controller controller){
        controller.addObserver(this);
        this.controller = controller;
        dragOrigine = new Point();
        translate = new Point();
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
        if(isLeftClicked){
            translate.x = e.getX() - dragOrigine.x;
            translate.y = e.getY() - dragOrigine.y;
            repaint();
        }
    }

    private void mousePressedEvent(MouseEvent e){
        switch (e.getButton()){
            case 1:
                controller.clic(e.getX(), e.getY());
                break;
            case 2:
                //Wheel click
                break;
            case 3:
                //Left click
                dragOrigine.x = e.getX() - translate.x;
                dragOrigine.y = e.getY() - translate.y;
                isLeftClicked = true;
                break;
        }
    }

    private void mouseReleasedEvent(MouseEvent e){
        switch (e.getButton()){
            case 1:
                //Left click
                break;
            case 2:
                //Wheel click
                break;
            case 3:
                //Left click
                isLeftClicked = false;
                break;
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.gray);
        int sizeX = this.getWidth()-1;
        int sizeY = this.getHeight()-1;
        int gridOffX = translate.x % GRID_SIZE;
        int gridOffY = translate.y % GRID_SIZE;
        for(int i = -GRID_SIZE + gridOffX; i < sizeX + GRID_SIZE; i += GRID_SIZE){
            for(int j = -GRID_SIZE + gridOffY; j < sizeY + GRID_SIZE; j+=GRID_SIZE){
                g.drawRect(i, j, GRID_SIZE, GRID_SIZE);
            }
        }
        g.translate(translate.x, translate.y);
        this.controller.paintCanevas(g);
        g.fillRect(100,100,100,100);

    }

    @Override
    public void update() {
        repaint();
    }
}
