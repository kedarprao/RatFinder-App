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


public class HomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        final Button logoutButton = (Button) findViewById(R.id.logout_button);
        final Button addNewRatButton = (Button) findViewById(R.id.addRat_button);

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

          
        adapter.addSightingsFromCsv(this, "Rat_Sightings.csv");


        ExpandableListView sightingsLV = (ExpandableListView) findViewById(R.id.ratListView);
        sightingsLV.setAdapter(adapter);

    }
}
