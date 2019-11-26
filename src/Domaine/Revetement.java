package Domaine;

import java.awt.*;

public class Revetement {
    private Color couleurTuile;
    private String couleurTuileText;
    private String nomDuRevetement;
    private String typeMateriauTuile;
    private String motifTuiles;
    private int nbTuilesBoite;
    // taille des tuiles par défaut
    private int longueurTuile;
    private int hauteurTuile;

    private int tailleDuCoulis = 2;
    private Color couleurCoulis = Color.WHITE;

    public Revetement(String nom) {
        this.nomDuRevetement = nom;
        this.typeMateriauTuile = "Céramique";
        this.couleurTuile = Color.RED;
        this.couleurTuileText = "Jaune";
        this.motifTuiles = "Installation droite";
        this.hauteurTuile = 20;
        this.longueurTuile = 50;
        this.nbTuilesBoite = 20;
    }

    public Revetement() {
        // revetement avec motif / taille par defaut (TBD)
        this.longueurTuile = 50;
        this.hauteurTuile = 20;
        this.nomDuRevetement = "Revêtement par défaut";
        this.typeMateriauTuile = "Céramique";
        this.couleurTuile = Color.RED;
        this.couleurTuileText = "Jaune";
        this.motifTuiles = "Installation droite";
        this.hauteurTuile = 20;
        this.longueurTuile = 50;
        this.nbTuilesBoite = 20;
        // revetement avec motif / taille par defaut (TBD)
    }

    //
    public Revetement(String nomDuRevetement, String typeMateriauTuile, Color couleurTuile, String couleurTuileText, String motifTuile,
                      int hauteurTuile, int longueurTuile, int nbTuilesBoite) {

        this.nomDuRevetement = nomDuRevetement;
        this.typeMateriauTuile = typeMateriauTuile;
        this.couleurTuile = couleurTuile;
        this.couleurTuileText = couleurTuileText;
        this.motifTuiles = motifTuile;
        this.hauteurTuile = hauteurTuile;
        this.longueurTuile = longueurTuile;
        this.nbTuilesBoite = nbTuilesBoite;
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

    public String getTypeMateriauTuile() {
        return typeMateriauTuile;
    }

    public String getCouleurTuileText() {
        return couleurTuileText;
    }

    public String getMotifTuiles() {
        return motifTuiles;
    }

    public int getNbTuilesBoite(){return nbTuilesBoite;}

    public void setNbTuilesBoite(int nbTuilesBoite){ this.nbTuilesBoite = nbTuilesBoite;}

}

