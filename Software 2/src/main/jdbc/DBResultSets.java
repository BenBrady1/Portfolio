package main.jdbc;

import model.*;
import viewController.patient;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

/** Reads results of a query.*/
public class DBResultSets {
    public static Connection conn = DBDriver.getConnection();

//------Populates------\\

    /** Populates allLocations list.*/
    public static void populateLocations() throws SQLException {
        Location.allLocations().clear();
        //Populate allAppointments
        String insertStatement = "SELECT DISTINCT Location FROM appointments";
        DBPreparedStatements.setPreparedStatement(conn, insertStatement);
        PreparedStatement ps = DBPreparedStatements.getPreparedStatement();
        ResultSet rs = ps.executeQuery();
        //Populate list
        while (rs.next()) {
            //get information from DB
            String City = rs.getString(1);
            //insert appointments into observablelist for AppointmentTableView
            Location.addLocation(Location.allLocations(), new Location(City));
        }
    }

    /** Populates all/month/week appointments list list.*/
    public static void populateAppointments() throws SQLException {
        Appointment.getAllAppointments().clear();
        Appointment.getMonthsAppointments().clear();
        Appointment.getWeeksAppointments().clear();

        //Populate allAppointments
        String insertStatement = "SELECT * FROM appointments";
        DBPreparedStatements.setPreparedStatement(conn, insertStatement);
        PreparedStatement ps = DBPreparedStatements.getPreparedStatement();
        ResultSet rs = ps.executeQuery();
        //Populate list
        while (rs.next()) {
            //get information from DB
            int apptId = rs.getInt("Appointment_ID");
            String title = rs.getString("title");
            String description = rs.getString("Description");
            String location = rs.getString("Location");
            String type = rs.getString("Type");
            LocalDateTime start = rs.getTimestamp("Start").toLocalDateTime();
            LocalDateTime end = rs.getTimestamp("end").toLocalDateTime();
            Date createDate = rs.getDate("Create_Date");
            String createdBy = rs.getString("Created_By");
            Timestamp lastUpdate = rs.getTimestamp("Last_Update");
            String lastUpdateBy = rs.getString("Last_Updated_By");
            int customerId = rs.getInt("Customer_ID");
            int userId = rs.getInt("User_ID");
            int contactId = rs.getInt("Contact_ID");
            //insert appointments into observablelist for AppointmentTableView
            Appointment.addAppt(Appointment.getAllAppointments(), new Appointment(apptId, title, description, location, type, start,
                    end, createDate, createdBy, lastUpdate, lastUpdateBy, customerId, userId, contactId));
        }

        //Populate weeksAppointments
        insertStatement = "select * FROM appointments where Start between current_timestamp() AND date_add(current_timestamp(), INTERVAL 7 DAY)";
        DBPreparedStatements.setPreparedStatement(conn, insertStatement);
        ps = DBPreparedStatements.getPreparedStatement();
        rs = ps.executeQuery();
        //Populate list
        while (rs.next()) {
            //get information from DB
            int apptId = rs.getInt("Appointment_ID");
            String title = rs.getString("title");
            String description = rs.getString("Description");
            String location = rs.getString("Location");
            String type = rs.getString("Type");
            LocalDateTime start = rs.getTimestamp("Start").toLocalDateTime();
            LocalDateTime end = rs.getTimestamp("end").toLocalDateTime();
            Date createDate = rs.getDate("Create_Date");
            String createdBy = rs.getString("Created_By");
            Timestamp lastUpdate = rs.getTimestamp("Last_Update");
            String lastUpdateBy = rs.getString("Last_Updated_By");
            int customerId = rs.getInt("Customer_ID");
            int userId = rs.getInt("User_ID");
            int contactId = rs.getInt("Contact_ID");
            //insert appointments into observablelist for AppointmentTableView
            Appointment.addAppt(Appointment.getWeeksAppointments(), new Appointment(apptId, title, description, location, type, start,
                    end, createDate, createdBy, lastUpdate, lastUpdateBy, customerId, userId, contactId));
        }

        //Populate monthsAppointments by going forward 30 days
        insertStatement = "select * FROM appointments where Start between current_timestamp() AND date_add(current_timestamp(), INTERVAL 30 DAY)";
        DBPreparedStatements.setPreparedStatement(conn, insertStatement);
        ps = DBPreparedStatements.getPreparedStatement();
        rs = ps.executeQuery();
        //Populate list
        while (rs.next()) {
            //get information from DB
            int apptId = rs.getInt("Appointment_ID");
            String title = rs.getString("title");
            String description = rs.getString("Description");
            String location = rs.getString("Location");
            String type = rs.getString("Type");
            LocalDateTime start = rs.getTimestamp("Start").toLocalDateTime();
            LocalDateTime end = rs.getTimestamp("end").toLocalDateTime();
            Date createDate = rs.getDate("Create_Date");
            String createdBy = rs.getString("Created_By");
            Timestamp lastUpdate = rs.getTimestamp("Last_Update");
            String lastUpdateBy = rs.getString("Last_Updated_By");
            int customerId = rs.getInt("Customer_ID");
            int userId = rs.getInt("User_ID");
            int contactId = rs.getInt("Contact_ID");
            //insert appointments into observablelist for AppointmentTableView
            Appointment.addAppt(Appointment.getMonthsAppointments(), new Appointment(apptId, title, description, location, type, start,
                    end, createDate, createdBy, lastUpdate, lastUpdateBy, customerId, userId, contactId));
        }
    }

