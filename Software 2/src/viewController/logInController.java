package viewController;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.Main;
import main.jdbc.DBPreparedStatements;
import main.jdbc.DBResultSets;
import model.*;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.ResourceBundle;

/** Controls the login screen.*/
public class logInController implements Initializable {
    public static Users loggedInUser;
    public TextField userNameText;
    public TextField passwordText;
    public Label zoneID;
    public Label userName;
    public Label Password;

    /** Checks the Username and password against the one in the database & updates the LoginAttempts.*/
    public void validate(ActionEvent actionEvent) throws IOException, SQLException {
        String name = userNameText.getText();
        String password = passwordText.getText();
        //Update loging tracking
        Main.snitch.setUserName(name);
        Main.snitch.setDate(LocalDate.now());

        if (DBPreparedStatements.validatePassword(name,password) == true) {
            //Alert for appointment in 15 min
            DBResultSets.checkForApptsIn15();

            //Set currentUser
            loggedInUser = DBResultSets.getLoggedInUser(name);
            System.out.println(loggedInUser);

            //Get home test screen
            Window.goHome(actionEvent);

            //Populate tables
            DBResultSets.populateAppointments();
            System.out.println("Table Populated");

            //update login report
            Main.snitch.setLoginAtempt(Main.snitch.getLoginAtempt() + 1);
            Main.snitch.setSuccess(true);
            Main.snitch.setTimestamp(Timestamp.valueOf(LocalDateTime.now()));
            LoginAttempt.usingPath(Main.snitch.toString());

        }

       else {
           //Alert failed attempt
            Messages.invalidPassword();

            //update login report
            Main.snitch.setLoginAtempt(Main.snitch.getLoginAtempt()+1);
            Main.snitch.setSuccess(false);
            Main.snitch.setTimestamp(Timestamp.valueOf(LocalDateTime.now()));
            LoginAttempt.usingPath(Main.snitch.toString());
       }
    }

    /** Updates label to show Location/Time/Language */
    public static String getSystemSettings(){
        String location  = ZoneOffset.systemDefault().toString();
        String language = Locale.getDefault().toString();
        String parsedDate = DateTimeFormatter.ISO_DATE_TIME.format(LocalDateTime.now());
        return "User's location: " + location + " || User's time: " + parsedDate   + " || User's Language: " + language;
    }

    /** Checks Database to see if there are any appointments in the next 15 minutes */
    public void checkForApptIn15() throws SQLException{
        Appointment check = DBResultSets.checkForApptsIn15();
        if (check != null){
            Messages.appointmentIn15Min(check.getApptId(),check.getStart());
        }
        else {Messages.NoAppointmentIn15Min();}
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Display ZoneID
        zoneID.setText(getSystemSettings());

        // Translate to french
        if (Locale.getDefault().getLanguage().equals("fr")){
            userName.setText("Nom d'utilisateur");
            Password.setText("Mot de passe");
        }
    }
}
