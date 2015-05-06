package Card;

import java.util.*;

public class Card {
    
    String english;
    String hungarian;
    int id;
    
    public Card(String english, String hungarian) {
        this.english = english;
        this.hungarian = hungarian;
        this.id = 0;
    }
    
    public Card(int id, String english, String hungarian) {
        this.english = english;
        this.hungarian = hungarian;
        this.id = id;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
