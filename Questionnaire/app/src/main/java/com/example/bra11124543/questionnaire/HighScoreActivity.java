package com.example.bra11124543.questionnaire;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.paperdb.Paper;

public class HighScoreActivity extends AppCompatActivity {

    private ListView listView;
    private Toolbar toolbar;

    private List<HighScoreObject> highScores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);

          toolbar = (Toolbar) findViewById(R.id.tool_bar); // Attaching the layout to the toolbar object
          setSupportActionBar(toolbar);

        listView = (ListView)findViewById(R.id.ListView);

        Paper.init(this);

        highScores = Paper.book().read("highscores", new ArrayList<HighScoreObject>());

        Toast.makeText(this, "Number = " + highScores.size() , Toast.LENGTH_SHORT).show();

        HighscoreAdapter adapter = new HighscoreAdapter(highScores);

        listView.setAdapter(adapter);
    }

    private class HighscoreAdapter extends ArrayAdapter<HighScoreObject> {

        public HighscoreAdapter(List<HighScoreObject> items) {
            super(HighScoreActivity.this, 0, items);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if (convertView == null) {
                convertView = getLayoutInflater().inflate(
                        R.layout.row_highscore, null);
            }

            HighScoreObject highscore = highScores.get(position);
            Date date = new Date(highscore.getTime());

            SimpleDateFormat fmtOut = new SimpleDateFormat("dd-MM-yyyy");

            TextView lblTitle = (TextView) convertView.findViewById(R.id.lblTitle);

            lblTitle.setText(highscore.getScore() + "    -    " + highscore.getName() + "    -    " + fmtOut.format(date));

            return convertView;
        }
    }
        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.menu_high_score, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            // Handle item selectionprivate void answerResultAlert(String feedback){
            //int id = item.getItemId();
            AlertDialog.Builder warning = new AlertDialog.Builder(this);

            //
            // return true;
            warning.setTitle("Erasing high score database");
            warning.setMessage("This action cannot be undone. Are you sure you want to delete the database?");
            warning.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    //only when the ok is clicked will continue to run the program
                    deleteDatabase();
                }
            });
            warning.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            warning.create();
            warning.show();
            //}
            return super.onOptionsItemSelected(item);
        }

        public void deleteDatabase(){
            Paper.book().destroy();//delete the database
            super.recreate();
            Toast.makeText(this, "Database deleted", Toast.LENGTH_SHORT).show();
        }
}

