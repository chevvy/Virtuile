package Vues;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class Plan extends JPanel {

    public Plan(){

    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.gray);
        for(int i = 0; i < this.getWidth()-1; i += 50){
            for(int j = 0; j < this.getHeight()-1; j+=50){
                g.drawRect(i, j, 50, 50);
            }
        }
    }
}
