package Domaine;

import java.awt.*;
import java.util.*;

public class GestionnaireRevetements {

    public Revetement revetement;

    private Map<String, Revetement> mapRevetements = new HashMap<String, Revetement>(){{
                put( "Aucun Revêtement", new Revetement("Aucun revêtement"));
                put( "Revêtement 1", new Revetement("Revêtement 1"));
                put("Revêtement 2", new Revetement("Revêtement 2", "Béton", Color.RED, "Rouge",
                        "Installation en chevron", 13 , 13, 30));


    }};

    public GestionnaireRevetements(){
    }

    //Liste des revêtements
    public Map<String, Revetement> getMapRevetements(){return mapRevetements;}

    public void ajouterRevetement(String nomRevetement, Revetement revetement){
        this.mapRevetements.put(nomRevetement, revetement);
    }

    public Set getNomRevetements(){
        return mapRevetements.keySet();
    }

    public Map<String, String> getInfosRevetement(String nom) {
        //String revetement =
        Map<String, String> map = new HashMap<String, String>();
        map.put("Nom Revêtement", revetement.getNomDuRevetement());
        map.put("Type matériau", revetement.getTypeMateriauTuile());
        map.put("Couleur tuiles", revetement.getCouleurTuileText());
        map.put("Motif tuiles", revetement.getMotifTuiles());
        map.put("Hauteur tuiles", Integer.toString(revetement.getHauteurTuile()));
        map.put("Longueur tuiles", Integer.toString(revetement.getLongueurTuile()));
        map.put("nb. tuiles par boite", Integer.toString(revetement.getLongueurTuile()));
        return map;
    }

}
