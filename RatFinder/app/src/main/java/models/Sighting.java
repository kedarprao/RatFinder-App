package models;
/**
 * Created by wlim on 10/5/17.
 */

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


    private int splitSize;



    public Sighting(String raw) {
        String[] parts = raw.split(",");

        splitSize = parts.length;


        if (splitSize > 1) {
            uniqueKey = parts[0];
        }
        if (splitSize > 1) {
            createdDate = parts[1];
        }
        if (splitSize > 7) {
            locationType = parts[7];
        }
        if (splitSize > 8) {
            incidentZip = parts[8];
        }
        if (splitSize > 9) {
            incidentAddress = parts[9];
        }
        if (splitSize > 16) {
            city = parts[16];
        }
        if (splitSize > 23) {
            borough = parts[23];
        }
        if (splitSize > 49) {
            latitude = parts[49];
        }
        if (splitSize > 50) {
            longitude = parts[50];
        }
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
    public String getCity() {
        return city;
    }
    public String getBorough() {
        return borough;
    }
    public String getLatitude() {
        return latitude;
    }
    public String getLongitude() {
        return longitude;
    }


    public String getImportantStuff() {
        String acc = "";

        if ((locationType == null || locationType.isEmpty())
                && (incidentAddress == null || incidentAddress.isEmpty())) {
            return "Some info on this sighting is missing!";
        }

        if (locationType != null && !locationType.isEmpty()) {
            acc += "Location Type: " + locationType + ", ";
        }
        if (incidentAddress != null && !incidentAddress.isEmpty()) {
            acc += "Incident Address: " + incidentAddress;
        }

        return acc;
    }
}
