package Vues.Revetements;
import MVC.Controller;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class FrameRevetements extends JFrame{
    private Controller controller;
    private FrameRevetements frame;

    public FrameRevetements(Controller controller) {
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                controller.ClicMenu();
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                controller.ClicMenu();
            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {
                controller.ClicMenu();
            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                controller.ClicMenu();
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                controller.ClicMenu();
            }
        });

        this.setSize(800, 410);
        this.setLocation(100,100);
        this.setTitle("Propriétés des matériaux");
        this.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        PanneauChoixRevetements panelChoixRevetements = new PanneauChoixRevetements(controller);
        this.add(panelChoixRevetements, BorderLayout.WEST);
        PanneauInformationsRevetement panelInformationsRevetements = new PanneauInformationsRevetement(controller, this);
        this.add(panelInformationsRevetements, BorderLayout.EAST);

    }
}
