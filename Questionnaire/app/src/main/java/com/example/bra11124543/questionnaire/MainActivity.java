package com.example.bra11124543.questionnaire;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.paperdb.Paper;

public class MainActivity extends AppCompatActivity {

    private Button buttonTrue;
    private Button buttonFalse;
    private TextView question;
    private ImageView flag;
    private TextView lblCount;

    private List<QuestionObject> questions;

    private QuestionObject currentQuestions;
    private int index;

    private String m_Text = "";

    private int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonTrue = (Button) findViewById(R.id.buttonTrue);
        buttonFalse = (Button) findViewById(R.id.buttonFalse);
        question = (TextView) findViewById(R.id.question);
        flag = (ImageView) findViewById(R.id.flag);
        lblCount = (TextView) findViewById(R.id.scoreCount);

        flag.setImageResource(R.drawable.flag_australia);

        index = 0;
        score = 0;

        buttonTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                determineButtonPress(true);
            }
        });

        buttonFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                determineButtonPress(false);
            }
        });

        generateQuestions();

        setUpQuestions();

        Paper.init(this);
    }

    private void generateQuestions() {

        questions = new ArrayList<>();

        questions.add(new QuestionObject("Burma has a capital city", false, R.drawable.flag_burma));
        questions.add(new QuestionObject("Guam has roads made of coral", true, R.drawable.flag_guam));
        questions.add(new QuestionObject("Nepal has the only non-rectangular flag", true, R.drawable.flag_nepal));
        questions.add(new QuestionObject("Saudi Arabia contains the most rivers in the world", false, R.drawable.flag_saudi));
        questions.add(new QuestionObject("The whole of monaco can fit inside central park in New York", true, R.drawable.flag_monaco));
        questions.add(new QuestionObject("Canada contains the most lakes in the world", true, R.drawable.flag_canada));
        questions.add(new QuestionObject("The Falkland Islands has the least sheep per capita in the world", false, R.drawable.flag_falklands));
        questions.add(new QuestionObject("The United States has the most people in prison in comparison to population", true, R.drawable.flag_usa));
        questions.add(new QuestionObject("The Hawaiian alphabet contains only 12 letters", true, R.drawable.flag_hawaii));
        questions.add(new QuestionObject("Australia is the continent with the most active volcanoes in the world", false, R.drawable.flag_australia));
        questions.add(new QuestionObject("French Fries are in fact from Belgium", true, R.drawable.flag_burma));
        questions.add(new QuestionObject("Croissant's are French", false, R.drawable.flag_burma));
        questions.add(new QuestionObject("In Africa you can buy beer brewed from bananas", true, R.drawable.flag_burma));
        questions.add(new QuestionObject("The country Brazil was names after the nut", false, R.drawable.flag_burma));
        questions.add(new QuestionObject("Paper came from Japan", false, R.drawable.flag_burma));
        questions.add(new QuestionObject("The Unites States shreds 7,000 tonnes of worn out currency per year", true, R.drawable.flag_usa));
    }

    private void setUpQuestions() {

        if (index == questions.size()) {
            Log.d("ALEX_APP", "ALL QUESTIONS ANSWERED");
            endGame();
        } else {
            currentQuestions = questions.get(index);
            question.setText(currentQuestions.getQuestion());
            flag.setImageResource(currentQuestions.getPicture());
            index++;
        }

    }

    private void determineButtonPress(boolean answer) {

        boolean expectedAnswer = currentQuestions.isAnswer();

        if (answer == expectedAnswer) {
            Toast.makeText(MainActivity.this, "Correct!", Toast.LENGTH_SHORT).show();
            score++;
            lblCount.setText("Score = " + score);
        } else {
            Toast.makeText(MainActivity.this, "Incorrect!", Toast.LENGTH_SHORT).show();
        }

        setUpQuestions();
    }

    private void endGame() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Congratulations!");
        final EditText input = new EditText(this);
        builder.setView(input);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                m_Text = input.getText().toString();

                HighScoreObject highScore = new HighScoreObject(m_Text, score, new Date().getTime());

                List<HighScoreObject> highScores = Paper.book().read("highScores", new ArrayList<HighScoreObject>());

                highScores.add(highScore);

                Paper.book().write("highscores", highScores);

                finish();

            }
        });

        builder.show();

    }
}







