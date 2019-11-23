package Domaine;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Tuile{
    // la classe tuile est un extend de polygon
    // elle contient, sa taille, ses points,
    // elle a un getter et un setter pour sa taille
    // ceux-ci viennent affecter ses points ? Pas sûr
    private Polygon polygone;
    private int length;
    private int height;


    Tuile(ArrayList<Point> listePoints){
        int[] coords_x = listePoints.stream().mapToInt(point -> point.x).toArray();
        int[] coords_y = listePoints.stream().mapToInt(point -> point.y).toArray();
        polygone = new Polygon(coords_x, coords_y, listePoints.size());
        length = polygone.xpoints.length;
        height = polygone.ypoints.length;
    }

    public Polygon getPolygone() {
        return polygone;
    }

    public int getLength() {
        return length;
    }

    public int getHeight() {
        return height;
    }

    public void setPolygone(Polygon polygone) {
        this.polygone = polygone;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
