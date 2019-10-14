package Vues;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class Plan extends JPanel {

    public Plan(){

    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.RED);
        g.fill3DRect(100,100,100,100, true);
    }
}
