package adapters;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import edu.gatech.krao9.ratfinder.R;
import models.Sighting;

//TODO: make the sightings appear/generate in pages/chunks, instead of rendering all at once

public class SightingsExpandableListAdapter extends BaseExpandableListAdapter {

    private static final int MAX_SIGHTINGS_DISPLAYED = 15;

    private ArrayList<Sighting> sightings;

    private LayoutInflater mInflater;

    public SightingsExpandableListAdapter(Context context) {
        sightings = new ArrayList<Sighting>();
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }




    // Returns key corresponding to child position and sighting
    // i.e. Consider a Sighting with only fields zip code, latitude, and longitude not null. The
    // three fields will be assigned indices by insertion order, so if we set them in order of
    // their original listing, the indices of zip code, latitude, and longitude would
    // be 0, 1, 2, respectively. Calling this method with childPosition = 2 would return "Longitude".
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        ArrayList<String> x = new ArrayList<String>(sightings.get(groupPosition).getChildren().keySet());
        return x.get(childPosition);
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        String childType = (String) getChild(groupPosition, childPosition);


        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.sighting_child_item, null);
        }

        TextView txtListChild = (TextView) convertView.findViewById(R.id.child_label);

        txtListChild.setText(childType + ": " + sightings.get(groupPosition).getChildren().get(childType));
        return convertView;

    }
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }


    @Override
    public int getChildrenCount(int groupPosition) {
        Sighting sighting = sightings.get(groupPosition);

        return sighting.getChildren().size();


    }
    @Override
    public Object getGroup(int groupPosition) {
        return sightings.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return sightings.size();
    }
    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }


    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = mInflater.inflate(R.layout.sighting_list_item, parent, false);
        }


        Sighting sighting = (Sighting) getGroup(groupPosition);

        TextView secondLine = (TextView) view.findViewById(R.id.secondLine);
        TextView firstLine = (TextView) view.findViewById(R.id.firstLine);

        if (sighting.getIncidentAddress() == null || sighting.getIncidentAddress().isEmpty()) {
            firstLine.setText("Unknown Address");
        } else {
            firstLine.setText(sighting.getIncidentAddress());
        }
        secondLine.setText(sighting.getCreatedDate());

        return view;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }


    public void addSighting(Sighting sighting) { //use this to add Sightings! note this might be less than robust
        sightings.add(sighting);
        notifyDataSetChanged();
    }

    public void addSightingsFromCsv(Context context, String csvName) {
        AssetManager am = context.getAssets();
        try {
            InputStream inputStream = am.open(csvName);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;


            reader.readLine(); //first line of the csv only contains info about the next lines

            while ((line = reader.readLine()) != null
                    && sightings.size() <= MAX_SIGHTINGS_DISPLAYED) {
                sightings.add(new Sighting(line));
            }
            reader.close();
        } catch (Exception e) {
            Log.d("Reading from csv error", e.getMessage());
        }
    }
}
