package inka;

import inka.database.DatabaseHandler;
import javax.swing.JFrame;
import windows.MainWindow;

public class Inka {

    /**
     * @param args the command line arguments
     */
    private static MainWindow window = null;
    
    public static void main(String[] args) {
        DatabaseHandler databaseHandler = new DatabaseHandler();
        window = new MainWindow();
        window.changePanel(0);
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static MainWindow getWindow() {
        return window;
    }
    
    public static void setWindow(MainWindow window) {
        Inka.window = window;
    }
}
