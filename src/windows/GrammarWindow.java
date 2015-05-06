/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package windows;

import inka.Inka;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author me
 */
public class GrammarWindow extends JFrame {
    
    protected JPanel grammar = new JPanel();
    protected JPanel buttonPanel = new JPanel();
    //private MainWindow mainWindow;
        
    
    public GrammarWindow() {
        this.grammar.setLayout(new BorderLayout());
        this.buttonPanel.setLayout(new FlowLayout());
        
        createGrammarWindow();
    }
    
    private void createGrammarWindow() {
        addButton2("Menu", buttonPanel);
        addButton2("Ask", buttonPanel);
        addButton2("Card manager", buttonPanel);
        grammar.add(buttonPanel, BorderLayout.SOUTH);
        //panels.add(grammar);
    }
    
    private static void addButton2(String text, Container container){
        JButton button = new JButton(text);
        button.addActionListener(new changePanelListener());
        //button.setSize(20, 10);
        //button.setAlignmentX(Component.CENTER_ALIGNMENT);
        //container.add(Box.createVerticalStrut(20));
        container.add(button);
    }
    
    private static class changePanelListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String buttonText = e.getActionCommand();
            System.out.println(e.getActionCommand());
            switch (buttonText) {
                case "Menu":
                    Inka.getWindow().changePanel(0);
                    Inka.getWindow().setTitle(buttonText);
                case "Ask":
                    //startAskingFromUser();
                    Inka.getWindow().setTitle(buttonText);
                    break;
                case "Card Manager":
                    Inka.getWindow().changePanel(1);
                    Inka.getWindow().setTitle(buttonText);
                    break;
                case "Grammar":
                    Inka.getWindow().changePanel(2);
                    Inka.getWindow().setTitle(buttonText);
                    break;
            }
        }
    }
}