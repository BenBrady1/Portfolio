package viewController;
// Java:8.0.22

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import main.jdbc.DBDriver;
import main.jdbc.DBPreparedStatements;
import main.jdbc.DBResultSets;
import model.*;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;

/** Controls the home screen.*/
public class homeController implements Initializable {
    public TableView<Appointment> appointmentTable;
    public TableColumn apptIdCol;
    public TableColumn titleCol;
    public TableColumn DescriptionCol;
    public TableColumn locationCol;
    public TableColumn ContactCol;
    public TableColumn startCol;
    public TableColumn endCol;
    public TableColumn customerIdCol;
    public TableColumn typeCol;
    public Button newAppt;
    public Button reports;
    public Button addPatient;
    public Label contactEmail;
    public Label nameFieldCust;
    public Label zipField;
    public Label addressField;
    public Label phoneField;
    public Label contactName;
    public TextField title;
    public TextField description;
    public TextField location;
    public ComboBox<Customers> customerBox;
    public ComboBox<Contacts> contactBox;
    public DatePicker date;
    public Button delete;
    public Button Modify;
    public Label userIDLabel;
    public ComboBox <LocalTime> apptTime;
    public ComboBox duration;
    public Label apptID;
    public TextField type;
    public RadioButton weekRadio;
    public ToggleGroup toggleGroup;
    public RadioButton monthRadio;
    public RadioButton allRadio;
    Appointment selected;

