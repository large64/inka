/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package windows;

import inka.Inka;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

/**
 *
 * @author me
 */
public class GrammarWindow extends JFrame {
    
    JPanel mainPanel = new JPanel();
    private JPanel grammar = new JPanel();
    private JPanel menuPanel = new JPanel();
    private JPanel wordPanel = new JPanel();
    private static JLabel jlabel = new JLabel();
    
    //private MainWindow mainWindow;
        
    
    public GrammarWindow() {
        this.mainPanel.setLayout(new GridLayout());
        this.grammar.setLayout(new BorderLayout());
        this.menuPanel.setLayout(new FlowLayout());
        this.wordPanel.setLayout(new FlowLayout());
        
        mainPanel.setBorder(new LineBorder(Color.BLACK));
        grammar.setBorder(new LineBorder(Color.BLACK));
        menuPanel.setBorder(new LineBorder(Color.BLACK));
        wordPanel.setBorder(new LineBorder(Color.BLACK));
        
        createGrammarWindow();
    }
    
    
    
    private void createGrammarWindow() {        
        addButton("Menu", menuPanel, 0);
        addButton("Ask", menuPanel, 0);
        addButton("Card manager", menuPanel, 0);
        
        jlabel.setText("Example sentence, which is ...");
        grammar.add(jlabel);
        
        addButton("bad", wordPanel, 1);
        addButton("wrong", wordPanel, 1);
        addButton("good", wordPanel, 1);
        
        grammar.add(menuPanel, BorderLayout.NORTH);
        grammar.add(wordPanel, BorderLayout.SOUTH);
        mainPanel.add(grammar);
    }
    
    //Get text to set button text, container, where to add the button and integer to which listener call
    private static void addButton(String text, Container container, int whichListener){
        JButton button = new JButton(text);
        if (whichListener == 0) {
            button.addActionListener(new changePanelListener());
            container.add(button);
        } else {
            button.addActionListener(new wordListener());
            container.add(button);
        }
        
    }
    
    private static class wordListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String[] sentence;
            sentence = jlabel.getText().split("[.]{3}");
            System.out.println(sentence[0]);
            String word = e.getActionCommand();
            jlabel.setText(sentence[0] + word);
        }
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