import MVC.Controller;
import Vues.MainWindow;

// test comment commit
import javax.swing.*;

public class Main {
    public static void main(String [] args){
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch(Exception ignored){}
        Controller controller = new Controller();
        MainWindow window = new MainWindow(controller);
    }
}
