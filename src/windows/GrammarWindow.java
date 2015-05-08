/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package windows;

import inka.Inka;
import inka.database.DatabaseHandler;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import sentence.Grammar;

/**
 *
 * @author me
 */
public final class GrammarWindow extends JPanel {
    
    private static final JLabel jlabel = new JLabel();
    private DatabaseHandler dataBaseHandler;
    private List<Grammar> grammarList;
    private static int buttonPressed;

    public GrammarWindow() {
        this.setLayout(new GridLayout());
        this.setDataBaseHandler(new DatabaseHandler());
        this.setGrammarList(this.getDataBaseHandler().selectGrammar("SELECT * FROM grammar"));
        createGrammarWindow();
    }
    
    private void createGrammarWindow() {
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new FlowLayout());
        JPanel grammar = new JPanel();
        grammar.setLayout(new BorderLayout());
        JPanel wordPanel = new JPanel();
        wordPanel.setLayout(new FlowLayout());
        
        addButton("Menu", menuPanel, new changeWindowlListener(), "Menu");
        addButton("Ask", menuPanel, new changeWindowlListener(), "Ask");
        addButton("Card manager", menuPanel, new changeWindowlListener(), "Card manager");
        
        Random random = new Random();
        int position = random.nextInt(grammarList.size());
        
        jlabel.setText(grammarList.get(position).getSentence());
        grammar.add(jlabel);
        
        addButton(grammarList.get(position).getOpt1(), wordPanel, new wordListener(wordPanel, grammarList.get(position)), "0");
        addButton(grammarList.get(position).getOpt2(), wordPanel, new wordListener(wordPanel, grammarList.get(position)), "1");
        addButton(grammarList.get(position).getOpt3(), wordPanel, new wordListener(wordPanel, grammarList.get(position)), "2");
        /*
        for (int i = 0; i < wordPanel.getComponentCount(); ++i) {
            if (wordPanel.getComponent(i).getName().equals(Integer.toString(grammarList.get(position).getCorrect()))) {
                wordPanel.getComponent(i).setBackground(Color.green);
            }
        }*/
        
        grammar.add(menuPanel, BorderLayout.NORTH);
        grammar.add(wordPanel, BorderLayout.SOUTH);
        this.add(grammar);
    }
    
    //Get text to set button text, container, where to add the button and which listener to call
    private static void addButton(String text, Container container, ActionListener actionListener, String name){
        JButton button = new JButton(text);
        button.setName(name);
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
    
    //Later will check the solution(correct word) and replace "..." to the choosed word
    private static final class wordListener implements ActionListener {
        JPanel wordPanel;
        Grammar grammar;

        public JPanel getWordPanel() {
            return wordPanel;
        }

        public void setWordPanel(JPanel wordPanel) {
            this.wordPanel = wordPanel;
        }

        public Grammar getGrammar() {
            return grammar;
        }

        public void setGrammar(Grammar grammar) {
            this.grammar = grammar;
        }
        
        public wordListener(JPanel wordPanel, Grammar grammar) {
            this.setGrammar(grammar);
            this.setWordPanel(wordPanel);
        }

        @Override
        public void actionPerformed(ActionEvent e) {          
            String[] sentence;
            /*sentence = jlabel.getText().split("[.]{3}");
            System.out.println(sentence[0]);
            String word = e.getActionCommand();
            //jlabel.setText(sentence[0] + word + sentence[1]);
            jlabel.setText(sentence[0] + word);*/
            
            for (int i = 0; i < wordPanel.getComponentCount(); ++i) {
                if (wordPanel.getComponent(i).getName().equals(Integer.toString(grammar.getCorrect()))) {
                    sentence = jlabel.getText().split("[.]{3}");
                    System.out.println(sentence[0]);
                    String word = e.getActionCommand();
                    jlabel.setText(sentence[0] + word + sentence[1]);
                    wordPanel.getComponent(i).setBackground(Color.green);
                } else {
                    wordPanel.getComponent(i).setBackground(Color.red);
                }
            }
        }
    }
    
    public DatabaseHandler getDataBaseHandler() {
        return dataBaseHandler;
    }

    public void setDataBaseHandler(DatabaseHandler dataBaseHandler) {
        this.dataBaseHandler = dataBaseHandler;
    }
    
    public List<Grammar> getGrammarList() {
        return grammarList;
    }

    public void setGrammarList(List<Grammar> grammarList) {
        this.grammarList = grammarList;
    }
}