    /** Populates customerScheedule list.
     * @param contactID ContactID to be searched.*/
    public static void populateCustomerSchedule(int contactID) throws SQLException {
        //Populate customerSchedule
        String insertStatement = "SELECT * FROM appointments WHERE Contact_ID = ? order by Contact_ID, Start asc";
        DBPreparedStatements.setPreparedStatement(conn,insertStatement);
        PreparedStatement ps = DBPreparedStatements.getPreparedStatement();
        ps.setInt(1,contactID);
        ResultSet rs = ps.executeQuery();
        //Populate list
        while (rs.next()) {
            //get information from DB
            int apptId = rs.getInt("Appointment_ID");
            String title = rs.getString("title");
            String description = rs.getString("Description");
            String location = rs.getString("Location");
            String type = rs.getString("Type");
            LocalDateTime start = rs.getTimestamp("Start").toLocalDateTime();
            LocalDateTime end = rs.getTimestamp("end").toLocalDateTime();
            Date createDate = rs.getDate("Create_Date");
            String createdBy = rs.getString("Created_By");
            Timestamp lastUpdate = rs.getTimestamp("Last_Update");
            String lastUpdateBy = rs.getString("Last_Updated_By");
            int customerId = rs.getInt("Customer_ID");
            int userId = rs.getInt("User_ID");
            int contactId = rs.getInt("Contact_ID");
            //insert appointments into observablelist for AppointmentTableView
            Appointment.addAppt(Appointment.getContactSchedule(),new Appointment(apptId, title, description, location, type, start,
                    end, createDate, createdBy, lastUpdate, lastUpdateBy, customerId,  userId, contactId));
        }
    }

    /** Populates Countries list.*/
    public static void populateCountries () throws SQLException{
        //Clear old list
        Countries.getAllCountries().clear();

        //Populate Countries list
        String insertStatement = "SELECT * FROM countries";
        DBPreparedStatements.setPreparedStatement(conn,insertStatement);
        PreparedStatement ps = DBPreparedStatements.getPreparedStatement();
        ResultSet rs = ps.executeQuery();
        //Populate list
        while (rs.next()) {
            //get information from DB
            int countyId = rs.getInt("Country_ID");
            String country= rs.getString("Country");
            Date createDate= rs.getDate("Create_Date");
            String createdBy= rs.getString("Created_By");
            Timestamp lastUpdate= rs.getTimestamp("Last_Update");
            String lastUpdatedBy= rs.getString("Last_Updated_By");
            //insert into observablelist
            Countries.addCountry(new Countries(countyId,country,createDate,createdBy,lastUpdate,lastUpdatedBy));
        }
    }

