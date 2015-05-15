package Card;

import java.util.*;

public class CardDeck {
    
    HashMap<String, String> cards;
    // these are the numbers for the user that how many cards must have been asked
    int easyCardNumber;
    int hardCardNumber;
    int harderCardNumber;
    // variables for the easy, hard and harder maps
    HashMap<String, String> easyCards;
    HashMap<String, String> hardCards;
    HashMap<String, String> harderCards;
    
    boolean studying;
    
    public CardDeck(){
        cards = new HashMap();
        easyCards = new HashMap();
        hardCards = new HashMap();
        harderCards = new HashMap();
        easyCardNumber = 1;
        hardCardNumber = 2;
        harderCardNumber = 6;
        studying = false;
    }
    
    public CardDeck(int easyCardNumber, int hardCardNumber, int harderCardNumber){
        cards = new HashMap();
        easyCards = new HashMap();
        hardCards = new HashMap();
        harderCards = new HashMap();
        this.easyCardNumber = easyCardNumber;
        this.hardCardNumber = hardCardNumber;
        this.harderCardNumber = harderCardNumber;
        studying = false;
    }
    
    // when the user wants the program to ask the words
    public void letsStudy(){
        studying = true;
    }
    
    // when the user wants the program to stop asking the words
    public void stopStudying(){
        studying = false;
    }
    
    public boolean isStudying(){
        return studying;
    }
    
    public HashMap<String, String> getEasyCards(){
        return easyCards;
    }
    
    public HashMap<String, String> getHardCards(){
        return hardCards;
    }
    
    public HashMap<String, String> getHarderCards(){
        return harderCards;
    }
    
    // delete this function
    public void WriteOutEverything(){
        System.out.println("Cards!");
        for(String key : cards.keySet()){
            String value = (String)cards.get(key);
            System.out.println("key : " + key + "\tvalue : " + value);
        }
    }
    
    // put cards into easy cards
    // if there is no enough card the user has to add more cards 
    public void loadHarderCards(){
        if(cards.size() >= harderCardNumber){
            // if the size is the same with the number put all the cards into the easycards
            if(cards.size() == harderCardNumber){
                harderCards.putAll(cards);
                WriteHarderCards();
            }else{
                // String array is just help to choose cards
                String[] words = new String[cards.size()];
                int i = 0;
                for(String key : cards.keySet()){
                    words[i] = key;
                    ++i;
                }
                String[] readyWords;
                readyWords = ChooseRandomWords(words);
                // now i have the different words int readyWords and I put them into the easyCards
                for(int k = 0; k < readyWords.length; ++k){
                    String english = readyWords[k];
                    String hungarian = cards.get(readyWords[k]);
                    harderCards.put(english, hungarian);
                }
                // Delete this function!
                WriteHarderCards();
            }
        // if there is not enough card, tell user to upload more cards
        }else{
            System.out.println("Sorry, you do not have enough card, please put at least " + harderCardNumber + " cards");
        }
    }
    
    // Delete this function!
    public void WriteHarderCards()
    {
        System.out.println("HarderCards");
        for(String key : harderCards.keySet()){
            System.out.println("key : " + key + "\tvalue : " + harderCards.get(key));
        }
    }
    
    // Choose random words, this is just a helper function
    private String[] ChooseRandomWords(String[] words){
        String[] choosenWords = new String[harderCardNumber];
        
        Random rnd = new Random();
        int myRandomNumber;
        int[] generatedRandomNumbers = new int[harderCardNumber];
        int j = 0;
        while(j < harderCardNumber){
            myRandomNumber = rnd.nextInt(words.length);
            if(ThereIsNotInIntArray(myRandomNumber, generatedRandomNumbers)){
                choosenWords[j] = words[myRandomNumber];
                generatedRandomNumbers[j] = myRandomNumber;
                ++j;
            }
        }
        return choosenWords;
    }
    
    // check if the random number is in the array or not
    private boolean ThereIsNotInIntArray(int myRandomNumber, int[] generatedRandomNumbers){
        for(int i = 0; i < generatedRandomNumbers.length; ++i){
            if(generatedRandomNumbers[i] == myRandomNumber){
                return false;
            }
        }
        return true;
    }
    
    public void addCard(Card c){
        cards.put(c.getEnglish(), c.getHungarian());
    }
    
    public void removeKey(String english){
        cards.remove(english);
    }
    
    public String returnTheMeaning(String english){
        return (String)cards.get(english);
    }
    
    // set the numbers of the cards which mush have been asked
    // in the consturctor these will be auttomatically set for the user
    // but the user has the chance to set it another numbers
    public void setEasyCardNumber(int number){
        easyCardNumber = number;
    }
    
    public void setHardCardNumber(int number){
        hardCardNumber = number;
    }
    
    public void setHarderCardNumber(int number){
        harderCardNumber = number;
    }
    
    // get the card numbers
    public int getEasyCardNumber(){
        return easyCardNumber;
    }
    
    public int getHardCarNumber(){
        return hardCardNumber;
    }
    
    public int getHarderCardNumber(){
        return harderCardNumber;
    }
    
    // delete from hard, harder, easy
    public void removeCardFromHarderCards(String english){
        harderCards.remove(english);
    }
    
    public void removeCardFromHardCards(String english){
        hardCards.remove(english);
    }
    
    public void removeCardFromEasyCards(String english){
        easyCards.remove(english);
    }
    
    // add to hard, harder, easy
    public void addCardToHarderCards(String english, String hungarian){
        harderCards.put(english, hungarian);
    }
    
    public void addCardToHardCards(String english, String hungarian){
        hardCards.put(english, hungarian);
    }
    
    public void addCardToEasyCards(String english, String hungarian){
        easyCards.put(english, hungarian);
    }
    
    // check if the easy, hard or harder card is empty
    public boolean isHarderCardEmpty(){
        return harderCards.isEmpty();
    }
    
    public boolean isHardCardEmpty(){
        return hardCards.isEmpty();
    }
    
    public boolean isEasyCardEmpty(){
        return easyCards.isEmpty();
    }
}
