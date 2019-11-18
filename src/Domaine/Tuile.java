package Domaine;
import java.awt.Polygon;
import java.awt.Point;
import java.util.ArrayList;
import java.awt.Polygon;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Tuile{
    // la classe tuile est un extend de polygon
    // elle contient, sa taille, ses points,
    // elle a un getter et un setter pour sa taille
    // ceux-ci viennent affecter ses points ? Pas sûr
    public Polygon polygone;


    public Tuile(List<Point> listePoints){
        int[] coords_x = listePoints.stream().mapToInt(point -> point.x).toArray();
        int[] coords_y = listePoints.stream().mapToInt(point -> point.y).toArray();
        polygone = new Polygon(coords_x, coords_y, listePoints.size());
    }
}
