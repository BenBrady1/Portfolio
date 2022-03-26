package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/** Creates and control Location.*/
public class Location {
    private String City;

    /** Constructor for location.
     * @param city name of location
     * */
    public Location(String city) {
        this.City = city;
    }

    /** Human readable representation of Location object.*/
    public String toString(){
        String statement = City;
        return statement;
    }

    /** Adds location to allLocations list.*/
    public static void addLocation(ObservableList<model.Location> locations, Location location) {
        Location.add(location);
    }

    /** Gets list of locations.*/
    public static ObservableList<Location> allLocations() {
        return Location;
    }

    /** Observable list of Locations.*/
    private static final ObservableList<Location> Location = FXCollections.observableArrayList();

    /** Gets city.*/
    public String getCity() {
        return City;
    }

    /** Sets city.*/
    public void setCity(String city) {
        this.City = city;
    }
}