    /** Populates Customers list.*/
    public static void populateCustomers() throws SQLException {
        //clear old list
        Customers.allCustomers.clear();

        String insertStatement = "SELECT * FROM customers";
        DBPreparedStatements.setPreparedStatement(conn,insertStatement);
        PreparedStatement ps = DBPreparedStatements.getPreparedStatement();
        ResultSet rs = ps.executeQuery();
        //Populate list
        while (rs.next()) {
            //get information from DB
            int Customer_ID = rs.getInt("Customer_ID");
            String Customer_Name = rs.getString("Customer_Name");
            String Address = rs.getString("Address");
            String Postal_Code = rs.getString("Postal_Code");
            String Phone = rs.getString("Phone");
            Date createDate = rs.getDate("Create_Date");
            String createdBy = rs.getString("Created_By");
            Timestamp lastUpdate = rs.getTimestamp("Last_Update");
            String lastUpdatedBy = rs.getString("Last_Updated_By");
            int Division_ID = rs.getInt("Division_ID");
            //insert into observablelist
            Customers.addCustomers(new Customers(Customer_ID, Customer_Name, Address, Postal_Code, Phone, createDate, createdBy, lastUpdate, lastUpdatedBy, Division_ID));
        }
    }

    /** populates FLD list.*/
    public static void populateDivisons() throws SQLException {
        //clear old list
        FirstLevelDivision.getFirstLevelDivisions().clear();

        //make SQL statment
        String insertStatement = "SELECT * FROM first_level_divisions WHERE Country_ID = " + patient.countryID;
        DBPreparedStatements.setPreparedStatement(DBDriver.getConnection(),insertStatement);
        PreparedStatement ps = DBPreparedStatements.getPreparedStatement();
        ResultSet rs = ps.executeQuery();

        //Populate list
        while (rs.next()) {
            //get information from DB
            int Division_ID= rs.getInt("Division_ID");
            String Division= rs.getString("Division");
            String Create_Date= rs.getString("Create_Date");
            String Created_By= rs.getString("Created_By");
            Timestamp Last_Update= rs.getTimestamp("Last_Update");
            String Last_Updated_By= rs.getString("Last_Updated_By");
            int COUNTRY_ID= rs.getInt("COUNTRY_ID");
            //insert into observablelist
            FirstLevelDivision.addFirstLevelDivision(new FirstLevelDivision (Division_ID,Division,Create_Date,Created_By,Last_Update,Last_Updated_By,COUNTRY_ID));
            //FirstLevelDivision.addFirstLevelDivision(Division);
            System.out.println(Division_ID + "||" + Division);
        }
    }

    /** populates FLD list for modify.*/
    public static void populateDivisonsM() throws SQLException {
        //clear old list
        FirstLevelDivision.getFirstLevelDivisions().clear();

        //make SQL statment
        String insertStatement = "SELECT * FROM first_level_divisions WHERE Country_ID = " + patient.countryIDM;
        DBPreparedStatements.setPreparedStatement(DBDriver.getConnection(),insertStatement);
        PreparedStatement ps = DBPreparedStatements.getPreparedStatement();
        ResultSet rs = ps.executeQuery();

        //Populate list
        while (rs.next()) {
            //get information from DB
            int Division_ID= rs.getInt("Division_ID");
            String Division= rs.getString("Division");
            String Create_Date= rs.getString("Create_Date");
            String Created_By= rs.getString("Created_By");
            Timestamp Last_Update= rs.getTimestamp("Last_Update");
            String Last_Updated_By= rs.getString("Last_Updated_By");
            int COUNTRY_ID= rs.getInt("COUNTRY_ID");
            //insert into observablelist
            FirstLevelDivision.addFirstLevelDivision(new FirstLevelDivision (Division_ID,Division,Create_Date,Created_By,Last_Update,Last_Updated_By,COUNTRY_ID));
            //FirstLevelDivision.addFirstLevelDivision(Division);
            System.out.println(Division_ID + "||" + Division);
        }
    }

