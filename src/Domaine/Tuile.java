package Domaine;
import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class Tuile implements Serializable {
    // la classe tuile est un extend de polygon
    // elle contient, sa taille, ses points,
    // elle a un getter et un setter pour sa taille
    // ceux-ci viennent affecter ses points ? Pas sûr
    private Polygon polygone;
    private int length;
    private int height;
    private int dimensionMin = 10;
    private Point pointMilieu;
    private Surface surface;
    private GestionnaireRevetements gestionnaireRevetement;


    Tuile(ArrayList<Point> listePoints) {
        int[] coords_x = listePoints.stream().mapToInt(point -> point.x).toArray();
        int[] coords_y = listePoints.stream().mapToInt(point -> point.y).toArray();
        polygone = new Polygon(coords_x, coords_y, listePoints.size());
        length = (int) polygone.getBounds().getMaxX() - (int) polygone.getBounds().getMinX();
        height = (int) polygone.getBounds().getMaxY() - (int) polygone.getBounds().getMinY();
        this.pointMilieu = new Point((int) polygone.getBounds().getMinX(),(int) polygone.getBounds().getMinY());
    }

    Tuile(Polygon polygon){
        this.polygone = polygon;
        length = (int)polygone.getBounds().getMaxX() - (int)polygone.getBounds().getMinX();
        height = (int)polygone.getBounds().getMaxY() - (int)polygone.getBounds().getMinY();
        this.pointMilieu = new Point((int) polygone.getBounds().getMinX(),(int) polygone.getBounds().getMinY());
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
        length = (int)polygone.getBounds().getMaxX() - (int)polygone.getBounds().getMinX();
        height = (int)polygone.getBounds().getMaxY() - (int)polygone.getBounds().getMinY();
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean estTropPetite(int DimensionMin){
        return this.getHeight() <= DimensionMin || this.getLength() <= DimensionMin;
    }
}
