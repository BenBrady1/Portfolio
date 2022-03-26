package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Timestamp;
import java.time.DateTimeException;

/** Creates and control FLD.*/
public class FirstLevelDivision{
    private int Division_ID;
    private String Division;
    private String Create_Date;
    private String Created_By;
    private Timestamp Last_Update;
    private String Last_Updated_By;
    private int COUNTRY_ID;

    /** Constructor for FLD.
     * @param division_ID Primary key and unique ID.
     * @param division name of FLD.
     * @param create_Date Date the appointment was created.
     * @param created_By Identifies who created the appointment.
     * @param last_Update Most recent update to the appointment.
     * @param last_Updated_By Identifies who did the last update to the appointment.
     * @param COUNTRY_ID Unique ID for Country
     * */
    public FirstLevelDivision(int division_ID, String division, String create_Date, String created_By, Timestamp last_Update, String last_Updated_By, int COUNTRY_ID) {
        Division_ID = division_ID;
        Division = division;
        Create_Date = create_Date;
        Created_By = created_By;
        Last_Update = last_Update;
        Last_Updated_By = last_Updated_By;
        this.COUNTRY_ID = COUNTRY_ID;
    }

    /** Human readable representation of FLD object.*/
    public String toString(){
        String statement = Division_ID +" || "+ Division;
        return statement;
    }


    /** adds state to allStates list.
     * @param division the division being added.*/
    public static void addFirstLevelDivision (FirstLevelDivision division) {
        firstLevelDivisions.add(division);
    }

    /**
     * Returns the list of all states.
     * @return allAppointments
     */
    public static ObservableList<FirstLevelDivision> getFirstLevelDivisions() {
        return firstLevelDivisions;
    }

    /** list of all states.
     * @param firstLevelDivisions a list of all states/provinces. */
    private static final ObservableList<FirstLevelDivision> firstLevelDivisions = FXCollections.observableArrayList();

    /** .*/
    public int getDivision_ID() {
        return Division_ID;
    }

    /** .*/
    public void setDivision_ID(int division_ID) {
        Division_ID = division_ID;
    }

    /** .*/
    public String getDivision() {
        return Division;
    }

    /** .*/
    public void setDivision(String division) {
        Division = division;
    }

    /** .*/
    public String getCreate_Date() {
        return Create_Date;
    }

    /** .*/
    public void setCreate_Date(String create_Date) {Create_Date = create_Date;}

    /** .*/
    public String getCreated_By() {
        return Created_By;
    }

    /** .*/
    public void setCreated_By(String created_By) {
        Created_By = created_By;
    }

    /** .*/
    public Timestamp getLast_Update() {
        return Last_Update;
    }

    /** .*/
    public void setLast_Update(Timestamp last_Update) {
        Last_Update = last_Update;
    }

    /** .*/
    public String getLast_Updated_By() {
        return Last_Updated_By;
    }

    /** .*/
    public void setLast_Updated_By(String last_Updated_By) {
        Last_Updated_By = last_Updated_By;
    }

    /** .*/
    public int getCOUNTRY_ID() {
        return COUNTRY_ID;
    }

    /** .*/
    public void setCOUNTRY_ID(int COUNTRY_ID) {
        this.COUNTRY_ID = COUNTRY_ID;
    }
}
