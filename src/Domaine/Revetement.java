package Domaine;

import java.awt.*;

public class Revetement {
    private Color couleurTuile;
    private String nomDuRevetement;
    // taille des tuiles par défaut
    private int longueurTuile;
    private int hauteurTuile;

    private int tailleDuCoulis = 2;
    private Color couleurCoulis = Color.WHITE;
    // contient le motif
    // methode : genererRevetement(boundingbox) -> selon le bound et le motif, modifie la liste des tuiles pour quelles soient de la bonne taille

    public Revetement(String nom){
        this.longueurTuile = 2;
        this.hauteurTuile = 1;
        this.nomDuRevetement = nom;
    }

    public Revetement(){
        // revetement avec motif / taille par defaut (TBD)
        this.longueurTuile = 15;
        this.hauteurTuile = 10;
        this.nomDuRevetement = "revetement par defaut";
    }

    public Color getCouleurTuile() {
        return couleurTuile;
    }

    public void setCouleurTuile(Color couleurTuile) {
        this.couleurTuile = couleurTuile;
    }

    public String getNomDuRevetement() {
        return nomDuRevetement;
    }

    public void setNomDuRevetement(String nomDuRevetement) {
        this.nomDuRevetement = nomDuRevetement;
    }

    public int getLongueurTuile() {
        return longueurTuile;
    }

    public void setLongueurTuile(int longueurTuile) {
        this.longueurTuile = longueurTuile;
    }

    public int getHauteurTuile() {
        return hauteurTuile;
    }

    public void setHauteurTuile(int hauteurTuile) {
        this.hauteurTuile = hauteurTuile;
    }

    public int getTailleDuCoulis() {
        return tailleDuCoulis;
    }

    public void setTailleDuCoulis(int tailleDuCoulis) {
        this.tailleDuCoulis = tailleDuCoulis;
    }

    public Color getCouleurCoulis() {
        return couleurCoulis;
    }

    public void setCouleurCoulis(Color couleurCoulis) {
        this.couleurCoulis = couleurCoulis;
    }
}

