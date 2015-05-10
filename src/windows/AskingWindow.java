package windows;

import Card.Card;
import Card.CardDeck;
import inka.Inka;
import inka.database.DatabaseHandler;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Box;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class AskingWindow extends JPanel{
    
     JPanel buttonPanel = new JPanel();
     JPanel choosenButtonsPanel = new JPanel();
     JPanel textFieldPanel = new JPanel();
     static JTextPane englishText = new JTextPane();
     static JTextPane hungarianText = new JTextPane();
     static CardDeck cardDeck;
     
     private List<Card> cards;
     private DatabaseHandler databaseHandler;
     
     static String thisIsWhatIaskeng;
     static String thisIsWhatIaskhun;
     
     static int i;
     static int j;
     static int k;
     
     static int step;
     
     static int status;
     
     //buttons
     static JButton buttonHarder;
     static JButton buttonHard;
     static JButton buttonEasy;
     static JButton buttonShow;
    
     // set the window - panels and buttons
     // load all the cards from database
    AskingWindow()
    {  
        SetPanelsAndButtons();
        cardDeck = new CardDeck();
        // load cards from database and convert to hashmap
        setDatabaseHandler(new DatabaseHandler());
        loadCardsFromDatabase();
        loadCardsFromListIntoHashMap();
        // put everything inot harder cards and write out 
        cardDeck.loadHarderCards();
        cardDeck.letsStudy();
        i = cardDeck.getHarderCardNumber();
        j = cardDeck.getHardCarNumber();
        k = cardDeck.getEasyCardNumber();
        status = 0;
        step = 0;
        progressOfAsking();
    }
    
    private static void progressOfAsking(){
        
        setShowButtonActivitation(true);
        setAskingButtonsActivitation(false);
        if(cardDeck.isStudying()){
            if(!cardDeck.isHarderCardEmpty() && i > 0){
                status = 1;
                askFromHarderCards();
                --i;
            }
            else if(!cardDeck.isHardCardEmpty() && j > 0){
                status = 2;
                if(!cardDeck.isHardCardEmpty())
                    askFromHardCards();
                --j;
            }
            else if(!cardDeck.isEasyCardEmpty() && k > 0){
                status = 3;
                if(!cardDeck.isEasyCardEmpty())
                    askFromEasyCards();
                --k;
            }
            else if(!cardDeck.isEasyCardEmpty()){
                status = 3;
                askFromEasyCards();
                i = cardDeck.getHarderCardNumber();
                j = cardDeck.getHardCarNumber();
                k = cardDeck.getEasyCardNumber();
            }
            else if(!cardDeck.isHarderCardEmpty()){
                status = 3;
                askFromHarderCards();
            }
            else if(!cardDeck.isHardCardEmpty()){
                status = 2;
                askFromHardCards();
            }
            
            if(cardDeck.isEasyCardEmpty() && cardDeck.isHardCardEmpty() && cardDeck.isHarderCardEmpty()){
                cardDeck.stopStudying();
            }
            writeToEngTextField(thisIsWhatIaskeng);
            writeToHunTextField("");
        }
        else{
            setShowButtonActivitation(false);
            setAskingButtonsActivitation(false);
        }
        // todo : give a sign to user 
        
    }
    
    private static void askFromEasyCards(){
        int size = cardDeck.getEasyCards().size();
        Random rand = new Random();
        int breaknumber = rand.nextInt(size);
        int i = 0;
        for(String key : cardDeck.getEasyCards().keySet()){
            thisIsWhatIaskeng = key;
            thisIsWhatIaskhun = cardDeck.getEasyCards().get(thisIsWhatIaskeng);
            if(i == breaknumber)
                break;
            ++i;
        }
        cardDeck.removeCardFromEasyCards(thisIsWhatIaskeng);
    }
    
    private static void askFromHardCards(){
        int size = cardDeck.getHardCards().size();
        Random rand = new Random();
        int breaknumber = rand.nextInt(size);
        int i = 0;
        for(String key : cardDeck.getHardCards().keySet()){
           thisIsWhatIaskeng = key;
           thisIsWhatIaskhun = cardDeck.getHardCards().get(thisIsWhatIaskeng);
           if(i == breaknumber)
               break;
           ++i;
        }
        cardDeck.removeCardFromHardCards(thisIsWhatIaskeng);
    }
    
    private static void askFromHarderCards(){
        // kerdezek egyet
        // :) -> belerakja az easybe es torli a harderbol
        // OK -> belerakja a hardba es torli a harderbol
        // :( -> ebbe hagyja
        int size = cardDeck.getHarderCards().size();
        Random rand = new Random();
        int breaknumber = rand.nextInt(size);
        int i = 0;
        for(String key : cardDeck.getHarderCards().keySet()){
            thisIsWhatIaskeng = key;
            thisIsWhatIaskhun = cardDeck.getHarderCards().get(thisIsWhatIaskeng);
            if(i == breaknumber)
                break;
            ++i;
        }
        cardDeck.removeCardFromHarderCards(thisIsWhatIaskeng);
    }

    // set the panels and the buttons
    private void SetPanelsAndButtons() {
        this.setLayout(new BorderLayout());
        buttonPanel.setLayout(new BorderLayout());
        choosenButtonsPanel.setLayout(new FlowLayout());
        textFieldPanel.setLayout(new BorderLayout());
        textFieldPanel.setPreferredSize(new Dimension(300,100));
        
        //Create menu panel with three menu button
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new FlowLayout());
        
        addButton2("Menu", menuPanel, new changeWindowlListener());
        addButton2("Grammar", menuPanel, new changeWindowlListener());
        addButton2("Card manager", menuPanel, new changeWindowlListener());
        
        this.add(menuPanel, BorderLayout.NORTH);
        
        SetTextFieldsAndProperties();
        
        this.add(Box.createVerticalStrut(300));
        textFieldPanel.add(englishText, BorderLayout.NORTH);
        textFieldPanel.add(hungarianText, BorderLayout.SOUTH);
        textFieldPanel.setBorder(new EmptyBorder(50, 0, 0, 0));
        
        //set the main panel borders
        this.setBorder(new EmptyBorder(10, 50, 50, 50));
        
        HarderButtonListener harderButtonListener = new HarderButtonListener();
        HardButtonListener hardButtonListener = new HardButtonListener();
        EasyButtonListener easyButtonListener = new EasyButtonListener();
        ShowButtonListener showButtonListener = new ShowButtonListener();
        
        /*addButton2(":(", choosenButtonsPanel, harderButtonListener);
        addButton2("OK", choosenButtonsPanel, hardButtonListener);
        addButton2(":)", choosenButtonsPanel, easyButtonListener);
        addButton2("Show", choosenButtonsPanel, showButtonListener);*/
        buttonHarder = new JButton(":(");
        buttonHard = new JButton("OK");
        buttonEasy = new JButton(":)");
        buttonShow = new JButton("Show");
        
        buttonHarder.addActionListener(harderButtonListener);
        buttonHard.addActionListener(hardButtonListener);
        buttonEasy.addActionListener(easyButtonListener);
        buttonShow.addActionListener(showButtonListener);
        
        choosenButtonsPanel.add(buttonHarder);
        choosenButtonsPanel.add(buttonHard);
        choosenButtonsPanel.add(buttonEasy);
        choosenButtonsPanel.add(buttonShow);
        
        
        buttonPanel.add(Box.createVerticalStrut(70));
        buttonPanel.add(textFieldPanel, BorderLayout.NORTH);
        buttonPanel.add(choosenButtonsPanel, BorderLayout.SOUTH);
        this.add(buttonPanel);
    }
    
    private static class ShowButtonListener implements ActionListener{
        
        @Override
        public void actionPerformed(ActionEvent e){
            writeToHunTextField(thisIsWhatIaskhun);
            setShowButtonActivitation(false);
            setAskingButtonsActivitation(true);
        }
    }
    
    private static void writeToHunTextField(String eng){
        englishText.setText(eng);
    }
    
    private static void writeToEngTextField(String hun){
        hungarianText.setText(hun);
    }
    
    private static void setShowButtonActivitation(boolean l){
        buttonShow.setEnabled(l);
    }
    
    private static void setAskingButtonsActivitation(boolean l){
        buttonHarder.setEnabled(l);
        buttonHard.setEnabled(l);
        buttonEasy.setEnabled(l);
    }
    
    private static class HarderButtonListener implements ActionListener{
        
        @Override
        public void actionPerformed(ActionEvent e){
             cardDeck.addCardToHarderCards(thisIsWhatIaskeng, thisIsWhatIaskhun);
             thisIsWhatIaskeng = "";
             thisIsWhatIaskhun = "";
             progressOfAsking();
        }
    }
    
    // OK
    private static class HardButtonListener implements ActionListener{
        
        @Override
        public void actionPerformed(ActionEvent e){
             cardDeck.addCardToHardCards(thisIsWhatIaskeng, thisIsWhatIaskhun);
             thisIsWhatIaskeng = "";
             thisIsWhatIaskhun = "";
             progressOfAsking();
        }
    }
    
    // :)
    private static class EasyButtonListener implements ActionListener{
        
        @Override
        public void actionPerformed(ActionEvent e){
            if(status == 1 || status == 2){
                cardDeck.addCardToEasyCards(thisIsWhatIaskeng, thisIsWhatIaskhun);
            }
            thisIsWhatIaskeng = "";
            thisIsWhatIaskhun = "";
             progressOfAsking();
        }
    }

    private void SetTextFieldsAndProperties() {
        //englishText.setText("english");
        //hungarianText.setText("hungarian");
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
    private void loadCardsFromListIntoHashMap()
    {
         // cards - List
        this.getCards().stream().forEach((i) -> {
            cardDeck.addCard(i);
         });
            
    }
    
    // just write out the content of my hashmap - DELETE THIS FUNCTION
    private void writeOut()
    {
        cardDeck.WriteOutEverything();
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
    
    private static void addButton2(String text, Container container, ActionListener actionListener){
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
}
