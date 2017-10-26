package models;

import java.util.HashMap;
import java.util.LinkedHashMap;

import edu.gatech.krao9.ratfinder.Rat;

public class Sighting {
    private String uniqueKey;
    private String createdDate;
    private String locationType;
    private String incidentZip;
    private String incidentAddress;
    private String city;
    private String borough;
    private String latitude;
    private String longitude;

    private LinkedHashMap<String, String> children;


    public Sighting() {
        children = new LinkedHashMap<String, String>();
    }

    public Sighting(Rat rat) {
        children = new LinkedHashMap<String, String>();
        this.setUniqueKey("" + rat.getUniqueKey());
        this.setCreatedDate(rat.getCreatedDate().toString());
        this.setLocationType(rat.getLocationType());
        this.setIncidentZip("" + rat.getIncidentZip());
        this.setIncidentAddress(rat.getIncidentAddress());
        this.setCity(rat.getCity());
        this.setBorough(rat.getBorough());
        this.setLatitude("" + rat.getLatitude());
        this.setLongitude("" + rat.getLongitude());


    }


    public Sighting(String csvLine) {
        children = new LinkedHashMap<String, String>();

        String[] parts = csvLine.split(",");
        int splitSize = parts.length;


        if (splitSize > 1) { this.setUniqueKey(parts[0]); }
        if (splitSize > 1) { this.setCreatedDate(parts[1]); }
        if (splitSize > 7) { this.setLocationType(parts[7]); }
        if (splitSize > 8) { this.setIncidentZip(parts[8]); }
        if (splitSize > 9) { this.setIncidentAddress(parts[9]); }
        if (splitSize > 16) { this.setCity(parts[16]); }
        if (splitSize > 23) { this.setBorough(parts[23]); }
        if (splitSize > 49) { this.setLatitude(parts[49]); }
        if (splitSize > 50) { this.setLongitude(parts[50]); }

    }

    public void setUniqueKey(String uniqueKey) {
        this.uniqueKey = uniqueKey;
        this.children.put("Unique Key", uniqueKey);
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
        //this.children.put("Created Date", createdDate);
    }

    public void setLocationType(String locationType) {
        this.locationType = locationType;
        this.children.put("Location Type", locationType);
    }

    public void setIncidentZip(String incidentZip) {
        this.incidentZip = incidentZip;
        this.children.put("Incident Zip", incidentZip);
    }


    public void setIncidentAddress(String incidentAddress) {
        this.incidentAddress = incidentAddress;
        //this.children.put("Incident Address", incidentAddress);
    }

    public void setCity(String city) {
        this.city = city;
        this.children.put("City", city);
    }

    public void setBorough(String borough) {
        this.borough = borough;
        this.children.put("Borough", borough);
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
        this.children.put("Latitude", latitude);
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
        this.children.put("Longitude", longitude);
    }

    //makes a copy of the hashmap so we cant alter the children member of a Sighting by getting it
    public LinkedHashMap<String, String> getChildren() {
        return new LinkedHashMap<>(this.children);
    }

    public String getUniqueKey() {
        return uniqueKey;
    }
    public String getCreatedDate() {
        return createdDate;
    }
    public String getLocationType() {
        return locationType;
    }
    public String getIncidentZip() {
        return incidentZip;
    }
    public String getIncidentAddress() {
        return incidentAddress;
    }
    public String getCity() { return city; }
    public String getBorough() {
        return borough;
    }
    public String getLatitude() {
        return latitude;
    }
    public String getLongitude() {
        return longitude;
    }

}
