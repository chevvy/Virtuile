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
    private static List<Plan> historique;
    private static int index;

    public static void setSingle_instance()
    {
        if (historique == null) {
            historique = new ArrayList<>();
            historique.add(new Plan());
            index = 0;
        }
    }

    public static void addState(Plan plan){
        setSingle_instance();
        if (index != historique.size()-1){
            List<Plan> temp = new ArrayList<>();
            temp.addAll(historique.subList(0, index + 1));
            historique = temp;
        }
        try{
            Plan copy = (Plan)ObjectCloner.deepCopy(plan);
            historique.add(copy);
            index = historique.size()-1;
        } catch (Exception e){
            System.out.println(e.toString());
        }
    }

    public static Plan goForward(){
        setSingle_instance();
        index++;
        try{
            return (Plan)ObjectCloner.deepCopy(historique.get(index));
        } catch (Exception e){
            System.out.println(e.toString());
            return null;
        }
    }

    public static Plan goBackward(){
        setSingle_instance();
        index--;
        try{
            return (Plan)ObjectCloner.deepCopy(historique.get(index));
        } catch (Exception e){
            System.out.println(e.toString());
            return null;
        }
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
