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
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author me
 */
public class GrammarWindow extends JPanel {
    
    private static final JLabel jlabel = new JLabel();
    
    public GrammarWindow() {
        this.setLayout(new GridLayout());
        createGrammarWindow();
    }
    
    private void createGrammarWindow() {
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new FlowLayout());
        JPanel grammar = new JPanel();
        grammar.setLayout(new BorderLayout());
        JPanel wordPanel = new JPanel();
        wordPanel.setLayout(new FlowLayout());
        
        addButton("Menu", menuPanel, new changeWindowlListener());
        addButton("Ask", menuPanel, new changeWindowlListener());
        addButton("Card manager", menuPanel, new changeWindowlListener());
        
        jlabel.setText("Example sentence, which is ...");
        grammar.add(jlabel);
        
        addButton("stupido", wordPanel, new wordListener());
        addButton("wrong", wordPanel, new wordListener());
        addButton("good", wordPanel, new wordListener());
        
        grammar.add(menuPanel, BorderLayout.NORTH);
        grammar.add(wordPanel, BorderLayout.SOUTH);
        this.add(grammar);
    }
    
    //Get text to set button text, container, where to add the button and which listener to call
    private static void addButton(String text, Container container, ActionListener actionListener){
        JButton button = new JButton(text);
        button.addActionListener(actionListener);
        container.add(button);        
    }
    
    //This helps to navigate between the windows
    private static class changeWindowlListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String buttonText = e.getActionCommand();
            System.out.println(e.getActionCommand());
            switch (buttonText) {
                case "Menu":
                    Inka.getWindow().changePanel(0);
                    Inka.getWindow().setTitle(buttonText);
                    break;
                case "Ask":
                    Inka.getWindow().changePanel(1);
                    Inka.getWindow().setTitle(buttonText);
                    break;
                case "Grammar":
                    Inka.getWindow().changePanel(2);
                    Inka.getWindow().setTitle(buttonText);
                    break;
                case "Card manager":
                    Inka.getWindow().changePanel(3);
                    Inka.getWindow().setTitle(buttonText);
                    break;
            }
        }
    }
    
    //Later will check the solution(correct word) and replace "..." to the choosed word
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
}