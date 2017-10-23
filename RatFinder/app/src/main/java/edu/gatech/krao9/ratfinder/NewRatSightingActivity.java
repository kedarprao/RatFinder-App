package edu.gatech.krao9.ratfinder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Date;

/**
 * Created by Graeme Sharpe on 10/17/2017.
 *
 */

public class NewRatSightingActivity extends AppCompatActivity {

    /**
     *  Sets view content and overrides button onClick methods
     *
     * @param savedInstanceState current instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_rat_sighting);

        Button confirmNewRat = (Button) findViewById(R.id.newRatButton);
        Button cancelNewRat = (Button) findViewById(R.id.cancelNewRatButton);

        confirmNewRat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Rat newRat = createNewRat();
                Intent home = new Intent(NewRatSightingActivity.this, HomeScreen.class);
                home.putExtra("NEW_RAT", newRat);
                startActivity(home);
            }
        });
        cancelNewRat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(NewRatSightingActivity.this, HomeScreen.class));
            }
        });
    }

    /**
     *  Method used to parse information entered on screen and create a new rat
     *
     * @return a newly sighted Rat
     */
    public Rat createNewRat() {
        final EditText locationType = (EditText) findViewById(R.id.locationType);
        final EditText incidentZip = (EditText) findViewById(R.id.incidentZip);
        final EditText incidentAddress = (EditText) findViewById(R.id.incidentAddress);
        final EditText city = (EditText) findViewById(R.id.city);
        final EditText borough = (EditText) findViewById(R.id.borough);
        final EditText latitude = (EditText) findViewById(R.id.latitude);
        final EditText longitude = (EditText) findViewById(R.id.longitude);
        Date createdDate = new Date();
        float lat = Integer.parseInt(latitude.getText().toString());
        float lonng = Integer.parseInt(longitude.getText().toString());

        return new Rat(createdDate, locationType.getText().toString(),
                Integer.parseInt(incidentZip.getText().toString()),
                incidentAddress.getText().toString(), city.getText().toString(),
                borough.getText().toString(), lat, lonng);
    }
}
