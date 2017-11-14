package models;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import edu.gatech.krao9.ratfinder.HomeScreen;
import edu.gatech.krao9.ratfinder.LoginActivity;
import edu.gatech.krao9.ratfinder.R;
import edu.gatech.krao9.ratfinder.RegistrationActivity;

/**
 *  Firebase Cilent Model. Handles data being passed between Firebase and View Controllers
 */

public class Client extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private boolean token;
    private String username;
    private String password;
    private DatabaseReference ratDatabase;
    private GoogleMap googleMap;
    int[] months = new int[3];

    public Client() {
        mAuth = FirebaseAuth.getInstance();
        ratDatabase = FirebaseDatabase.getInstance().getReference();
    }

    public Client(GoogleMap googleMap) {
        this.googleMap = googleMap;
        mAuth = FirebaseAuth.getInstance();
        ratDatabase = FirebaseDatabase.getInstance().getReference();
        token = false;
    }

    /**
     * register new user with firebase
     */
    public void registerNewUser(String username, String password) {
        mAuth.createUserWithEmailAndPassword(username, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("SIGN UP", "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("SIGN UP", "createUserWithEmail:failure", task.getException());
                        }
                    }
                });
    }

    /**
     * check user login creditals
     */
    public boolean checkLogin(String username, String password) {
        mAuth.signInWithEmailAndPassword(username, password)
        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("LOG IN", "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            token = true;
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("LOGIN", "signInWithEmail:failure", task.getException());
                            token = false;
                        }

                    }
                });
        return token;
    }

    /**
     *  Filter firebase to and adds relavent rats to the map
     *
     *
     * @param start the start date to fliter
     * @param end the end date to fliter
     */
    public void getMapMarkers(String start, String end) {
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
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("READER", "It didnt work");
            }
        });
    }

    /**
     *  Filters through Firebasse to return the relavent data points
     *
     * @param start start year to filter firebase
     * @param quat the quarter
     * @return the datapoints to be plotted
     */
    public int[] getGraphDatapoints(String start, String quat) {
        String end = start;
        DatabaseReference database = ratDatabase;

        int quarter = 0;
        if (quat.equals("Q1")) {
            quarter = 1;
        } else if (quat.equals("Q2")) {
            quarter = 2;
        } else if (quat.equals("Q3")) {
            quarter = 3;
        } else if (quat.equals("Q4")) {
            quarter = 4;
        }

        /*
            Queries through a given years quarter saving the data to an array
         */
        switch (quarter) {
            case 1:
                Query queryM1 = database.orderByChild("Created Date Int")
                        .startAt(start + "0101").endAt(end + "0131");
                queryM1.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        months[0] = (int) dataSnapshot.getChildrenCount();
                        Log.d("READER", String.valueOf(dataSnapshot.getChildrenCount()));
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
                Query queryM2 = database.orderByChild("Created Date Int")
                        .startAt(start + "0201").endAt(end + "0228");
                queryM2.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        months[1] = (int) dataSnapshot.getChildrenCount();
                        Log.d("READER", String.valueOf(dataSnapshot.getChildrenCount()));
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
                Query queryM3 = database.orderByChild("Created Date Int")
                        .startAt(start + "0301").endAt(end + "0331");
                queryM3.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        months[2] = (int) dataSnapshot.getChildrenCount();
                        Log.d("READER", String.valueOf(dataSnapshot.getChildrenCount()));
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
                break;
            case 2:
                Query queryM4 = database.orderByChild("Created Date Int")
                        .startAt(start + "0401").endAt(end + "0430");
                queryM4.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        months[0] = (int) dataSnapshot.getChildrenCount();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
                Query queryM5 = database.orderByChild("Created Date Int")
                        .startAt(start + "0501").endAt(end + "0531");
                queryM5.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        months[1] = (int) dataSnapshot.getChildrenCount();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
                Query queryM6 = database.orderByChild("Created Date Int")
                        .startAt(start + "0601").endAt(end + "0630");
                queryM6.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        months[2] = (int) dataSnapshot.getChildrenCount();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
                break;
            case 3:
                Query queryM7 = database.orderByChild("Created Date Int")
                        .startAt(start + "0701").endAt(end + "0731");
                queryM7.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        months[0] = (int) dataSnapshot.getChildrenCount();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
                Query queryM8 = database.orderByChild("Created Date Int")
                        .startAt(start + "0801").endAt(end + "0831");
                queryM8.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        months[1] = (int) dataSnapshot.getChildrenCount();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
                Query queryM9 = database.orderByChild("Created Date Int")
                        .startAt(start + "0901").endAt(end + "0930");
                queryM9.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        months[2] = (int) dataSnapshot.getChildrenCount();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
                break;
            case 4:
                Query queryM10 = database.orderByChild("Created Date Int")
                        .startAt(start + "1001").endAt(end + "1031");
                queryM10.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        months[0] = (int) dataSnapshot.getChildrenCount();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
                Query queryM11 = database.orderByChild("Created Date Int")
                        .startAt(start + "1101").endAt(end + "1130");
                queryM11.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        months[1] = (int) dataSnapshot.getChildrenCount();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
                Query queryM12 = database.orderByChild("Created Date Int")
                        .startAt(start + "1201").endAt(end + "1231");
                queryM12.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        months[2] = (int) dataSnapshot.getChildrenCount();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
                break;
            default:
                months[0] = 0;
                months[1] = 0;
                months[2] = 0;
        }

        Log.d("READER", String.valueOf(months[0]));
        return months;
    }
}
