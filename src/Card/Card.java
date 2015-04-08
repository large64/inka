package Card;

import java.util.*;

public class Card {
    
    String english;
    String hungarian;
    
    public Card(String english, String hungarian) {
        this.english = english;
        this.hungarian = hungarian;
    }
    
    public String getEnglish() {
        return english;
    }
    
    public String getHungarian() {
        return hungarian;
    }
    
    public void setEnglish(String english) {
        this.english = english;
    }
    
    public void setHungarian(String hungarian) {
        this.hungarian = hungarian;
    }
    
}
