package com.example.bra11124543.questionnaire;

/**
 * Created by bra11124543 on 21/10/2015.
 */
public class HighScoreObject {

    private String name;
    private int score;
    private long time;

    public HighScoreObject(){

    }

    public HighScoreObject(String name, int score, long time){
        this.name = name;
        this.score = score;
        this.time = time;
    }

    public String getName() {
        return name; //Hold information on the name
    }

    public int getScore() {
        return score; //Hold information on the score
    }

    public long getTime() {
        return time; //Hold information on the time
    }
}
