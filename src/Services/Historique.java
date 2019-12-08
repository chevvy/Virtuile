package Services;

import Domaine.GestionnaireRevetements;
import Domaine.Plan;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Historique {
    private static List<Object> historique;
    private static int index;

    public static void setSingle_instance()
    {
        if (historique == null) {
            historique = new ArrayList();
            historique.add(new Plan());
            index = 0;
        }
    }

    public static void addState(Plan plan){
        setSingle_instance();
        if (index != historique.size()-1){
            List<Object> temp = new ArrayList();
            temp.add(new Plan());
            temp.addAll(historique.subList(0, index));
            historique = temp;
        }
        try{
            Object copy = ObjectCloner.deepCopy(plan);
            historique.add(copy);
            index = historique.size()-1;
        } catch (Exception e){

        }
    }

    public static Plan goForward(){
        setSingle_instance();
        index++;
        return (Plan)historique.get(index);
    }

    public static Plan goBackward(){
        setSingle_instance();
        index--;
        return (Plan)historique.get(index);
    }

    public static boolean canGoForward(){
        setSingle_instance();
        return index != historique.size()-1;
    }

    public static boolean canGobackward(){
        setSingle_instance();
        return index != 0;
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
