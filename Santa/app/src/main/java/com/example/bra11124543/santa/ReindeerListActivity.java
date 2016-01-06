package com.example.bra11124543.santa;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ReindeerListActivity extends AppCompatActivity {

    private List<ReindeerObject> reindeers;
    private ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reindeer_list);
        listview = (ListView)findViewById(R.id.listview);

        reindeers = new ArrayList<>();

        reindeers.add(new ReindeerObject("Rudolph", R.drawable.reindeer, "Woof"));
        reindeers.add(new ReindeerObject("Dasher", R.drawable.reindeer, "Meow"));
        reindeers.add(new ReindeerObject("Dancer", R.drawable.reindeer, "Moo"));
        reindeers.add(new ReindeerObject("Prancer", R.drawable.reindeer, "Neigh"));
        reindeers.add(new ReindeerObject("Vixen", R.drawable.reindeer, "Sheep"));
        reindeers.add(new ReindeerObject("Comet", R.drawable.reindeer, "Orange"));
        reindeers.add(new ReindeerObject("Cupid", R.drawable.reindeer, "Grass"));
        reindeers.add(new ReindeerObject("Donner", R.drawable.reindeer, "Lawnmower"));
        reindeers.add(new ReindeerObject("Blitzen", R.drawable.reindeer, "Q"));

        ReindeerAdapter adapter = new ReindeerAdapter(reindeers);

        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ReindeerObject reindeer = reindeers.get(position);

                Toast.makeText(ReindeerListActivity.this, reindeer.getNoise(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private class ReindeerAdapter extends ArrayAdapter<ReindeerObject>{

        public ReindeerAdapter(List<ReindeerObject> items) {
            super(ReindeerListActivity.this, 0, items);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent){

            if (convertView ==null) {
                convertView = getLayoutInflater().inflate(
                        R.layout.row_reindeer, null);
            }

            TextView lblTitle = (TextView)convertView.findViewById(R.id.title);
            ImageView image = (ImageView)convertView.findViewById(R.id.image);

            ReindeerObject reindeer = getItem(position);

            lblTitle.setText(reindeer.getName());
            image.setBackgroundResource(reindeer.getPicture());

            return convertView;
        }
    }
}