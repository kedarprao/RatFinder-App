package edu.gatech.krao9.ratfinder;

import android.content.Intent;
import android.util.Log;
import android.content.res.AssetManager;
import android.os.Bundle;
//import android.support.design.widget.FloatingActionButton;
//import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.Toolbar;
//import android.support.design.widget.FloatingActionButton;
//import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import models.Sighting;
import java.io.BufferedReader;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class HomeScreen extends AppCompatActivity {

    private ArrayList<Sighting> sightings;

    private ListView sightingsLV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        final Button logoutButton = (Button) findViewById(R.id.logout_button);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeScreen.this, StartupActivity.class));
            }
        });


        AssetManager am = getResources().getAssets();
        try {
            InputStream inputStream = am.open("Rat_Sightings.csv");
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;

            sightings = new ArrayList<Sighting>();
            int counter = 0;
            while ((line = reader.readLine()) != null) {
                sightings.add(new Sighting(line));
                Log.d("darn!", "first loop" + counter++);
            }
            reader.close();


            String[] listItems = new String[sightings.size()];

            for (int i = 0; i < listItems.length; i++) {
                Sighting x = sightings.get(i);

                listItems[i] = x.getImportantStuff();

            }
            ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems);
            sightingsLV = (ListView) findViewById(R.id.sightingsListView);
            sightingsLV.setAdapter(adapter);

        } catch (Exception e){
            Log.d("darn!", e.getMessage());
        }



    }





}
