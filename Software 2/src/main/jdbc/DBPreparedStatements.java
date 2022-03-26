package main.jdbc;

import model.Users;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/** Executes SQL queries.*/
public class DBPreparedStatements {
    public static Connection conn = DBDriver.getConnection();
    private static PreparedStatement statement;

    /** Sets the prepared statement for an SQL query
     * create statement object
      */
    public static void setPreparedStatement(Connection conn, String sqlStatement) throws SQLException {
        statement = conn.prepareStatement(sqlStatement);
    }

    /** Returns the prepared statement for an SQL query
     * @return statement The SQL statment
     */
    //return statement object
    public static PreparedStatement getPreparedStatement(){
        return statement;
    }

    /** Adds a customer to the Database.
     * @param Customer_Name Name of the customer.
     * @param Address Address of the customer.
     * @param Postal_Code zip code of the customer.
     * @param Phone Phone number of the customer.
     * @param Created_By Person to create the entry
     * @param Last_Updated_By Person to most recently update the entry.
     * @param Division_ID Division ID of the customer.
     * @throws SQLException
     */
    public static void addCustomer( String Customer_Name, String Address, String Postal_Code, String Phone, String Create_Date,String Created_By, Timestamp Last_Update, String Last_Updated_By, int Division_ID) throws SQLException {
       String insertStatement = "INSERT INTO customers (Customer_Name,Address,Postal_Code,Phone,Create_Date,Created_By,Last_Update, Last_Updated_by, Division_ID) VALUES (?,?,?,?,?,?,?,?,?)";

        //Create prepared Statement
        setPreparedStatement(conn,insertStatement);

        PreparedStatement ps = getPreparedStatement();

        //Key-value Mapping
        ps.setString(1, Customer_Name);
        ps.setString(2, Address);
        ps.setString(3, Postal_Code);
        ps.setString(4, Phone);
        ps.setString(5,Create_Date);
        ps.setString(6, Created_By);
        ps.setTimestamp(7, Last_Update);
        ps.setString(8, Last_Updated_By);
        ps.setInt(9, Division_ID);

        // Execute prepared statement.
        ps.execute();
        //Check rows effected
        if (ps.getUpdateCount() > 0)
            System.out.println(ps.getUpdateCount() + " rows effected");
    }

    /**
     * Creates appointment in DB.
     * @param Title Name of the appointment.
     * @param Description description of the appointment.
     * @param Location Location of the appointment.
     * @param Type Type of appointment.
     * @param Start Start time of the appointment.
     * @param End End time of the appointment.
     * @param Create_Date Date the appointment was created.
     * @param Created_By Identifies who created the appointment.
     * @param Last_Update Most recent update to the appointment.
     * @param Last_Updated_By Identifies who did the last update to the appointment.
     * @param Customer_ID Unique ID for customer.
     * @param User_ID Unique ID for user.
     * @param Contact_ID Unique ID for contact.
     */
    public static void addAppointment(String Title, String Location, String Type, String Description, LocalDateTime Start, LocalDateTime End, String Create_Date, String Created_By, Timestamp Last_Update , String Last_Updated_By, int Customer_ID, int User_ID, int Contact_ID) throws SQLException {
        String insertStatement = "INSERT INTO appointments (Title, Description, Location, Type, Start, End, Create_Date, Created_By, Last_Update, Last_Updated_By, Customer_ID, User_ID, Contact_ID) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";

        //Create prepared Statement
        setPreparedStatement(conn,insertStatement);

        PreparedStatement ps = getPreparedStatement();

        //Key-value Mapping
        ps.setString(1, Title);
        ps.setString(2, Description);
        ps.setString(3, Location);
        ps.setString(4, Type);
        ps.setTimestamp(5, Timestamp.valueOf(Start));
        ps.setTimestamp(6, Timestamp.valueOf(End));
        ps.setString(7, Create_Date);
        ps.setString(8, Created_By);
        ps.setTimestamp(9, Last_Update);
        ps.setString(10, Last_Updated_By);
        ps.setInt(11, Customer_ID);
        ps.setInt(12, User_ID);
        ps.setInt(13, Contact_ID);

        // Execute prepared statement.
        ps.execute();
        //Check rows effected
        if (ps.getUpdateCount() > 0)
            System.out.println(ps.getUpdateCount() + " rows effected");
    }

