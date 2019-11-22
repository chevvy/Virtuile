package Vues;

import MVC.Controller;
import MVC.Observer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;

public class Canvas extends JPanel implements Observer{

    private Controller controller;
    private Point dragOrigine;
    private Point translate;
    private boolean isRightClicked = false;
    private boolean isLeftClicked = false;
    private Point mouse;
    private double zoomFactor = 1;


    public Canvas(Controller controller){
        controller.addObserver(this);
        this.controller = controller;
        dragOrigine = new Point();
        translate = new Point();
        mouse = new Point();
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
        this.addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent mouseWheelEvent) { MouseWheelMovedEvent(mouseWheelEvent);}
        });
    }

    private void MouseWheelMovedEvent(MouseWheelEvent evt){
        if (evt.getPreciseWheelRotation() > 0 && zoomFactor >= 0.1) {
            // zoom out
            zoomFactor -= 0.07;
        } else {
            // zoom in
            zoomFactor += 0.07;
        }
        repaint();
    }

    private void mouseMovedEvent(MouseEvent e){
        mouse = relativeToAbsolute(e.getPoint());
        repaint();
    }

    private void mousedDraggedEvent(MouseEvent e){
        mouse = relativeToAbsolute(e.getPoint());
        if(isRightClicked){
            translate.x = e.getX() - dragOrigine.x;
            translate.y = e.getY() - dragOrigine.y;
            repaint();
        }
        if(isLeftClicked){
            Point temp = relativeToAbsolute(e.getPoint());
            controller.glisser(temp);
        }
    }

    private void mousePressedEvent(MouseEvent e){
        switch (e.getButton()){
            case 1:
                Point temp = relativeToAbsolute(e.getPoint());
                controller.clic(temp);
                isLeftClicked = true;
                break;
            case 2:
                //Wheel click
                break;
            case 3:
                //Left click
                dragOrigine.x = e.getX() - translate.x;
                dragOrigine.y = e.getY() - translate.y;
                isRightClicked = true;
                this.setCursor(new Cursor(Cursor.MOVE_CURSOR));
                break;
        }
    }

    private void mouseReleasedEvent(MouseEvent e){
        switch (e.getButton()){
            case 1:
                //Left click
                controller.relacher();
                isRightClicked = false;
                break;
            case 2:
                //Wheel click
                break;
            case 3:
                //Right click
                isRightClicked = false;
                this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                break;
        }
    }

    private Point relativeToAbsolute(Point relative){
        Point absolute = new Point();
        absolute.x = relative.x - translate.x;
        absolute.y = relative.y - translate.y;
        return zoomOut(absolute);
    }

    private int zoom(int value) {
        return (int) (value * zoomFactor);
    }

    private Point zoomOut(Point point) {
        int x = (int) (point.x / zoomFactor);
        int y = (int) (point.y / zoomFactor);
        return new Point(x, y);
    }

    @Override
    public void paint(Graphics gb) {
        super.paintComponent(gb);
        Graphics2D g = (Graphics2D) gb;
        g.setColor(Color.gray);
        int sizeX = this.getWidth()-1;
        int sizeY = this.getHeight()-1;
        int grid_size = controller.getGridSize();
        int grid = zoom(grid_size);
        int gridOffX = translate.x % grid;
        int gridOffY = translate.y % grid;
        for(int i = -grid + gridOffX; i < sizeX + grid; i += grid){
            for(int j = -grid + gridOffY; j < sizeY + grid; j+= grid){
                g.drawRect(i, j, grid, grid);
            }
        }
        g.setColor(Color.DARK_GRAY);
        g.drawLine(translate.x, 0, translate.x, sizeY);
        g.drawLine(0, translate.y, sizeX, translate.y);
        AffineTransform oldTransform = g.getTransform();
        g.translate(translate.x, translate.y);
        g.scale(zoomFactor, zoomFactor);
        this.controller.paintCanevas(g, mouse);
        g.setTransform(oldTransform );
        g.setColor(Color.black);
        g.drawString(controller.getStatusString(), 10, 20);
    }

    @Override
    public void update() {
        repaint();
    }
}
