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
     
     static int status;
     
     //buttons
     static JButton buttonHarder;
     static JButton buttonHard;
     static JButton buttonEasy;
     static JButton buttonShow;
     static JButton buttonAgain;
    
     // set the window - panels and buttons
     // load all the cards from database
    AskingWindow()
    {  
        SetPanelsAndButtons();
        cardDeck = new CardDeck();
        // load cards from database and convert to hashmap
        loadCardsFromDatabase();
        loadCardsFromListIntoHashMap();
        // put everything inot harder cards and write out 
        cardDeck.loadHarderCards();
        cardDeck.letsStudy();
        i = cardDeck.getHarderCardNumber();
        j = cardDeck.getHardCarNumber();
        k = cardDeck.getEasyCardNumber();
        status = 0;
        buttonAgain.setEnabled(false);
        progressOfAsking();
    }
    
    // this is the main algorithm
    // it will move tha cards from one set to another
    // it will decide  which card u r asked from
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
            buttonAgain.setEnabled(true);
            setShowButtonActivitation(false);
            setAskingButtonsActivitation(false);
        }
        
    }
    
    // it will ask u from the easiest card
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
    
    // it will ask u from the hard cards
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
    
    // it will ask u from the hardest cards
    // if u know, u wont be asked with that card
    private static void askFromHarderCards(){
        // ask one
        // :) -> put into harder
        // OK -> put into hard
        // :( -> remove card
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
        addButton2("Card Manager", menuPanel, new changeWindowlListener());
        
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
        AgainButtonListener againButtonListener = new AgainButtonListener();
        
        buttonHarder = new JButton(":(");
        buttonHard = new JButton("OK");
        buttonEasy = new JButton(":)");
        buttonShow = new JButton("Show");
        buttonAgain = new JButton("Again");
        
        buttonHarder.addActionListener(harderButtonListener);
        buttonHard.addActionListener(hardButtonListener);
        buttonEasy.addActionListener(easyButtonListener);
        buttonShow.addActionListener(showButtonListener);
        buttonAgain.addActionListener(againButtonListener);
        
        choosenButtonsPanel.add(buttonHarder);
        choosenButtonsPanel.add(buttonHard);
        choosenButtonsPanel.add(buttonEasy);
        choosenButtonsPanel.add(buttonShow);
        choosenButtonsPanel.add(buttonAgain);
        
        buttonPanel.add(Box.createVerticalStrut(70));
        buttonPanel.add(textFieldPanel, BorderLayout.NORTH);
        buttonPanel.add(choosenButtonsPanel, BorderLayout.SOUTH);
        this.add(buttonPanel);
    }
    
    // start asking test again for the user
    private  class AgainButtonListener implements ActionListener{
        
        @Override
        public void actionPerformed(ActionEvent e){
            loadCardsFromDatabase();
            loadCardsFromListIntoHashMap();
            // put everything inot harder cards and write out 
            cardDeck.loadHarderCards();
            cardDeck.letsStudy();
            i = cardDeck.getHarderCardNumber();
            j = cardDeck.getHardCarNumber();
            k = cardDeck.getEasyCardNumber();
            status = 0;
            buttonAgain.setEnabled(false);
            setShowButtonActivitation(true);
            setAskingButtonsActivitation(true);
            progressOfAsking();
        }
    }
    
    // show the hungarian meaning of the card
    private static class ShowButtonListener implements ActionListener{
        
        @Override
        public void actionPerformed(ActionEvent e){
            writeToHunTextField(thisIsWhatIaskhun);
            setShowButtonActivitation(false);
            setAskingButtonsActivitation(true);
        }
    }
    
    // write the hungarian meaning to the textfield
    private static void writeToHunTextField(String eng){
        englishText.setText(eng);
    }
    
    // write the hungarian meaning to the textfield
    private static void writeToEngTextField(String hun){
        hungarianText.setText(hun);
    }
    
    // set active or inactive the show button
    private static void setShowButtonActivitation(boolean l){
        buttonShow.setEnabled(l);
    }
    
    // set active or inactive the button : :(, OK, :)
    private static void setAskingButtonsActivitation(boolean l){
        buttonHarder.setEnabled(l);
        buttonHard.setEnabled(l);
        buttonEasy.setEnabled(l);
    }
    
    // after click on :( button it will put the card into harder cards
    private static class HarderButtonListener implements ActionListener{
        
        @Override
        public void actionPerformed(ActionEvent e){
             cardDeck.addCardToHarderCards(thisIsWhatIaskeng, thisIsWhatIaskhun);
             thisIsWhatIaskeng = "";
             thisIsWhatIaskhun = "";
             progressOfAsking();
        }
    }
    
    // after click on OK button it will put the card into hard cards
    private static class HardButtonListener implements ActionListener{
        
        @Override
        public void actionPerformed(ActionEvent e){
             cardDeck.addCardToHardCards(thisIsWhatIaskeng, thisIsWhatIaskhun);
             thisIsWhatIaskeng = "";
             thisIsWhatIaskhun = "";
             progressOfAsking();
        }
    }
    
    // after click on :) button it will put the card into easy cards
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

    // set the texts and their properties
    private void SetTextFieldsAndProperties() {
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
    
    // create button, add button to the container added in parameter
    // add button an own actionlistener
    private static void addButton(String text, Container container){
        JButton button = new JButton(text);
        button.addActionListener(new AskingWindow.chooseButtonListener());
        container.add(button);
        container.add(Box.createHorizontalStrut(70));
    }
    
    // load all the card from database
    private void loadCardsFromDatabase()
    {
        this.setCards(DatabaseHandler.selectCards("SELECT * FROM cards"));
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
    
    // get the cards
    public List<Card> getCards() {
        return cards;
    }
    
    // set the cards for the cards added in parameter
    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    // get the database handler
    public DatabaseHandler getDatabaseHandler() {
        return databaseHandler;
    }

    // set the databse handler
    public void setDatabaseHandler(DatabaseHandler databaseHandler) {
        this.databaseHandler = databaseHandler;
    }
    
    // add button to a cointainer added in parameter
    // add own actionlistener to button
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
                case "Card Manager":
                    Inka.getWindow().changePanel(3);
                    Inka.getWindow().setTitle("Inka - " + buttonText);
                    break;
            }
        }
    }
}
