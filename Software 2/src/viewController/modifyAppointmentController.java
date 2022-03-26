package viewController;
// Java:8.0.22

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import main.jdbc.DBPreparedStatements;
import main.jdbc.DBResultSets;
import model.*;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;


/** Controls the new appointment screen.*/
public class modifyAppointmentController implements Initializable{
    public ComboBox <Customers>customerBox;
    public Label nameFieldCust;
    public Label zipField;
    public Label addressField;
    public Label phoneField;
    public ComboBox<Contacts> contactBox;
    public ComboBox timeBox;
    public ComboBox apptLengthBox;
    public TextField titleField;
    public TextArea descriptionField;
    public TextField locaitonField;
    public TextField typeField;
    public DatePicker date;
    public Label contactName;
    public Label ContactEmail;


    public void toHome(ActionEvent actionEvent) throws IOException {
        Window.switchScreen("/viewController/homeView.fxml", actionEvent);
    }

    public void create (ActionEvent actionEvent) throws IOException, SQLException {
        //Get information
        String Title = titleField.getText();
        String Location = locaitonField.getText();
        String Type = typeField.getText();
        String Description = descriptionField.getText();
        LocalTime time = (LocalTime) timeBox.getValue();
        LocalDate apptDate = date.getValue();
        LocalDateTime Start = LocalDateTime.of(apptDate.getYear(),apptDate.getMonth(),apptDate.getDayOfMonth(),time.getHour(),time.getMinute());
        long length = (long) apptLengthBox.getValue();
        LocalDateTime End = Start.plusMinutes(Long.valueOf(length));
        String Create_Date = LocalDateTime.now().toString();
        String Created_By = logInController.loggedInUser.getUser_Name();
        Timestamp Last_Update = new Timestamp(System.currentTimeMillis());
        String Last_Updated_By = logInController.loggedInUser.getUser_Name();

        int Customer_ID = customerBox.getValue().getCustomer_ID();
        int User_ID = logInController.loggedInUser.getUser_ID();
        int Contact_ID = contactBox.getValue().getContact_ID();

        Messages.appointmentCreated(Title,Location,Type,Description,Start.toString(),End.toString(),Create_Date,Created_By,Last_Update,Last_Updated_By,Customer_ID,User_ID,Contact_ID);
        DBPreparedStatements.addAppointment(Title,Location,Type,Description,Start,End,Create_Date,Created_By,Last_Update,Last_Updated_By,Customer_ID,User_ID,Contact_ID);

        //Go home
        Window.switchScreen("/viewController/homeView.fxml",actionEvent);
    }

    public void populateCustomersInfo(ActionEvent actionEvent) throws SQLException {
        Customers selected = customerBox.getValue();

        nameFieldCust.setText(selected.getCustomer_Name());
        addressField.setText(selected.getAddress());
        zipField.setText(selected.getPostal_Code());
        phoneField.setText(selected.getPhone());
    }

    public void populateContactInfo(ActionEvent actionEvent) throws SQLException {
        Contacts selected = contactBox.getValue();

        contactName.setText(selected.getContact_Name());
        ContactEmail.setText(selected.getEmail());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        userIDLabel.setText(logInController.loggedInUser);

        //Populate contact box
        try {
            DBResultSets.populateContact();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        contactBox.setItems(Contacts.allContacts());

        //Populate Customer box
        try {
            DBResultSets.populateCustomers();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        customerBox.setItems(Customers.getAllCustomers());

        //Populate timebox
        Time.populateHoursOfOperation();
        timeBox.setItems(Time.hoursOfOpperation);

        //Populate apptLenght
        Time.populateDuration();
        apptLengthBox.setItems(Time.apptDuration);
    }
}
