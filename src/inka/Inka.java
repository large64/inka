package inka;

import windows.MainWindow;

public class Inka {

    /**
     * @param args the command line arguments
     */
    private static MainWindow window = null;
    
    public static void main(String[] args) {
        window = new MainWindow();
        window.changePanel(0);
        window.setVisible(true);
    }

    public static MainWindow getWindow() {
        return window;
    }

    public static void setWindow(MainWindow window) {
        Inka.window = window;
    }
}
