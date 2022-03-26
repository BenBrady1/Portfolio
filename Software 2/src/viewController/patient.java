package viewController;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import main.jdbc.DBPreparedStatements;
import main.jdbc.DBResultSets;
import model.*;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ResourceBundle;
import java.time.LocalDateTime;


public class patient implements Initializable {
    public ComboBox<Countries> countryCombo;
    public ComboBox<FirstLevelDivision> firstLevelDivisionComboBox;
    public Label nameLabel;
    public Button back_button;
    public TextField nameField;
    public TextField addressField;
    public TextField zipField;
    public Button createBox;
    public Label logo;
    public Label stateLabel;
    public Label countryLabel;
    public Label addressLabel;
    public Label zipLabel;
    public TextField phoneField;
    public Label phoneLabel;
    public Label userIDLabel;
    public TextField phoneFieldM;
    public TextField zipFieldM;
    public TextField addressFieldM;
    public ComboBox<FirstLevelDivision> firstLevelDivisionComboBoxM;
    public ComboBox<Countries> countryComboM;
    public TextField nameFieldM;
    public Button createBoxM;
    public Label phoneLabel1;
    public ComboBox <Customers >  customerIDComboBox;
    public TableView<Customers> customerTable;
    public TableColumn idCol;
    public TableColumn nameCol;
    public TableColumn addressCol;
    public TableColumn zipCol;
    public TableColumn phoneCol;
    public TableColumn createDateCol;
    public TableColumn createByCol;
    public TableColumn lastUpDateCol;
    public TableColumn lastUpdateByCol;
    public TableColumn divisionCol;
    public TableColumn postalCodeCol;
    public Button selectorBox;
    public Button deleteBox;

    //Variables
    public static int countryID = -1;
    public static int divisionID = -1;
    public static int countryIDM = -1;
    public static int divisionIDM = -1;
    public static int customerID = -1;


    /** Brings users to homepage */
    public void toHome(ActionEvent actionEvent) throws IOException {
        Window.goHome(actionEvent);
    }

    /** Populates customerTable.*/
    public void setCustomerTable(ObservableList<Customers> customersList) throws SQLException {
        customerTable.setItems(customersList);  // Tell Table where to get info
        idCol.setCellValueFactory(new PropertyValueFactory<>("Customer_ID")); //Get Col info
        nameCol.setCellValueFactory(new PropertyValueFactory<>("Customer_Name")); //Get Col info
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address")); //Get Col info
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phone")); //Get Col info
        createDateCol.setCellValueFactory(new PropertyValueFactory<>("Create_Date"));  // Tell Table where to get info
        createByCol.setCellValueFactory(new PropertyValueFactory<>("Created_By")); //Get Col info
        lastUpDateCol.setCellValueFactory(new PropertyValueFactory<>("Last_Update")); //Get Col info
        lastUpdateByCol.setCellValueFactory(new PropertyValueFactory<>("Last_Updated_By")); //Get Col info
        divisionCol.setCellValueFactory(new PropertyValueFactory<>("Division_ID")); //Get Col info
        postalCodeCol.setCellValueFactory(new PropertyValueFactory<>("Postal_Code"));
    }

    /** Deletes Customer from DB/list/table.*/
    public void delete(ActionEvent actionEvent) throws IOException, SQLException {
        //Get info form the table
        Customers selectedItem = customerTable.getSelectionModel().getSelectedItem();

        //Check for apppointments (can not delete if they appointments)
        int numAppts = DBResultSets.checkForAppointmetnsBeforeDelete(selectedItem.getCustomer_ID());

        //If no appointments then delete
        if (numAppts == 0){
            if (Messages.patientDelete() == true){
                DBPreparedStatements.deleteCustomer(selectedItem.getCustomer_ID());
            }
        }
        else{
            Messages.patientNOTdelete(numAppts);
        }
        //update table
        customerTable.getItems().clear();
        DBResultSets.populateCustomers();
    }

    /** Creates Customer in DB/list/table.*/
    public void create (ActionEvent actionEvent) throws IOException, SQLException {
    //Get information
    String Customer_Name = nameField.getText();
    String Address = addressField.getText();
    String Postal_Code = zipField.getText();
    String Phone = phoneField.getText();
    String Create_Date = LocalDateTime.now().toString();
    String Created_By = logInController.loggedInUser.getUser_Name();
    Timestamp Last_Update = new Timestamp(System.currentTimeMillis());
    String Last_Updated_By = logInController.loggedInUser.getUser_Name();
    int Division_ID = firstLevelDivisionComboBox.getValue().getDivision_ID();

    Messages.patientCreated(Customer_Name,Address,Postal_Code,Phone,Create_Date,Created_By,Last_Update,Last_Updated_By,Division_ID);
    DBResultSets.populateCustomers();
    }

