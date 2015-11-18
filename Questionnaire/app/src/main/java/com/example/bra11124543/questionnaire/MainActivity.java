package com.example.bra11124543.questionnaire;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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

    private Toolbar toolbar;

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

        toolbar = (Toolbar) findViewById(R.id.tool_bar); // Attaching the layout to the toolbar object
        setSupportActionBar(toolbar);

        buttonTrue = (Button) findViewById(R.id.buttonTrue);
        buttonFalse = (Button) findViewById(R.id.buttonFalse);
        question = (TextView) findViewById(R.id.question);
        flag = (ImageView) findViewById(R.id.flag);
        lblCount = (TextView) findViewById(R.id.scoreCount);

        flag.setImageResource(R.drawable.flag_singapore);

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
        //True means left option (one), false is right option (two).
        questions.add(new QuestionObject("Which flag is this?", true, R.drawable.flag_singapore, "Singapore", "Indonesia"));
        questions.add(new QuestionObject("Which flag is this?", true, R.drawable.flag_jamaica, "Jamaica", "Kenya"));
        questions.add(new QuestionObject("Which flag is this?", false, R.drawable.flag_greenland, "Poland", "Greenland"));
        questions.add(new QuestionObject("Which flag is this?", true, R.drawable.flag_saudi, "Saudi Arabia", "Mauritania"));
        questions.add(new QuestionObject("Which flag is this?", false, R.drawable.flag_costarica, "Serbia", "Costa Rica"));
        questions.add(new QuestionObject("Which flag is this?", false, R.drawable.flag_tonga, "Switzerland", "Tonga"));
        questions.add(new QuestionObject("Which flag is this?", true, R.drawable.flag_egypt, "Egypt", "Angola"));
        questions.add(new QuestionObject("Which flag is this?", false, R.drawable.flag_poland, "Greenland", "Poland"));
        questions.add(new QuestionObject("Which flag is this?", true, R.drawable.flag_sweden, "Sweden", "Finland"));
        questions.add(new QuestionObject("Which flag is this?", false, R.drawable.flag_qatar, "Bahrain", "Qatar"));
        questions.add(new QuestionObject("Which flag is this?", true, R.drawable.flag_fiji, "Fiji", "Botswana"));
        questions.add(new QuestionObject("Which flag is this?", true, R.drawable.flag_ghana, "Ghana", "Cameroon"));
        questions.add(new QuestionObject("Which flag is this?", false, R.drawable.flag_zimbabwe, "Zambia", "Zimbabwe"));
        questions.add(new QuestionObject("Which flag is this?", false, R.drawable.flag_ireland, "Ivory Coast", "Ireland"));
        questions.add(new QuestionObject("Which flag is this?", true, R.drawable.flag_romania, "Romania", "Moldova"));
        questions.add(new QuestionObject("Which flag is this?", false, R.drawable.flag_canada, "Bosnia", "Canada"));
        questions.add(new QuestionObject("Which flag is this?", true, R.drawable.flag_andorra, "Andorra", "Chad"));
        questions.add(new QuestionObject("Which flag is this?", true, R.drawable.flag_uk, "UK", "Malta"));
        questions.add(new QuestionObject("Which flag is this?", true, R.drawable.flag_hungary, "Hungary", "Italy"));
        questions.add(new QuestionObject("Which flag is this?", true, R.drawable.flag_pakistan, "Pakistan", "New Caledonia"));
        questions.add(new QuestionObject("Which flag is this?", false, R.drawable.flag_vatican, "Dominica", "Vatican City"));
        questions.add(new QuestionObject("Which flag is this?", false, R.drawable.flag_albania, "Grenada", "Albania"));
        questions.add(new QuestionObject("Which flag is this?", true, R.drawable.flag_bulgaria, "Bulgaria", "Bolivia"));
        questions.add(new QuestionObject("Which flag is this?", false, R.drawable.flag_ecuador, "Ghana", "Ecuador"));
        questions.add(new QuestionObject("Which flag is this?", true, R.drawable.flag_afghanistan, "Afghanistan", "Jordan"));
        questions.add(new QuestionObject("Which flag is this?", true, R.drawable.flag_chile, "Chile", "Cuba"));
        questions.add(new QuestionObject("Which flag is this?", false, R.drawable.flag_belgium, "Germany", "Belgium"));
        questions.add(new QuestionObject("Which flag is this?", false, R.drawable.flag_georgia, "Iceland", "Georgia"));
        questions.add(new QuestionObject("Which flag is this?", true, R.drawable.flag_cyprus, "Equatorial Guinea", "Cyprus"));
    }

    private void setUpQuestions() {

        if (index == questions.size()) {
            Log.d("ALEX_APP", "ALL QUESTIONS ANSWERED");
            endGame();
        } else {
            currentQuestions = questions.get(index);
            question.setText(currentQuestions.getQuestion());
            flag.setImageResource(currentQuestions.getPicture());
            buttonTrue.setText(currentQuestions.getOptionone());
            buttonFalse.setText(currentQuestions.getOptiontwo());
            index++;
        }

    }

    private void determineButtonPress(boolean answer) {

        boolean expectedAnswer = currentQuestions.isAnswer();

        if (answer == expectedAnswer) {
            Toast.makeText(MainActivity.this, "Correct!", Toast.LENGTH_SHORT).show();
            score++;
            lblCount.setText("Current Score = " + score);
        } else {
            Toast.makeText(MainActivity.this, "Incorrect!", Toast.LENGTH_SHORT).show();
        }

        setUpQuestions();
    }

    private void endGame() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Congratulations! Please enter your name!");
        final EditText input = new EditText(this);
        builder.setView(input);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                m_Text = input.getText().toString();

                HighScoreObject highScore = new HighScoreObject(m_Text, score, new Date().getTime());

                List<HighScoreObject> highScores = Paper.book().read("highscores", new ArrayList<HighScoreObject>());

                highScores.add(highScore);

                Paper.book().write("highscores", highScores);

                finish();

            }
        });

        builder.show();

    }
}







