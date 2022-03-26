package model;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import main.jdbc.DBPreparedStatements;
import main.jdbc.DBResultSets;

import javax.swing.*;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

/** Creates and controls messages .*/
public class Messages {
    private static Alert alertInfo = new Alert(Alert.AlertType.INFORMATION,"Is this for the button", ButtonType.OK);
    private static Alert alertError = new Alert(Alert.AlertType.ERROR," String s?", ButtonType.CANCEL );
    private static Alert alertConfirm = new Alert(Alert.AlertType.CONFIRMATION);
    private static Alert alertWarning = new Alert(Alert.AlertType.WARNING);

    /** Displays alert for an invalid user name/Password combination.*/
    public static void invalidPassword() {
        //Change to french
        if (Locale.getDefault().getLanguage().equals("fr")) {
            alertError.setTitle("Nom d'utilisateur ou mot de passe invalide");
            alertError.setHeaderText("Nom d'utilisateur ou mot de passe invalide. Veuillez réessayer.");
            alertError.showAndWait().filter(response -> response == ButtonType.OK);
        }
        else {
            alertError.setTitle("Invalid username or password");
            alertError.setHeaderText("Invalid username or password. Please try again.");
            alertError.showAndWait();
        }
    }

    /** Displays alert for creating a patient.*/
    public static void patientCreated (String Customer_Name, String Address, String Postal_Code, String Phone, String Create_Date, String Created_By, Timestamp Last_Update, String Last_Updated_By, int Division_ID) throws SQLException {
        alertConfirm.setTitle("Create patient");
        alertConfirm.setHeaderText("Create the following patient:");
        alertConfirm.setContentText( "Customer Name: " + Customer_Name
                + "\n" + "Address" + Address
                + "\n" + "Postal_Code :" + Postal_Code
                + "\n" + "Phone  :" + Phone
                + "\n" + "Create_Date  :" + Create_Date
                + "\n" + "Created_By  :" + Created_By
                + "\n" + "Last_Update  :" + Last_Update
                + "\n" + "Last_Updated_By  :" + Last_Updated_By
                + "\n" + "Division_ID  :" + Division_ID);
        alertConfirm.showAndWait().filter(response -> response == ButtonType.OK).ifPresent(response -> {
                    try {
                        DBPreparedStatements.addCustomer(Customer_Name, Address, Postal_Code, Phone, Create_Date, Created_By, Last_Update, Last_Updated_By, Division_ID);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                });
    }

    /** Displays alert for modifying a patient.*/
    public static void patientModified (String Customer_Name, String Address, String Postal_Code, String Phone, String Last_Update, String Last_Updated_By, int Division_ID, int Customer_ID) throws SQLException {
        alertConfirm.setTitle("Modify patient");
        alertConfirm.setHeaderText("Modify patient to the following:");
        alertConfirm.setContentText(
                "Customer_ID: " + Customer_ID
                + "\n" + "Customer Name: " + Customer_Name
                + "\n" + "Address" + Address
                + "\n" + "Postal_Code :" + Postal_Code
                + "\n" + "Phone  :" + Phone
                + "\n" + "Last_Update  :" + Last_Update
                + "\n" + "Last_Updated_By  :" + Last_Updated_By
                + "\n" + "Division_ID  :" + Division_ID);
        alertConfirm.showAndWait().filter(response -> response == ButtonType.OK).ifPresent(response -> {
            try {
                DBPreparedStatements.modifyCustomer(Customer_Name,Address,Postal_Code,Phone,Last_Update,Last_Updated_By,Division_ID,Customer_ID);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
    }

    /** Displays alert for deleting a patient.*/
    public static boolean patientDelete() {
        alertWarning.setTitle("Delete patient");
        alertWarning.setHeaderText("Warning this will permanently delete the patient");
        alertWarning.setContentText("Are you sure you would like to delete?");
        alertWarning.showAndWait();
        Optional<ButtonType> confirm = alertWarning.showAndWait();
        if (confirm.get() == ButtonType.OK){
                return true;
            }
        else
            return false;
    }

    /** Displays alert for deleting an appointment.*/
    public static void apointmentDelete (Appointment appt) {
        alertWarning.setTitle("Cancel Appointment");
        alertWarning.setHeaderText("Appointment was canceled.");
        alertWarning.setContentText("Appointment #:" + appt.getApptId() +" for " + appt.getType() + " was canceled.");
        alertWarning.showAndWait();
    }

    /** Displays alert for modifying an appointment.*/
    public static void apointmentModified (int appt) {
        alertWarning.setTitle("Modified Appointment");
        alertWarning.setHeaderText("Appointment was modified.");
        alertWarning.setContentText("Appointment #:" + appt  + " was modified.");
        alertWarning.showAndWait();
    }

    /** Displays alert for a failed delete of a patient.*/
    public static void patientNOTdelete(int numAppts){
        alertWarning.setTitle("Delete NOT patient");
        alertWarning.setHeaderText("patient could not be deleted.");
        alertConfirm.setContentText("patient has "+numAppts+ " appoints. You must cancel appointments before you delete the patient");
        alertWarning.showAndWait();
    }

    /** Displays alert for creating an appointment.*/
    public static void appointmentCreated (String Title,String Location,String Type,String Description,String Start,String End,String Create_Date,String Created_By,Timestamp Last_Update ,String Last_Updated_By, int Customer_ID, int User_ID,int Contact_ID) throws SQLException {
        alertConfirm.setTitle("appointment created");
        alertConfirm.setHeaderText("The following appointment was created:");
        alertConfirm.setContentText(
                "Title: " + Title
                + "\n" + "Location" + Location
                + "\n" + "Type :" + Type
                + "\n" + "Description  :" + Description
                + "\n" + "Start  :" + Start
                + "\n" + "End  :" + End
                + "\n" + "Create_Date  :" + Create_Date
                + "\n" + "Created_By  :" + Created_By
                + "\n" + "Last_Updated_By  :" + Last_Updated_By
                + "\n" + "Last_Update  :" + Last_Update
                + "\n" + "Customer_ID  :" + Customer_ID
                + "\n" + "User_ID  :" + User_ID
                + "\n" + "Contact_ID  :" + Contact_ID);
        alertConfirm.showAndWait();
    }

    /** Displays alert for a time overlap.*/
    public static void timeFrameOccupied(){
        alertError.setTitle("timeFrameOccupied");
        alertError.setHeaderText("Please select another time.");
        alertError.showAndWait();
    }

    /** Displays alert for an appointment in 15 minutes.*/
    public static void appointmentIn15Min(int appointmentID, LocalDateTime start){
        alertWarning.setTitle("Appointment in the next 15 minutes");
        alertWarning.setHeaderText("Appointment " + appointmentID + " starts at " + start);
        alertWarning.showAndWait();
    }

    /** Displays alert for no appointment in 15 minutes.*/
    public static void NoAppointmentIn15Min(){
        alertInfo.setTitle("No Appointment in the next 15 minutes");
        alertInfo.setHeaderText("There are not appointments in the next 15 minutes");
        alertInfo.showAndWait();
    }

    /** Displays alert for creating an appointment out of business hours.*/
    public static void outsideBusinessHours(){
        alertError.setTitle("Outside of business hour");
        alertError.setHeaderText("Please select a time between 8:00 a.m. and 10:00 p.m.");
        alertError.showAndWait();
    }
}