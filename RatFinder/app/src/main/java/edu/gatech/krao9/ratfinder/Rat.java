package edu.gatech.krao9.ratfinder;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by graem on 10/17/2017.
 * Rat object class
 */

public class Rat implements Serializable {

    private int uniqueKey;
    private Date createdDate;
    private String locationType;
    private int incidentZip;
    private String incidentAddress;
    private String city;
    private String borough;
    private float latitude;
    private float longitude;

    /**
     *  Rat Constructor
     *
     * @param createdDate date created
     * @param locationType location tyoe of new rat
     * @param incidentZip new rat's sighting zip
     * @param incidentAddress new rat's sighting address
     * @param city city rat was sighted
     * @param borough corough rat was sighted
     * @param latitude latitude of rat sighting
     * @param longitude long of rat sighting

     */
    public Rat(Date createdDate, String locationType,
               int incidentZip, String incidentAddress,
               String city, String borough, float latitude, float longitude) {
        this.createdDate = createdDate;
        this.locationType = locationType;
        this.incidentZip = incidentZip;
        this.incidentAddress = incidentAddress;
        this.city = city;
        this.borough = borough;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     *  Getters and Setter for Rat
     *
     */
    public int getUniqueKey() {
        return uniqueKey;
    }

    public void setUniqueKey(int uniqueKey) {
        this.uniqueKey = uniqueKey;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getLocationType() {
        return locationType;
    }

    public void setLocationType(String locationType) {
        this.locationType = locationType;
    }

    public int getIncidentZip() {
        return incidentZip;
    }

    public void setIncidentZip(int incidentZip) {
        this.incidentZip = incidentZip;
    }

    public String getIncidentAddress() {
        return incidentAddress;
    }

    public void setIncidentAddress(String incidentAddress) {
        this.incidentAddress = incidentAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public String getBorough() {
        return borough;
    }

    public void setBorough(String borough) {
        this.borough = borough;
    }

    /**
     * End of Getters and Setters for Rat
     */
}
