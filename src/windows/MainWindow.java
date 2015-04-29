
package windows;
import inka.Inka;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JFrame;


public class MainWindow extends JFrame {
    
    private final JPanel buttonsPanel = new JPanel();
    private JPanel panel = new JPanel();
    private final static List<JPanel> panels = new ArrayList<>();
    
    public MainWindow(){
        setTitle("Main Window");
        setSize(300,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        // Create panels that represents states and layouts of the only one window we have.
        // Main menu panel
        JPanel mainMenuJPanel = new JPanel();
        mainMenuJPanel.setLayout(new BoxLayout(mainMenuJPanel, BoxLayout.Y_AXIS));
        addButton("Card Manager", mainMenuJPanel);
        addButton("Ask", mainMenuJPanel);
        panels.add(mainMenuJPanel);
        
        JPanel testPanel = new JPanel();
        testPanel.setLayout(new BoxLayout(testPanel, BoxLayout.Y_AXIS));
        addButton("Test", testPanel);
        panels.add(testPanel);
        
        // this is the panel for Ask
        CreateAskPanel();
    }
    
    private void CreateAskPanel(){
        JPanel askPanel = new JPanel();
        //askPanle.setLayout()
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
            if (buttonText.equals("Card Manager")) {
                    Inka.getWindow().changePanel(1);
            }
            else if(buttonText.equals("Ask")){
                startAskingFromUser();
            }
        }
        
    }
    
    private static void startAskingFromUser(){
        
    }
    
    private static void addButton(String text, Container container){
        JButton button = new JButton(text);
        button.addActionListener(new MainWindow.changePanelListener());
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        container.add(Box.createVerticalStrut(20));
        container.add(button);
    }
   
}
