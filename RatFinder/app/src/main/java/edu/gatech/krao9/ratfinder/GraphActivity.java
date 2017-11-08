package edu.gatech.krao9.ratfinder;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.androidplot.xy.LineAndPointFormatter;
import com.androidplot.xy.SimpleXYSeries;
import com.androidplot.xy.XYGraphWidget;
import com.androidplot.xy.XYPlot;
import com.androidplot.xy.XYSeries;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;


import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GraphActivity extends AppCompatActivity {

    int[] months = new int[12];
    private DatabaseReference database;
    private XYPlot plot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        database = FirebaseDatabase.getInstance().getReference();
        Button display = (Button) findViewById(R.id.graph_display_button);
        Button setter = (Button) findViewById(R.id.graph_set);


        setter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setGrpah();
            }
        });

        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String start = ((EditText) findViewById(R.id.data_year)).getText().toString();
                String end = ((EditText) findViewById(R.id.data_year)).getText().toString();
                String quat = ((EditText) findViewById(R.id.data_quarter)).getText().toString();
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
            }
        });
    }

    public void setGrpah() {
        GraphView graph = (GraphView) findViewById(R.id.graph);
        graph.removeAllSeries();
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(1, months[0]),
                new DataPoint(2, months[1]),
                new DataPoint(3, months[2])
        });
        graph.addSeries(series);
    }
}
