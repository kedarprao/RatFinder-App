package edu.gatech.krao9.ratfinder;

import android.content.Intent;
import android.os.Bundle;
//import android.support.design.widget.FloatingActionButton;
//import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.Toolbar;
//import android.support.design.widget.FloatingActionButton;
//import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;

import adapters.SightingsExpandableListAdapter;
import models.Sighting;


public class HomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        final Button logoutButton = (Button) findViewById(R.id.logout_button);
        final Button addNewRatButton = (Button) findViewById(R.id.addRat_button);
        final Button mapButton = (Button) findViewById(R.id.map_button);

        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeScreen.this, RatMapActivity.class));
            }
        });

        addNewRatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeScreen.this, NewRatSightingActivity.class));
            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeScreen.this, StartupActivity.class));
            }
        });


        SightingsExpandableListAdapter adapter = new SightingsExpandableListAdapter(this);

        Rat r = (Rat) getIntent().getSerializableExtra("NEW_RAT");
        if (r != null) {
            adapter.addSighting(new Sighting(r));
        }
        adapter.addSightingsFromCsv(this, "Rat_Sightings.csv");


        ExpandableListView sightingsLV = (ExpandableListView) findViewById(R.id.ratListView);
        sightingsLV.setAdapter(adapter);

    }
}