    /**
     * Modifies Appointment in DB.
     * @param Title Name of the appointment.
     * @param Description description of the appointment.
     * @param Location Location of the appointment.
     * @param Type Type of appointment.
     * @param Start Start time of the appointment.
     * @param End End time of the appointment.
     * @param Create_Date Date the appointment was created.
     * @param Created_By Identifies who created the appointment.
     * @param Last_Update Most recent update to the appointment.
     * @param Last_Updated_By Identifies who did the last update to the appointment.
     * @param Customer_ID Unique ID for customer.
     * @param User_ID Unique ID for user.
     * @param Contact_ID Unique ID for contact.
     */
    public static void modifyAppointment( String Title,String Description,String Location,String Type,LocalDateTime Start,LocalDateTime End,String Create_Date,String Created_By,Timestamp Last_Update ,String Last_Updated_By, int Customer_ID, int User_ID,int Contact_ID, int Appointment_ID) throws SQLException {
        String insertStatement = "UPDATE appointments SET Title = ?, Description = ?,  Location = ?,  Type = ?,  Start  = ?, End  = ?, Create_Date  = ?, Created_By  = ?, Last_Update  = ?, Last_Updated_By  = ?, Customer_ID  = ?, User_ID  = ?, Contact_ID = ? WHERE Appointment_ID = ?";

        //Create prepared Statement
        setPreparedStatement(conn,insertStatement);

        PreparedStatement ps = getPreparedStatement();

        //Key-value Mapping
        ps.setString(1, Title);
        ps.setString(2, Description);
        ps.setString(3, Location);
        ps.setString(4, Type);
        ps.setTimestamp(5,Timestamp.valueOf(Start));
        ps.setTimestamp(6, Timestamp.valueOf(End));
        ps.setString(7, Create_Date);
        ps.setString(8, Created_By);
        ps.setTimestamp(9, Last_Update);
        ps.setString(10, Last_Updated_By);
        ps.setInt(11, Customer_ID);
        ps.setInt(12, User_ID);
        ps.setInt(13, Contact_ID);
        ps.setInt(14, Appointment_ID);

        // Execute prepared statement.
        ps.execute();
        //Check rows effected
        if (ps.getUpdateCount() > 0)
            System.out.println(ps.getUpdateCount() + " rows effected");
    }

    /** Constructor for Customers.
     * @param Customer_ID Name of Customer.
     * @param Address Customers address.
     * @param Postal_Code zip code of address.
     * @param Phone Phone number of address.
     * @param Last_Update Most recent update to the appointment.
     * @param Last_Updated_By Identifies who did the last update to the appointment.
     * @param Division_ID Unique ID for FLD.
     * */
    public static void modifyCustomer( String Customer_Name, String Address, String Postal_Code, String Phone, String Last_Update, String Last_Updated_By, int Division_ID, int Customer_ID) throws SQLException {
        String insertStatement = "UPDATE customers SET Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?, Last_Update =  ?, Last_Updated_By = ?, Division_ID = ? WHERE Customer_ID = ?";

        //Create prepared Statement
        setPreparedStatement(conn,insertStatement);

        PreparedStatement ps = getPreparedStatement();

        //Key-value Mapping
        ps.setString(1, Customer_Name);
        ps.setString(2, Address);
        ps.setString(3, Postal_Code);
        ps.setString(4, Phone);
        ps.setString(5, Last_Update);
        ps.setString(6, Last_Updated_By);
        ps.setInt(7, Division_ID);
        ps.setInt(8, Customer_ID);

        // Execute prepared statement.
        ps.execute();
        //Check rows effected
        if (ps.getUpdateCount() > 0)
            System.out.println(ps.getUpdateCount() + " rows effected");
    }
    /** Deletes Customer from DB.
     * @param selectedItem selected CustomerID.*/
    public static void deleteCustomer(int selectedItem) throws SQLException {
        String insertStatement = "DELETE from customers WHERE Customer_ID = '" + selectedItem + "'";
        DBPreparedStatements.setPreparedStatement(DBDriver.getConnection(), insertStatement);
        PreparedStatement ps = DBPreparedStatements.getPreparedStatement();
        ps.execute();
    }

    /** Validates Username and password.
     * @param name name of user.
     * @param password password of user.*/
    public static boolean validatePassword(String name, String password) throws SQLException {
        String insertStatement = "SELECT Password FROM users WHERE User_Name = ? ";

        //Create prepared Statement
        setPreparedStatement(conn,insertStatement);
        PreparedStatement ps = getPreparedStatement();

        //Key-value Mapping
        ps.setString(1, name);
        // Execute prepared statement.
        ResultSet rs = ps.executeQuery();

        //Get results
        String key = null;

        if(rs.next())
            key = rs.getString(1);
            if (password.equals(key)) {
                System.out.println("Password match");
                return true;
            }
            else {
                System.out.println("No match");
                return false;
        }
   }


}