    /** Puts information in the tables.*/
    @Override
    public void initialize(URL location, ResourceBundle resources) {
       //populate appointments
        try {
            DBResultSets.populateAppointments();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            setAppointmentTable(Appointment.getAllAppointments());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        //set user label
        userIDLabel.setText(logInController.loggedInUser.getUser_Name());

        //Populate timebox
        Time.populateHoursOfOperation();
        apptTime.setItems(Time.hoursOfOpperation);

        //Populate apptLenght
        Time.populateDuration();
        duration.setItems(Time.apptDuration);

        // Populate Contacts
        try {
            DBResultSets.populateContact();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        contactBox.setItems(Contacts.allContacts());

        // Populate Customers
        try {
            DBResultSets.populateCustomers();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        customerBox.setItems(Customers.allCustomers);
    }

    /** Populates appointment table. */
    public void setAppointmentTable(ObservableList<Appointment> appointmentList) throws SQLException {
        appointmentTable.setItems(appointmentList);  // Tell Table where to get info
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

    /** Repopulates table after update. */
    public void refeshTable() throws SQLException {
        appointmentTable.getItems().clear();
        DBResultSets.populateAppointments();
    }

    /** Populates the appointments table with the next 7 days of appointments.*/
    public void weeksAppointments(ActionEvent actionEvent) throws IOException, SQLException {
        setAppointmentTable(Appointment.getWeeksAppointments());
    }

    /** Populates the appointments table with the next 30 days of appointments.*/
    public void monthsAppointments(ActionEvent actionEvent) throws IOException, SQLException {
        setAppointmentTable(Appointment.getMonthsAppointments());
    }

    /** Populates the appointments table with all of the appointments.*/
    public void allAppointments(ActionEvent actionEvent) throws IOException, SQLException {
        setAppointmentTable(Appointment.getAllAppointments());
    }

    //todo why wont this work with window.switchscreen?
    /** Loads the page: new appointments.*/
    public void newApptAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(homeController.class.getResource("/viewController/newAppointmentView.fxml"));
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("InteliDoc");
        stage.setScene(scene);
        stage.show();
    }

    /** Loads the page: reports.*/
    public void reportsAction(ActionEvent actionEvent) throws IOException {
        Window.switchScreen("/viewController/reportsView.fxml", actionEvent);
    }

    /** Loads the page: Patients.*/
    public void patientAction(ActionEvent actionEvent) throws IOException {
        Window.switchScreen("/viewController/patientView.fxml", actionEvent);
    }

    /** Deletes an appointment form the appointments table, list and database*/
    public void delete(ActionEvent actionEvent) throws IOException, SQLException {
        //Get info form the table
        Appointment selectedItem = appointmentTable.getSelectionModel().getSelectedItem();

        //Make info in to SQL statement
        String insertStatement = "DELETE from appointments WHERE Appointment_ID = '" + selectedItem.getApptId() + "'";
        System.out.println(insertStatement);

        //Do delete
        DBPreparedStatements.setPreparedStatement(DBDriver.getConnection(),insertStatement);
        PreparedStatement ps = DBPreparedStatements.getPreparedStatement();
        ps.execute();

        //Message
        Messages.apointmentDelete(selectedItem);

        //update table
        refeshTable();
    }

    /** Modifies an appointment in the appointments table, list and database*/
    public void modify (ActionEvent actionEvent) throws IOException, SQLException {
        LocalTime time = apptTime.getValue();
        LocalDate apptDate = date.getValue();
        Long getLength = ((Number) duration.getValue()).longValue() ;
        long length = getLength;

        int Appointment_ID = selected.getApptId();

        String Title = title.getText();
        String Description = description.getText();
        String Location = location.getText();
        String Type = type.getText();
        LocalDateTime Start = LocalDateTime.of(apptDate.getYear(),apptDate.getMonth(),apptDate.getDayOfMonth(),time.getHour(),time.getMinute());
        LocalDateTime End = Start.plusMinutes(Long.valueOf(length));
        LocalDate Create_Date = date.getValue();
        String Created_By = selected.getCreatedBy();
        Timestamp Last_Update = new Timestamp(System.currentTimeMillis());
        String Last_Updated_By = logInController.loggedInUser.getUser_Name();
        int Customer_ID = customerBox.getValue().getCustomer_ID();
        int User_ID = logInController.loggedInUser.getUser_ID();
        int Contact_ID = contactBox.getValue().getContact_ID();

        if (DBResultSets.checkTimeFrame(Start,End, Appointment_ID) == true){
            DBPreparedStatements.modifyAppointment(Title,Description,Location,Type,Start,End,Create_Date.toString(),Created_By,Last_Update,Last_Updated_By,Customer_ID,User_ID,Contact_ID,Appointment_ID);
            Messages.apointmentModified(Appointment_ID);
        }
        else
            Messages.timeFrameOccupied();

        DBResultSets.populateAppointments();
        appointmentTable.getSelectionModel().clearSelection();
        appointmentTable.refresh();
    }

    /** Gets the appointment information from the appointment table and populates it in the editable fields.*/
    public void getCustomerInfo(ActionEvent actionEvent) throws SQLException {
        //Set information
        selected = appointmentTable.getSelectionModel().getSelectedItem();

        apptID.setText(Integer.toString(selected.getApptId()));
        title.setText(selected.getTitle());
        description.setText(selected.getDescription());
        location.setText(selected.getLocation());
        type.setText(selected.getType());

        LocalDateTime st = selected.getStart();
        LocalDateTime et = selected.getEnd();

        date.setValue(st.toLocalDate());
        apptTime.setValue(LocalTime.of(st.getHour(),st.getMinute()));
        if ((st.plusMinutes(15).isEqual(et))){duration.setValue(15);System.out.println("15");}
        else if ((st.plusMinutes(30).isEqual(et))){duration.setValue(30);System.out.println("30");}
        else if ((st.plusMinutes(45).isEqual(et))){duration.setValue(45);System.out.println("45");}
        else if ((st.plusMinutes(60).isEqual(et))){duration.setValue(60);System.out.println("60");}
        else System.out.println("Duration out of range");

//      ContactBox
        Contacts contact = DBResultSets.findContact(selected.getContactId());
        selectContact(contact);

//      CustomerBox.
        Customers customer = DBResultSets.findCustomer(selected.getCustomerId());
        selectCustomer(customer);
    }

    /** Gets the contact value in the contactBox.*/
    public void updateContact (ActionEvent actionEvent) throws IOException, SQLException {
        Contacts contact = contactBox.getValue();
        selectContact(contact);
    }

    /** Sets the contact value int the contactBox.*/
    public void selectContact(Contacts contact){
        contactBox.getSelectionModel().select(contact);

        contactName.setText(contact.getContact_Name());
        contactEmail.setText(contact.getEmail());
    }

    /** Gets the contact value in the customerBox.*/
    public void updateCustomer (ActionEvent actionEvent) throws IOException, SQLException {
        Customers customer = customerBox.getValue();
        selectCustomer(customer);
    }

    /** Sets the contact value int the customerBox.*/
    public void selectCustomer(Customers customer){
        customerBox.getSelectionModel().select(customer);

        nameFieldCust.setText(customer.getCustomer_Name());
        addressField.setText(customer.getAddress());
        phoneField.setText(customer.getPhone());
        zipField.setText(customer.getPostal_Code());
    }
}
