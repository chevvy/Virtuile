package Services;

import Services.MesureImperiale;

import java.awt.*;
import java.util.Optional;

public class Outils {

    public static double impToMet(MesureImperiale frac){
        return (double)frac.getHuitiemes() * 25.4/8.0;
    }

    public static double impToMet(int huitiemes){
        return huitiemes * 25.4/8.0;
    }

    public static MesureImperiale metToImp(double met){
        return new MesureImperiale((int)Math.round(8.0*met/25.4)) ;
    }

    public static int getMaxValue(int[] listeNombre){
        int maxValue = listeNombre[0];
        for(int i=1;i < listeNombre.length;i++){
            if(listeNombre[i] > maxValue){
                maxValue = listeNombre[i];
            }
        }
        return maxValue;
    }
    public static int getMinValue(int[] listeNombre) {
        int minValue = listeNombre[0];
        for (int i = 1; i < listeNombre.length; i++) {
            if (listeNombre[i] < minValue) {
                minValue = listeNombre[i];
            }
        }
        return minValue;
    }

    public static Optional<Point> CalculIntersection(double m1, double b1, double m2, double b2) {

        if (m1 == m2) {
            return Optional.empty();
        }

        double x = (b2 - b1) / (m1 - m2);
        double y = m1 * x + b1;

        Point point = new Point();
        point.setLocation(x, y);
        return Optional.of(point);
    }




}
