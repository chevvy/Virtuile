package Services;

import Domaine.GestionnaireRevetements;
import Domaine.Plan;

import java.io.Serializable;

public class SaveBundle implements Serializable {
    public Plan plan;
    public GestionnaireRevetements gestionnaireRevetements;

    SaveBundle(){

    }

    SaveBundle(Plan plan, GestionnaireRevetements gestionnaireRevetements){
        this.plan = plan;
        this.gestionnaireRevetements = gestionnaireRevetements;
    }

}
