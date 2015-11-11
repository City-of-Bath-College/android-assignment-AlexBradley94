package com.example.bra11124543.questionnaire;

/**
 * Created by bra11124543 on 30/09/2015.
 */

public class QuestionObject {

    private String question;
    private boolean answer;
    private int picture;
    private String optionone;
    private String optiontwo;

    public QuestionObject(String question, boolean answer, int picture, String optionone, String optiontwo) {
        this.question = question;
        this.answer = answer;
        this.picture = picture;
        this.optionone = optionone;
        this.optiontwo = optiontwo;
    }

    public String getQuestion(){
        return question;
    }

    public boolean isAnswer(){
        return answer;
    }

    public int getPicture(){
        return picture;
    }

    public String getOptionone (){
        return optionone;
    }

    public String getOptiontwo (){
        return optiontwo;
    }
}
