package main.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/** Establishes a connection to the database.*/
public class DBDriver {

    /*** database Protocol.*/
    private static final String protocol = "jdbc";

    /*** database Vendor Name.*/
    private static final String vendorName = ":mysql:";

    /*** database name.*/
    private static final String location = "//localhost/";

    /*** database IP Address.*/
    private static final String databaseName = "client_schedule";

    /*** Database URL.*/
    private static final String jdbcURL = protocol + vendorName + location + databaseName + "?connectionTimeZone = SERVER"; // LOCAL

    /*** Driver reference */
    private static final String driver = "com.mysql.cj.jdbc.Driver";

    /*** Driver interface reference.*/
    private static final String MYSQLDriver = "com.mysql.jdbc.Driver";

    /*** Database username.*/
    private static final String userName = "sqlUser";

    /*** Database password.*/
    private static final String passWord = "Passw0rd!";

    /** Connection interface*/
    private static Connection connection;

    /** Starts connection.*/
    public static Connection startConnection(){
        try {
            Class.forName(MYSQLDriver);
            connection = DriverManager.getConnection(jdbcURL,userName,passWord);
            System.out.println("Connection successful");
        }
        catch (ClassNotFoundException  e){
        //System.out.println(e.getMessage());
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
    /** gets connection.*/
    public static Connection getConnection(){
        System.out.println("get connection");
        return connection;
    }

    /** Closes connection.*/
    public static void closeConnection() {
        try {
            connection.close();
            System.out.println("Connection closed!");
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
