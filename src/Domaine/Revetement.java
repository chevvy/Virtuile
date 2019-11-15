package Domaine;

import java.awt.*;

public class Revetement {
    private Color couleurTuile;
    private String nomDuRevetement;
    // contient la liste des tuiles
    // contient le motif
    // methode : genererRevetement(boundingbox) -> selon le bound et le motif, modifie la liste des tuiles pour quelles soient de la bonne taille

    public Revetement(String nom){
        this.nomDuRevetement = nom;
    }


    public String getNom(){return this.nomDuRevetement;}
    public void setNom(String nom){this.nomDuRevetement = nom;}
}