    /** Populates Contacts list.*/
    public static void populateContact() throws SQLException {
        //clear out old list
        Contacts.allContacts().clear();

        //make SQL statment
        String insertStatement = "SELECT * FROM contacts";
        DBPreparedStatements.setPreparedStatement(DBDriver.getConnection(),insertStatement);
        PreparedStatement ps = DBPreparedStatements.getPreparedStatement();
        ResultSet rs = ps.executeQuery();

        //Populate list
        while (rs.next()) {
            //get information from DB
            int Contact_ID= rs.getInt("Contact_ID");
            String Contact_Name= rs.getString("Contact_Name");
            String Email= rs.getString("Email");
            //insert into observablelist
            Contacts.addContacts(new Contacts (Contact_ID, Contact_Name, Email));
            System.out.println(Contact_ID + "||" + Contact_Name + "||" + Email);
        }
    }

    //------Finds------\\

    /** Finds FLD from selected from table.
     * @param divisonID FLD to be found*/
    public static FirstLevelDivision findDivisons(int divisonID) throws SQLException {
        //make SQL statment
        String insertStatement = "SELECT * FROM first_level_divisions WHERE Division_ID = " + divisonID;
        DBPreparedStatements.setPreparedStatement(DBDriver.getConnection(),insertStatement);
        PreparedStatement ps = DBPreparedStatements.getPreparedStatement();
        ResultSet rs = ps.executeQuery();

        FirstLevelDivision result = null;
        //Populate list
        while (rs.next()) {
            //get information from DB
            int Division_ID = rs.getInt("Division_ID");
            String Division = rs.getString("Division");
            String Create_Date = rs.getString("Create_Date");
            String Created_By = rs.getString("Created_By");
            Timestamp Last_Update = rs.getTimestamp("Last_Update");
            String Last_Updated_By = rs.getString("Last_Updated_By");
            int COUNTRY_ID = rs.getInt("COUNTRY_ID");
            //insert into observablelist
            result = new FirstLevelDivision(Division_ID, Division, Create_Date, Created_By, Last_Update, Last_Updated_By, COUNTRY_ID);
        }
        return result;
    }

    /** Finds customer from selected from table.
     * @param customerID customer ID to be found.*/
    public static Customers findCustomer(int customerID) throws SQLException {
        //make SQL statment
        String insertStatement = "SELECT * FROM customers WHERE Customer_ID = " + customerID;
        DBPreparedStatements.setPreparedStatement(DBDriver.getConnection(),insertStatement);
        PreparedStatement ps = DBPreparedStatements.getPreparedStatement();
        ResultSet rs = ps.executeQuery();

        Customers result = null;
        //Populate list
        while (rs.next()) {
            //get information from DB
            int Customer_ID = rs.getInt("Customer_ID");
            String Customer_Name = rs.getString("Customer_Name");
            String Address = rs.getString("Address");
            String Postal_Code = rs.getString("Postal_Code");
            String Phone = rs.getString("Phone");
            Date createDate = rs.getDate("Create_Date");
            String createdBy = rs.getString("Created_By");
            Timestamp lastUpdate = rs.getTimestamp("Last_Update");
            String lastUpdatedBy = rs.getString("Last_Updated_By");
            int Division_ID = rs.getInt("Division_ID");
            //insert into observablelist
            result = new Customers(Customer_ID, Customer_Name, Address, Postal_Code, Phone, createDate, createdBy, lastUpdate, lastUpdatedBy, Division_ID);
        }
        return result;
    }

    /** Finds contact from selected from customer.
     * @param contactID ContactId of contact to be found.*/
    public static Contacts findContact(int contactID) throws SQLException {
        //make SQL statment
        String insertStatement = "SELECT * FROM contacts WHERE Contact_ID = " + contactID;
        DBPreparedStatements.setPreparedStatement(DBDriver.getConnection(),insertStatement);
        PreparedStatement ps = DBPreparedStatements.getPreparedStatement();
        ResultSet rs = ps.executeQuery();

        Contacts result = null;
        //Populate list
        while (rs.next()) {
            //get information from DB
            int Contact_ID = rs.getInt("Contact_ID");
            String Contact_Name = rs.getString("Contact_Name");
            String Email = rs.getString("Email");
            //insert into observablelist
            result = new Contacts(Contact_ID, Contact_Name, Email);
        }
        return result;
    }

