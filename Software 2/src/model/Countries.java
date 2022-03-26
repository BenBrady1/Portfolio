package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Timestamp;
import java.util.Date;

/** Creates and control Countries.*/
public class Countries {
    private int countyId;
    private String country;
    private Date createDate;
    private String createdBy;
    private Timestamp lastUpdate;
    private String lastUpdatedBy;

    /** Constructor for Countries.
     * @param countyId Primary key and unique ID.
     * @param  country Name of country.
     * @param createDate date the country created.
     * @param createdBy Identifies who created the country.
     * @param lastUpdate Most recent update to the Country.
     * @param lastUpdatedBy Identifies who did the last update to the country.
     */
    public Countries(int countyId, String country, Date createDate, String createdBy, Timestamp lastUpdate, String lastUpdatedBy) {
        this.countyId = countyId;
        this.country = country;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
    }

    /** Human readable representation of Countries object.*/
    public String toString(){
        String statement = countyId +" || "+ country;
        return statement;
    }

    /** adds appt to allCountries list.
     * @param countries The country that is being added to the list.*/
    public static void addCountry (Countries countries) {
        allCountries.add(countries);
    }

    /**
     * Returns the list of all Countries.
     * @return allCountries
     */
    public static ObservableList<Countries> getAllCountries() {
        return allCountries;
    }

    /** list of all Countries
     * @param allCountries a list of all countries. */
    private static ObservableList<Countries> allCountries = FXCollections.observableArrayList();

    /** .*/
    public int getCountyId() {
        return countyId;
    }

    /** .*/
    public void setCountyId(int countyId) {
        this.countyId = countyId;
    }

    /** .*/
    public String getCountry() {
        return country;
    }

    /** .*/
    public void setCountry(String country) {
        this.country = country;
    }

    /** .*/
    public Date getCreateDate() {
        return createDate;
    }

    /** .*/
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /** .*/
    public String getCreatedBy() {
        return createdBy;
    }

    /** .*/
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /** .*/
    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    /** .*/
    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    /** .*/
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    /** .*/
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }
}
