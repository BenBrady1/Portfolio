package viewController;
// Java:8.0.22

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import main.jdbc.DBResultSets;
import model.*;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/** Controls the reports screen.*/
public class reportsController implements Initializable {
    public Button back_button;
    public Label typeMonthAnswer;
    public TextField type;
    public ChoiceBox month;
    public Label numPatientsAnswer;
    public Label numAppointmentsAnswer;
    public TableColumn apptIdCol;
    public TableView scheduleTable;
    public TableColumn titleCol;
    public TableColumn DescriptionCol;
    public TableColumn locationCol;
    public TableColumn ContactCol;
    public TableColumn typeCol;
    public TableColumn startCol;
    public TableColumn endCol;
    public TableColumn customerIdCol;
    public ChoiceBox <Contacts> contactBox;
    public ComboBox locaitonBoxAppt;
    public ComboBox locationBoxPatients;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Time.populatemonths();
        month.setItems(Time.months);

        try {
            DBResultSets.populateContact();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        contactBox.setItems(Contacts.allContacts());

        try {
            DBResultSets.populateLocations();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        locaitonBoxAppt.setItems(Location.allLocations());
        locationBoxPatients.setItems(Location.allLocations());


    }

    /** Brings users to homepage.*/
    public void toHome(ActionEvent actionEvent) throws IOException {
        Window.goHome(actionEvent);
    }

    /** Populates appointmentTable.*/
    public void populateAppointmentTable() throws SQLException {
        Contacts selected = contactBox.getValue();
        DBResultSets.populateCustomerSchedule(selected.getContact_ID());
        scheduleTable.setItems(Appointment.contactSchedule());
        apptIdCol.setCellValueFactory(new PropertyValueFactory<>("apptId")); //Get Col info
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title")); //Get Col info
        DescriptionCol.setCellValueFactory(new PropertyValueFactory<>("description")); //Get Col info
        locationCol.setCellValueFactory(new PropertyValueFactory<>("location")); //Get Col info
        ContactCol.setCellValueFactory(new PropertyValueFactory<>("contactId"));  // Tell Table where to get info
        startCol.setCellValueFactory(new PropertyValueFactory<>("start")); //Get Col info
        endCol.setCellValueFactory(new PropertyValueFactory<>("end")); //Get Col info
        customerIdCol.setCellValueFactory(new PropertyValueFactory<>("customerId")); //Get Col info
        typeCol.setCellValueFactory(new PropertyValueFactory<>("userId")); //Get Col info
    }

    /** Gets results for type/month report.*/
    public void typeMonth(ActionEvent actionEvent) throws IOException, SQLException {
        String results = DBResultSets.reportMonthAndType(month.getValue().toString(),type.getText());
        typeMonthAnswer.setText(String.valueOf(results));
    }

    /** Gets results for number of customers report.*/
    public void numCustomers(ActionEvent actionEvent) throws IOException, SQLException {
        Location selected = (Location) locationBoxPatients.getValue();
        String results = DBResultSets.reportNumCustomers(selected.getCity());
        numPatientsAnswer.setText(String.valueOf(results));
    }

    /** Gets results for number of appointments report.*/
    public void numAppointments(ActionEvent actionEvent) throws IOException, SQLException {
        Location selected = (Location) locaitonBoxAppt.getValue();
        String results = DBResultSets.reportNumAppointments(selected.getCity());
        numAppointmentsAnswer.setText(String.valueOf(results));
    }
}