    //------Checks------\\

    /** Checks for appointments before deleting a customer.
     * @param customerID customerID of Customer trying to be deleted.*/
    public static int checkForAppointmetnsBeforeDelete(int customerID) throws SQLException {
        //make SQL statment
        String insertStatement = "SELECT * FROM appointments WHERE Customer_ID = " + customerID;
        DBPreparedStatements.setPreparedStatement(DBDriver.getConnection(),insertStatement);
        PreparedStatement ps = DBPreparedStatements.getPreparedStatement();
        ResultSet rs = ps.executeQuery();

        int numAppts = 0;
        ArrayList<Integer> result = new ArrayList<Integer>(); // Create an ArrayList object

        //Populate list
        while (rs.next()) {
            //get information from DB
            int Appointment_ID = rs.getInt("Appointment_ID");
            //insert into array
            result.add(Appointment_ID);
            numAppts += 1;
       }
        return numAppts;
    }

    /** Checks 15 minutes before the current time for appointments.*/
    public static Appointment checkForApptsIn15() throws SQLException {
        String insertStatement = "SELECT * FROM appointments WHERE Start Between ? AND ? ";
        DBPreparedStatements.setPreparedStatement(DBDriver.getConnection(),insertStatement);
        PreparedStatement ps = DBPreparedStatements.getPreparedStatement();

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime soon = LocalDateTime.now().plusMinutes(15);
        Appointment result = null;

        ps.setTimestamp(1, Timestamp.valueOf(now));
        ps.setTimestamp(2, Timestamp.valueOf(soon));


        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            //get information from DB
            int Appointment_ID = rs.getInt("Appointment_ID");
            String title = rs.getString("title");
            String description = rs.getString("Description");
            String location = rs.getString("Location");
            String type = rs.getString("Type");
            LocalDateTime starts = rs.getTimestamp("Start").toLocalDateTime();
            LocalDateTime ends = rs.getTimestamp("end").toLocalDateTime();
            Date createDate = rs.getDate("Create_Date");
            String createdBy = rs.getString("Created_By");
            Timestamp lastUpdate = rs.getTimestamp("Last_Update");
            String lastUpdateBy = rs.getString("Last_Updated_By");
            int customerId = rs.getInt("Customer_ID");
            int userId = rs.getInt("User_ID");
            int contactId = rs.getInt("Contact_ID");
            //insert appointments into observablelist for AppointmentTableView
            result = new Appointment(Appointment_ID, title, description, location, type, starts,
                    ends, createDate, createdBy, lastUpdate, lastUpdateBy, customerId, userId, contactId);
        }
        return result;
    }

    /** Checks for appointment overlap.
     * @param start The start time of the appointment.
     * @param end The end time of the appointment.
     * @param ApptIDPass Appointment ID of the appointment. */
    public static boolean checkTimeFrame(LocalDateTime start, LocalDateTime end, int ApptIDPass) throws SQLException {
        String insertStatement = "SELECT * FROM appointments WHERE Start Between ? AND ? OR End Between ? AND ?";

        DBPreparedStatements.setPreparedStatement(DBDriver.getConnection(),insertStatement);
        PreparedStatement ps = DBPreparedStatements.getPreparedStatement();
        boolean goAhead = false;
        Appointment result = null;

        ps.setTimestamp(1, Timestamp.valueOf(start));
        ps.setTimestamp(2, Timestamp.valueOf(end));
        ps.setTimestamp(3, Timestamp.valueOf(end));
        ps.setTimestamp(4, Timestamp.valueOf(start));

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            //get information from DB
            int Appointment_ID = rs.getInt("Appointment_ID");
            String title = rs.getString("title");
            String description = rs.getString("Description");
            String location = rs.getString("Location");
            String type = rs.getString("Type");
            LocalDateTime starts = rs.getTimestamp("Start").toLocalDateTime();
            LocalDateTime ends = rs.getTimestamp("end").toLocalDateTime();
            Date createDate = rs.getDate("Create_Date");
            String createdBy = rs.getString("Created_By");
            Timestamp lastUpdate = rs.getTimestamp("Last_Update");
            String lastUpdateBy = rs.getString("Last_Updated_By");
            int customerId = rs.getInt("Customer_ID");
            int userId = rs.getInt("User_ID");
            int contactId = rs.getInt("Contact_ID");
            //insert appointments into observablelist for AppointmentTableView
            result = new Appointment(Appointment_ID, title, description, location, type, starts,
                    ends, createDate, createdBy, lastUpdate, lastUpdateBy, customerId, userId, contactId);
        }

        if (result == null || result.getApptId() == ApptIDPass) {
            goAhead = true;
            System.out.println("You have the goahed");
        } else {
            goAhead = false;
            System.out.println("time frame occupied");
        }

        return goAhead;
    }

    /** Checks logged in user credentials.
     * @param name Username of the user.*/
    public static Users getLoggedInUser(String name) throws SQLException {
        //make SQL statment
        String insertStatement = "SELECT * FROM users WHERE User_Name = ?";
        DBPreparedStatements.setPreparedStatement(DBDriver.getConnection(),insertStatement);
        PreparedStatement ps = DBPreparedStatements.getPreparedStatement();
        ps.setString(1, name);
        ResultSet rs = ps.executeQuery();

        Users result = null;
        //Populate list
        while (rs.next()) {
            //get information from DB
            int User_ID = rs.getInt("User_ID");
            String User_Name = rs.getString("User_Name");
            String Password = rs.getString("Password");
            String Create_Date = rs.getString("Create_Date");
            String Created_By = rs.getString("Created_By");
            Timestamp Last_Update = rs.getTimestamp("Last_Update");
            String Last_Updated_By = rs.getString("Last_Updated_By");
            //insert into observablelist
            result = new Users(User_ID, User_Name, Password, Create_Date, Created_By, Last_Update, Last_Updated_By);
        }
        return result;
    }

    //------REPORTS------\\

    /** Returns number of appointments by month and type.
     * @param month month to be searched.
     * @param type type to be searched.*/
    public static String reportMonthAndType(String month, String type) throws SQLException{
        //make SQL statment
        String insertStatement = "SELECT count(start) FROM appointments WHERE (EXTRACT(month FROM start) = ?) AND Type = ?";
        DBPreparedStatements.setPreparedStatement(DBDriver.getConnection(),insertStatement);
        PreparedStatement ps = DBPreparedStatements.getPreparedStatement();
        ps.setString(1, month);
        ps.setString(2, type);
        ResultSet rs = ps.executeQuery();
        
        String result = null;
        while (rs.next()) {
            //get information from DB
             result = rs.getString(1);
        }
        return result;
    }

    /** Returns number of customers by location.
     * @param location location to be searched.*/
    public static String reportNumCustomers(String location) throws SQLException{
        //make SQL statment
        String insertStatement = " SELECT count(Customer_ID) FROM appointments WHERE Location = ?";
        DBPreparedStatements.setPreparedStatement(DBDriver.getConnection(),insertStatement);
        PreparedStatement ps = DBPreparedStatements.getPreparedStatement();
        ps.setString(1, location);
        ResultSet rs = ps.executeQuery();

        String result = null;
        while (rs.next()) {
            //get information from DB
            result = rs.getString(1);
        }
        return result;
    }

    /** Returns number of customers by location.
     * @param location location to be searched.*/
    public static String reportNumAppointments(String location) throws SQLException {
        //make SQL statment
        String insertStatement = " SELECT count(Appointment_ID) FROM appointments WHERE Location = ?";
        DBPreparedStatements.setPreparedStatement(DBDriver.getConnection(), insertStatement);
        PreparedStatement ps = DBPreparedStatements.getPreparedStatement();
        ps.setString(1, location);
        ResultSet rs = ps.executeQuery();

        String result = null;
        while (rs.next()) {
            //get information from DB
            result = rs.getString(1);
        }
        return result;
    }
}