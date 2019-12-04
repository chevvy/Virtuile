package Services;

import Domaine.GestionnaireRevetements;
import Domaine.Plan;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Historique {

    private static Historique single_instance = null;

    private Historique()
    {
        single_instance = new Historique();
    }

    public static Historique getInstance()
    {
        if (single_instance == null)
            single_instance = new Historique();
        return single_instance;
    }

    public static SaveBundle loadProject(String path) {
        SaveBundle bundle = new SaveBundle();
        try {
            FileInputStream fichier = new FileInputStream(path);
            ObjectInputStream ois = new ObjectInputStream(fichier);
            bundle = (SaveBundle) ois.readObject();
        } catch (java.io.IOException e) {
        } catch (ClassNotFoundException e) {
        }
        return bundle;
    }

    public static void saveProject(Plan plan, GestionnaireRevetements gestionnaireRevetements, String path) {
        SaveBundle bundle = new SaveBundle(plan, gestionnaireRevetements);
        try {
            FileOutputStream fos = new FileOutputStream(path);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(bundle);
            oos.flush();
            oos.close();
        } catch (java.io.IOException e) {
        }
    }
}
