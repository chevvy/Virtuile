package Vues;

import MVC.Controller;
import MVC.Observer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Canvas extends JPanel implements Observer{

    private Controller controller;
    private Point dragOrigine;
    private Point translate;
    private boolean isGrilleMagnetiqueActive = false;
    private boolean isRightClicked = false;
    private boolean isLeftClicked = false;
    private int grid_size = 50;

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
        if(isRightClicked){
            translate.x = e.getX() - dragOrigine.x;
            translate.y = e.getY() - dragOrigine.y;
            repaint();
        }
        if(isLeftClicked){
            Point temp = relativeToAbsolute(e.getPoint());
            controller.glisser(temp.x, temp.y);
        }
    }

    private void mousePressedEvent(MouseEvent e){
        switch (e.getButton()){
            case 1:
                Point temp = relativeToAbsolute(e.getPoint());
                controller.clic(temp.x, temp.y);
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
        if (isGrilleMagnetiqueActive){
            int offX = absolute.x % grid_size;
            int offY = absolute.y % grid_size;
            absolute.x = offX < grid_size / 2 ? absolute.x - offX : absolute.x - offX + grid_size;
            absolute.y = offY < grid_size / 2 ? absolute.y - offY : absolute.y - offY + grid_size;
        }
        return absolute;
    }

    public void setGrilleMagnetiqueActive(boolean active){
        this.isGrilleMagnetiqueActive = active;
    }

    public void setGrilleSize(int size){
        this.grid_size = size;
        repaint();
    }

    public int getGrilleSize(){
        return this.grid_size;
    }

    @Override
    public void paint(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.gray);
        int sizeX = this.getWidth()-1;
        int sizeY = this.getHeight()-1;
        int gridOffX = translate.x % grid_size;
        int gridOffY = translate.y % grid_size;
        for(int i = -grid_size + gridOffX; i < sizeX + grid_size; i += grid_size){
            for(int j = -grid_size + gridOffY; j < sizeY + grid_size; j+= grid_size){
                g.drawRect(i, j, grid_size, grid_size);
            }
        }
        g.setColor(Color.DARK_GRAY);
        g.drawLine(translate.x, 0, translate.x, sizeY);
        g.drawLine(0, translate.y, sizeX, translate.y);
        g.translate(translate.x, translate.y);
        this.controller.paintCanevas(g);
    }

    @Override
    public void update() {
        repaint();
    }
}
