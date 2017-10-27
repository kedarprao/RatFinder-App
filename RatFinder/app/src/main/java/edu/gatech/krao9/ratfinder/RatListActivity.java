package edu.gatech.krao9.ratfinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;

import adapters.SightingsExpandableListAdapter;

public class RatListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rat_list);

        final Button addNewRatButton = (Button) findViewById(R.id.addRat_button);

        addNewRatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RatListActivity.this, NewRatSightingActivity.class));
            }
        });

        SightingsExpandableListAdapter adapter = new SightingsExpandableListAdapter(this);

        adapter.addSightingsFromCsv(this, "Rat_Sightings.csv");

        ExpandableListView sightingsLV = (ExpandableListView) findViewById(R.id.ratListView);
        sightingsLV.setAdapter(adapter);
    }
}
