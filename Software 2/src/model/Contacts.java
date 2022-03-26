package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/** Creates and control Contacts.*/
public class Contacts {
    private int Contact_ID;
    private String Contact_Name;
    private String Email;

    /** Constructor for Contacts.
     * @param Contact_ID Primary key and unique ID.
     * @param Contact_Name Name of contact.
     * @param Email Email of contact.
     * */
    public Contacts(int Contact_ID,String Contact_Name, String Email) {
        this.Contact_ID = Contact_ID;
        this.Contact_Name = Contact_Name;
        this.Email = Email;
    }

    /** Human readable representation of Contacts object.*/
    public String toString(){
        String statement = Contact_ID +" || "+ Contact_Name;
        return statement;
    }

    /** adds state to allStates list.
     * @param contacts the contacts being added.*/
    public static void addContacts (Contacts contacts) {
        allContacts.add(contacts);
    }

    /**
     * Returns the list of all states.
     * @return allAppointments
     */
    public static ObservableList<Contacts> allContacts() {
        return allContacts;
    }

    /** list of all states.
     * @param firstLevelDivisions a list of all states/provinces. */
    private static final ObservableList<Contacts> allContacts = FXCollections.observableArrayList();

    /** .*/
    public int getContact_ID() {
        return Contact_ID;
    }

    /** .*/
    public void setContact_ID(int contact_ID) {
        Contact_ID = contact_ID;
    }

    /** .*/
    public String getContact_Name() {
        return Contact_Name;
    }

    /** .*/
    public void setContact_Name(String contact_Name) {
        Contact_Name = contact_Name;
    }

    /** .*/
    public String getEmail() {
        return Email;
    }

    /** .*/
    public void setEmail(String email) {
        Email = email;
    }
}
