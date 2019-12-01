package Domaine;

import java.awt.*;
import java.util.*;

public class GestionnaireRevetements {

    public Revetement revetement;
    private String revetementSelectionnee;

    private Map<String, Revetement> mapRevetements = new HashMap<String, Revetement>(){{
                put("Revêtement par défaut",  new Revetement());
                put("Revêtement 1", new Revetement("Revêtement 1", "Béton", Color.BLUE,
                        "Installation en chevron", 13 , 13, 30));
                put("Revêtement 2", new Revetement("Revêtement 2", "Terre cuite", Color.GRAY,
                "Installation en décallé", 20 , 30, 18));
                put("Aucun Revêtement", new Revetement("Aucun Revêtement", "Aucun",
                        Color.WHITE, "Aucun Motif", 0,0,0 ));

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
        Revetement revetementSelect = mapRevetements.get(nom);
        Map<String, String> map = new HashMap<String, String>();
        map.put("Nom Revêtement", revetementSelect.getNomDuRevetement());
        map.put("Type matériau", revetementSelect.getTypeMateriauTuile());
        map.put("Couleur tuiles", String.valueOf(revetementSelect.getCouleurTuile().getRGB()));
        map.put("Motif tuiles", revetementSelect.getMotifTuiles());
        map.put("Hauteur tuiles", Integer.toString(revetementSelect.getHauteurTuile()));
        map.put("Longueur tuiles", Integer.toString(revetementSelect.getLongueurTuile()));
        map.put("nb. tuiles par boite", Integer.toString(revetementSelect.getNbTuilesBoite()));
        return map;
    }

    public Revetement getRevetementFromNom(String nom){
        return mapRevetements.containsKey(nom) ? mapRevetements.get(nom) : mapRevetements.get("Revêtement par défaut");
    }

    public String getRevetementSelectionnee() {
        return revetementSelectionnee;
    }

    public void setRevetementSelectionnee(String revetementSelectionnee) {
        if(revetementSelectionnee != null) {
            this.revetementSelectionnee = revetementSelectionnee;
        }
    }

    public int getPositionDansArray(ArrayList<String> list, String elementRecherche){
        int i = 0;
        for(String element : list){
            if (element.equals(elementRecherche)){
                return i;
            }
            i++;
        }
        return i;
    }

    public int getPositionDansSet(Set list, String elementRecherche){
        int i = 0;
        for(Object element : list){
            if (element.equals(elementRecherche)){
                return i;
            }
            i++;
        }
        return i;
    }
}
