package windows;

import Card.Card;
import Card.CardDeck;
import inka.Inka;
import inka.database.DatabaseHandler;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class AskingWindow extends JPanel{
    
     JPanel buttonPanel = new JPanel();
     JPanel choosenButtonsPanel = new JPanel();
     JPanel textFieldPanel = new JPanel();
     JTextPane englishText = new JTextPane();
     JTextPane hungarianText = new JTextPane();
     CardDeck cardDeck;
     
     private List<Card> cards;
     private DatabaseHandler databaseHandler;
    
    AskingWindow()
    {  
        SetPanelsAndButtons();
        cardDeck = new CardDeck();
    }

    // set the panels and the buttons
    private void SetPanelsAndButtons() {
        buttonPanel.setLayout(new BorderLayout());
        choosenButtonsPanel.setLayout(new FlowLayout());
        textFieldPanel.setLayout(new BorderLayout());
        textFieldPanel.setPreferredSize(new Dimension(300,100));
        
        SetTextFieldsAndProperties();
        
        this.add(Box.createVerticalStrut(300));
        textFieldPanel.add(englishText, BorderLayout.NORTH);
        textFieldPanel.add(hungarianText, BorderLayout.SOUTH);
        
        addButton(":(", choosenButtonsPanel);
        addButton("OK", choosenButtonsPanel);
        addButton(":)", choosenButtonsPanel);
        buttonPanel.add(Box.createVerticalStrut(70));
        buttonPanel.add(textFieldPanel, BorderLayout.NORTH);
        buttonPanel.add(choosenButtonsPanel, BorderLayout.SOUTH);
        this.add(buttonPanel);
    }

    private void SetTextFieldsAndProperties() {
        englishText.setText("english");
        hungarianText.setText("hungarian");
        englishText.setEditable(false);
        hungarianText.setEditable(false);
        // set all text to center
        StyledDocument doceng = englishText.getStyledDocument();
        StyledDocument dochung = hungarianText.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doceng.setParagraphAttributes(0, doceng.getLength(), center, false);
        dochung.setParagraphAttributes(0, dochung.getLength(), center, false);
    }
    
    private static class chooseButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String buttonText = e.getActionCommand();
            System.out.println(e.getActionCommand());
            switch (buttonText) {
                case "Card Manager":
                    
                    break;
            }
        }
        
    }
    
    private static void addButton(String text, Container container){
        JButton button = new JButton(text);
        button.addActionListener(new AskingWindow.chooseButtonListener());
        container.add(button);
        container.add(Box.createHorizontalStrut(70));
    }
    
    private void loadCardsFromDatabase()
    {
        this.setCards(this.getDatabaseHandler().select("SELECT * FROM cards"));
    }
    
    // put cards from list into hasmap
    private void LoadCardsFromListIntoHashMap()
    {
         // cards - List
        this.getCards().stream().forEach((i) -> {
            cardDeck.addCard(i);
         });
            
    }
    
    // just write out the content of my hashmap - DELETE THIS FUNCTION
    private void writeOut()
    {
        
    }
    
    

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public DatabaseHandler getDatabaseHandler() {
        return databaseHandler;
    }

    public void setDatabaseHandler(DatabaseHandler databaseHandler) {
        this.databaseHandler = databaseHandler;
    }
    
    
}
