/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sentence;

/**
 *
 * @author me
 */
public class Sentence {
    
    private int id;
    private String sentence;
    private String opt1;
    private String opt2;
    private String opt3;
    private int correct;
    
    public Sentence(int id, String sentence, String opt1, String opt2, String opt3, int correct) {
        this.id = id;
        this.sentence = sentence;
        this.opt1 = opt1;
        this.opt2 = opt2;
        this.opt3 = opt3;
        this.correct = correct;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }

    public String getOpt1() {
        return opt1;
    }

    public void setOpt1(String opt1) {
        this.opt1 = opt1;
    }

    public String getOpt2() {
        return opt2;
    }

    public void setOpt2(String opt2) {
        this.opt2 = opt2;
    }

    public String getOpt3() {
        return opt3;
    }

    public void setOpt3(String opt3) {
        this.opt3 = opt3;
    }

    public int getCorrect() {
        return correct;
    }

    public void setCorrect(int correct) {
        this.correct = correct;
    }
}
