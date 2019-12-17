package Vues;

import Services.MesureImperiale;
import Services.Outils;

import javax.swing.*;
import java.awt.event.ActionListener;

public class ImperialLabel extends JLabel {
    public JTextField pieds, pouce, fraction;
    public ImperialLabel(boolean full){
        this.setSize(200, 30);
        pieds = new JTextField();
        pieds.setSize(40, 30);
        pieds.setLocation(0, 0);
        if (full){
            this.add(pieds);

            JLabel labelPieds = new JLabel("pied");
            labelPieds.setSize(40, 30);
            labelPieds.setLocation(40, 0);
            this.add(labelPieds);
        }

        pouce = new JTextField();
        pouce.setSize(40, 30);
        pouce.setLocation(80, 0);
        this.add(pouce);

        JLabel labelPouce = new JLabel("pouce");
        labelPouce.setSize(40, 30);
        labelPouce.setLocation(120, 0);
        this.add(labelPouce);

        fraction = new JTextField();
        fraction.setSize(40, 30);
        fraction.setLocation(160, 0);
        this.add(fraction);
    }

    public void setVallues(double value){
        MesureImperiale mesure = Outils.metToImp(value);
        pieds.setText(mesure.pieds +"");
        pouce.setText(mesure.pouces +"");
        fraction.setText(mesure.num + "/" + mesure.denum);
    }

    public void setEmpty(){
        pieds.setText("");
        pouce.setText("");
        fraction.setText("");
    }

    public void setEditable(boolean editable){
        pieds.setEditable(editable);
        pouce.setEditable(editable);
        fraction.setEditable(editable);
    }

    public void addActionListenner(ActionListener listener){
        pieds.addActionListener(listener);
        pouce.addActionListener(listener);
        fraction.addActionListener(listener);
    }

    public double getValue(){
        int pieds = Integer.parseInt(this.pieds.getText());
        int pouce = Integer.parseInt(this.pouce.getText());
        String [] frac = this.fraction.getText().split("/");
        int num = Integer.parseInt(frac[0]);
        int denum = Integer.parseInt(frac[1]);
        int value = (int)Outils.impToMet(new MesureImperiale(pieds, pouce, num, denum));
        return value;
    }
}
