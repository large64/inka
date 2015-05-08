
package windows;
import windows.cardManager.ManageCardsWindow;
import inka.Inka;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class MainWindow extends JFrame {
    
    private JPanel panel = new JPanel();
    private final static List<JPanel> panels = new ArrayList<>();
    
    public MainWindow(){
        setTitle("Main Window");
        setSize(800,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        // Create panels that represents states and layouts of the only one window we have.        
        // Main menu panel
        JPanel mainMenuJPanel = new JPanel();
        mainMenuJPanel.setLayout(new BoxLayout(mainMenuJPanel, BoxLayout.Y_AXIS));        
        
        JLabel title = new JLabel("Inka");
        title.setFont(new Font("Verdana", 1, 30));
        title.setAlignmentX(CENTER_ALIGNMENT);
        mainMenuJPanel.add(title);
        
        addButton("Ask", mainMenuJPanel);
        addButton("Grammar", mainMenuJPanel);
        addButton("Card Manager", mainMenuJPanel);
        
        panels.add(mainMenuJPanel);
        
        //Asking window panel
        panels.add(new AskingWindow());        
        
        //Grammar window panel
        panels.add(new GrammarWindow());
        
        //Card manager window panel
        panels.add(new ManageCardsWindow());
    }
    
    public void changePanel(int numberOfPanel) {
        this.getContentPane().removeAll();
        this.panel = panels.get(numberOfPanel);
        this.add(this.panel);
        // Call validate to add new content
        this.validate();
        // Call repaint to refresh content
        this.repaint();
    }
    
    private static class changePanelListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String buttonText = e.getActionCommand();
            System.out.println(e.getActionCommand());
            switch (buttonText) {
                case "Menu":
                    Inka.getWindow().changePanel(0);
                    Inka.getWindow().setTitle("Inka - " + buttonText);
                    break;
                case "Ask":
                    Inka.getWindow().changePanel(1);
                    Inka.getWindow().setTitle("Inka - " + buttonText);
                    break;
                case "Grammar":
                    Inka.getWindow().changePanel(2);
                    Inka.getWindow().setTitle("Inka - " + buttonText);
                    break;
                case "Card manager":
                    Inka.getWindow().changePanel(3);
                    Inka.getWindow().setTitle("Inka - " + buttonText);
                    break;
            }
        }
    }
    
    private static void addButton(String text, Container container){
        JButton button = new JButton(text);
        button.addActionListener(new MainWindow.changePanelListener());
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        container.add(Box.createVerticalStrut(20));
        container.add(button);
    }
}
