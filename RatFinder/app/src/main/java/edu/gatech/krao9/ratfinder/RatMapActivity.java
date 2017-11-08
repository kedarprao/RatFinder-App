package edu.gatech.krao9.ratfinder;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

/**
 *  Map Activity displays Rats centered around New York
 *
 * Created by Graeme Sharpe on 10/25/2017.
 *
 */

public class RatMapActivity extends AppCompatActivity implements OnMapReadyCallback{

    private DatabaseReference ratDatabase;
    private final static int DISPLAY_COUNT = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        ratDatabase = FirebaseDatabase.getInstance().getReference();

        Button filterButton = (Button) findViewById(R.id.mapFilter_Buttpn);

        final Bundle temp = savedInstanceState;

        /*
        Initialize google Map
         */
        MapView ratMap = (MapView) findViewById(R.id.mapView);
        ratMap.onCreate(savedInstanceState);
        ratMap.getMapAsync(this);


    }

    /**
     * Sets the contents on the GoogleMap nested in the MapView
     *
     * @param googleMap the googleMap to fill the MapView
     */
    @Override
    public void onMapReady(final GoogleMap googleMap) {
        /*
        Read rat information from firebase when filtered
         */
        Button filterButton = (Button) findViewById(R.id.mapFilter_Buttpn);

        filterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                googleMap.clear();

                String start = ((EditText) findViewById(R.id.mapFilter_start)).getText().toString();
                String end = ((EditText) findViewById(R.id.mapFilter_end)).getText().toString();
                Query query = ratDatabase.orderByChild("Created Date Int")
                        .startAt(start).endAt(end);

                query.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Log.d("READER", String.valueOf(dataSnapshot.getChildrenCount()));
                        for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                            Log.d("READER", snapshot.toString());
                            if (snapshot.child("latitude").getValue() != null
                                    && snapshot.child("longitude").getValue() != null) {
                                Log.d("READER", snapshot.child("latitude").getValue().toString());
                                Log.d("READER", snapshot.child("longitude").getValue().toString());
                                double lat = 0;
                                double loong = 0;
                                if (!snapshot.child("latitude").getValue().toString().equals("")) {
                                    lat = Double.parseDouble(snapshot.child("latitude").getValue().toString());
                                }
                                if (!snapshot.child("latitude").getValue().toString().equals("")) {
                                    loong = Double.parseDouble(snapshot.child("longitude").getValue().toString());
                                }
                                LatLng newYork = new LatLng(lat, loong);
                                googleMap.addMarker(new MarkerOptions().position(newYork)
                                        .title("Unique Key: " + snapshot.child("uniqueKey").getValue().toString()));
//                                Toast.makeText(RatMapActivity.this, "Rat Info:" + "\n"
//                                                + snapshot.child("uniqueKey").getValue().toString() + "\n"
//                                                + snapshot.child("locationType").getValue().toString(),
//                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.d("READER", "It didnt work");
                    }
                });
                LatLng newYork = new LatLng(40.7128, -74.0060);

                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(newYork, 8));
            }
        });

        /*
        Set map to be centered on NY and place a marker in the center of the city
         */
        LatLng newYork = new LatLng(40.7128, -74.0060);
        googleMap.addMarker(new MarkerOptions().position(newYork)
                .title("Center New York"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(newYork, 8));
    }
}
