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
    
    public CardDeck(){
        cards = new HashMap();
        easyCards = new HashMap();
        hardCards = new HashMap();
        harderCards = new HashMap();
        easyCardNumber = 6;
        hardCardNumber = 2;
        harderCardNumber = 1;
    }
    
    public CardDeck(int easyCardNumber, int hardCardNumber, int harderCardNumber){
        cards = new HashMap();
        easyCards = new HashMap();
        hardCards = new HashMap();
        harderCards = new HashMap();
        this.easyCardNumber = easyCardNumber;
        this.hardCardNumber = hardCardNumber;
        this.harderCardNumber = harderCardNumber;
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
    public void loadEasyCards(){
        if(cards.size() >= easyCardNumber){
            // if the size is the same with the number put all the cards into the easycards
            if(cards.size() == easyCardNumber){
                easyCards.putAll(cards);
                WriteOutEasyCards();
            }else{
                // if
                String[] words = new String[cards.size()];
                int i = 0;
                for(String key : cards.keySet()){
                    words[i] = key;
                    ++i;
                }
                String[] readyWords = new String[easyCardNumber];
                readyWords = ChooseRandomWords(words);
                // now i have the different word and I put them into the easy
                // word[i] == dog
                for(int k = 0; k < readyWords.length; ++k){
                    String english = readyWords[k];
                    String hungarian = cards.get(readyWords[k]);
                    easyCards.put(english, hungarian);
                }
                // Delete this function!
                WriteOutEasyCards();
            }
        }else{
            System.out.println("Sorry, you do not have enough card, please put at least " + easyCardNumber + " cards");
        }
    }
    
    // Delete this function!
    private void WriteOutEasyCards()
    {
        System.out.println("EasyCards");
        for(String key : easyCards.keySet()){
            System.out.println("key : " + key + "\tvalue : " + easyCards.get(key));
        }
    }
    
    // Choose dirrent words
    private String[] ChooseRandomWords(String[] words){
        String[] choosenWords = new String[easyCardNumber];
        
        Random rnd = new Random();
        int myRandomNumber;
        int[] generatedRandomNumbers = new int[easyCardNumber];
        int j = 0;
        while(j < easyCardNumber){
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
    public void setEaseCardNumber(int number){
        easyCardNumber = number;
    }
    
    public void setHardCardNumber(int number){
        hardCardNumber = number;
    }
    
    public void setHarderCardNumber(int number){
        harderCardNumber = number;
    }
}