    //for new comboboxes
//-----------------------------------------------------------------------------------------
    /** Sets FLD.*/
    public void setFirststLevelDivision() throws SQLException {
        FirstLevelDivision selected = firstLevelDivisionComboBox.getValue();
        divisionID= selected.getDivision_ID();
    }

    /** Gets FLD.*/
    public void getFirststLevelDivision(ActionEvent actionEvent) throws SQLException {
        //Get selected Country ID
        Countries selected = countryCombo.getValue();
        countryID= selected.getCountyId();
        System.out.println(countryID);
        //show get divions in divinsions combobox
        DBResultSets.populateDivisons();
        firstLevelDivisionComboBox.setItems(FirstLevelDivision.getFirstLevelDivisions());
    }

    //for modify
//-----------------------------------------------------------------------------------------
    /** Gets customer information from table.*/
    public void getCustomerInfo(ActionEvent actionEvent) throws SQLException {
        //Set information
        Customers selected = customerTable.getSelectionModel().getSelectedItem();

        customerID = selected.getCustomer_ID();
        nameFieldM.setText(selected.getCustomer_Name());
        addressFieldM.setText(selected.getAddress());
        zipFieldM.setText(selected.getPostal_Code());
        phoneFieldM.setText(selected.getPhone());

        //Set Country
        //US
        if (selected.getDivision_ID() <= 54){
            countryComboM.getSelectionModel().select(0);
            countryIDM= 1;
        }
        //UK
        else if (selected.getDivision_ID() >= 101) {
            countryComboM.getSelectionModel().select(1);
            countryIDM = 2;
        }
        //Canada
        else {
            countryComboM.getSelectionModel().select(2);
            countryIDM = 3;
        }

        //Set Division
        firstLevelDivisionComboBoxM.getSelectionModel().select(DBResultSets.findDivisons(selected.getDivision_ID()));
    }

    /** Modifies patient.*/
    public void modify (ActionEvent actionEvent) throws IOException, SQLException {
        //Get information
        int Customer_ID = customerID;
        String Customer_Name = nameFieldM.getText();
        String Address = addressFieldM.getText();
        String Postal_Code = zipFieldM.getText();
        String Phone = phoneFieldM.getText();
        String Last_Update = new Timestamp(System.currentTimeMillis()).toString();
        String Last_Updated_By = logInController.loggedInUser.getUser_Name();
        int Division_ID = divisionIDM;

        Messages.patientModified(Customer_Name,Address,Postal_Code,Phone,Last_Update,Last_Updated_By,Division_ID,Customer_ID);

        customerTable.setItems(Customers.getAllCustomers());
        DBResultSets.populateCustomers();
    }

    /** Gets FLD from table.*/
    public void customersDivision() throws SQLException {
        Countries selected = countryComboM.getValue();
        updateDivisionM(selected.getCountyId());
    }

    /** Sets FLD from table.*/
    public void updateDivisionM(int selectedCountry) throws SQLException {
        firstLevelDivisionComboBoxM.getSelectionModel().select(DBResultSets.findDivisons(selectedCountry));
    }

    /** Gets FLD.*/
    public void setFirststLevelDivisionM(ActionEvent actionEvent) throws SQLException {
        FirstLevelDivision selected = firstLevelDivisionComboBoxM.getValue();
        divisionIDM= selected.getDivision_ID();
        System.out.println(divisionIDM);
    }

    /** Sets FLD.*/
    public void getFirststLevelDivisionM(ActionEvent actionEvent) throws SQLException {
        //Get selected Country ID
        Countries selected = countryComboM.getValue();
        countryIDM= selected.getCountyId();
        System.out.println(countryIDM);
        //show get divions in divinsions combobox
        DBResultSets.populateDivisonsM();
        firstLevelDivisionComboBoxM.setItems(FirstLevelDivision.getFirstLevelDivisions());
    }
//-----------------------------------------------------------------------------------------

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            DBResultSets.populateCountries();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        countryCombo.setItems(Countries.getAllCountries());
        countryComboM.setItems(Countries.getAllCountries());

        try {
            DBResultSets.populateCustomers();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        try {
            setCustomerTable(Customers.getAllCustomers());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        userIDLabel.setText(logInController.loggedInUser.getUser_Name());
    }
}
