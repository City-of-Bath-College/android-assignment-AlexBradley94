package com.example.bra11124543.questionnaire;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import io.paperdb.Paper;

public class IntroductionActivity extends AppCompatActivity {

    private Button btn_Play;
    private Button btn_Highscore;
    private Button btn_About;
    private TextView currentHighScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduction);

        btn_Play = (Button)findViewById(R.id.btn_Play);
        btn_Highscore = (Button)findViewById(R.id.btn_Highscore);
        btn_About = (Button)findViewById(R.id.btn_About);
        currentHighScore = (TextView)findViewById(R.id.currentHighScore);
        Paper.init(this);



        btn_Play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(IntroductionActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

        btn_Highscore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(IntroductionActivity.this, HighScoreActivity.class);
                startActivity(i);
            }
        });

        btn_About.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(IntroductionActivity.this, ProfileActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    protected void onResume(){
        super.onResume();

        List<HighScoreObject> highScores = Paper.book().read("highscores", new ArrayList<HighScoreObject>());

        int maxScore = 0;
        String playerName = new String();

        for( int i = 0; i < highScores.size(); i++){

            HighScoreObject h = highScores.get(i);

            if (h.getScore() > maxScore ){
                maxScore = h.getScore();
                playerName = h.getName();
            }

        }
        currentHighScore.setText("Current high score is \n" + playerName + " with " + maxScore);

    }
}
