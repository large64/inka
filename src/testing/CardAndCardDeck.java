
package testing;

import Card.Card;
import Card.CardDeck;

public class CardAndCardDeck {
    public static void main(String[] args) {
        CardDeck d = new CardDeck();
        
        Card c1 = new Card("cat", "cica");
        Card c2 = new Card("dog", "kutya");
        Card c3 = new Card("mouse", "eger");
        Card c4 = new Card("rabbit", "nyul");
        Card c5 = new Card("apple", "alma");
        Card c6 = new Card("strawberry", "eper");
        Card c7 = new Card("pen", "toll");
        Card c8 = new Card("table", "tabla");
        Card c9 = new Card("task", "feladat");
        Card c10 = new Card("train", "vonat");
        Card c11 = new Card("orange", "narancs");
        
        d.addCard(c1);
        d.addCard(c2);
        d.addCard(c3);
        d.addCard(c4);
        d.addCard(c5);
        d.addCard(c6);
        /*d.addCard(c7);
        d.addCard(c8);
        d.addCard(c9);
        d.addCard(c10);
        d.addCard(c11);*/
        
        d.loadEasyCards();
        d.WriteOutEverything();
    }
}
