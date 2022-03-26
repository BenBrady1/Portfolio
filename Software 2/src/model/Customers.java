package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Timestamp;
import java.util.Date;

/** Creates and control Customers.*/
public class Customers {
    private int Customer_ID;
    private String Customer_Name;
    private String Address;
    private String Postal_Code;
    private String Phone;
    private Date Create_Date;
    private String Created_By;
    private Timestamp Last_Update;
    private String Last_Updated_By;
    private int Division_ID;

    /** Constructor for Customers.
     * @param customer_ID Primary key and unique ID.
     * @param customer_Name Name of Customer.
     * @param address Customers address.
     * @param postal_Code zip code of address.
     * @param phone Phone number of address.
     * @param create_Date Date the appointment was created.
     * @param created_By Identifies who created the appointment.
     * @param last_Update Most recent update to the appointment.
     * @param last_Updated_By Identifies who did the last update to the appointment.
     * @param division_ID Unique ID for FLD.
     * */
    public Customers(int customer_ID, String customer_Name, String address, String postal_Code, String phone, Date create_Date, String created_By, Timestamp last_Update, String last_Updated_By, int division_ID) {
        Customer_ID = customer_ID;
        Customer_Name = customer_Name;
        Address = address;
        Postal_Code = postal_Code;
        Phone = phone;
        Create_Date = create_Date;
        Created_By = created_By;
        Last_Update = last_Update;
        Last_Updated_By = last_Updated_By;
        Division_ID = division_ID;
    }

    /** Human readable representation of Customers object.*/
    public String toString(){
        String statement = Customer_ID +" || "+ Customer_Name;
        return statement;
    }

    /** adds appt to allCountries list.
     * @param customer The customer that is being added to the list.*/
    public static void addCustomers (Customers customer) {
        allCustomers.add(customer);
    }

    /**
     * Returns the list of all Countries.
     * @return allCustomers
     */
    public static ObservableList<Customers> getAllCustomers() {
        return allCustomers;
    }

    /** list of all customers
     * @param allCountries a list of all customers. */
    public static ObservableList<Customers> allCustomers = FXCollections.observableArrayList();

    /** .*/
    public int getCustomer_ID() {
        return Customer_ID;
    }

    /** .*/
    public void setCustomer_ID(int customer_ID) {
        Customer_ID = customer_ID;
    }

    /** .*/
    public String getCustomer_Name() {
        return Customer_Name;
    }

    /** .*/
    public void setCustomer_Name(String customer_Name) {
        Customer_Name = customer_Name;
    }

    /** .*/
    public String getAddress() {
        return Address;
    }

    /** .*/
    public void setAddress(String address) {
        Address = address;
    }

    /** .*/
    public String getPostal_Code() {
        return Postal_Code;
    }

    /** .*/
    public void setPostal_Code(String postal_Code) {
        Postal_Code = postal_Code;
    }

    /** .*/
    public String getPhone() {
        return Phone;
    }

    /** .*/
    public void setPhone(String phone) {
        Phone = phone;
    }

    /** .*/
    public Date getCreate_Date() {
        return Create_Date;
    }

    /** .*/
    public void setCreate_Date(Date create_Date) {
        Create_Date = create_Date;
    }

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
    public int getDivision_ID() {
        return Division_ID;
    }

    /** .*/
    public void setDivision_ID(int division_ID) {
        Division_ID = division_ID;
    }
}
