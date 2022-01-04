package controller;

import model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

/** Controls modify parts screen.*/
public class AddPartController {
    /** Text that changes from machine ID to Company name.*/
    @FXML
    Label Add_Part_MID_Label;

    /** User input for the information for name.*/
    @FXML
    private TextField Add_Part_Name_Field;

    /** User input for the information for price.*/
    @FXML
    private TextField Add_Part_Price_Field;

    /** User input for the information for ID.*/
    @FXML
    private TextField Add_Part_Inv_Field;

    /** User input for the information for max.*/
    @FXML
    private TextField Add_Part_Max_Field;

    /** User input for the information for min.*/
    @FXML
    private TextField Add_Part_Min_Field;

    /** User input for the information for machine id and company name.*/
    @FXML
    private TextField Add_Part_MID_Field;

    /** User input for the information for inhouse.*/
    @FXML
    private RadioButton Add_Part_Radio_Inhouse;

    /** User input for the information for outsourced.*/
    @FXML
    private RadioButton Add_Part_Radio_Outsourced;

    /** Sets the label to Machine Id.
     * @param event action event */
    @FXML
    public void radioInhouse(ActionEvent event) {
        if (Add_Part_Radio_Inhouse.isSelected()) {
            Add_Part_MID_Label.setText("Machine ID");}
    }
    /** Sets the label to Company name.
     * @param event action event*/
    @FXML
    public void radioOutsourced(ActionEvent event) {
        if (Add_Part_Radio_Outsourced.isSelected()) {
            Add_Part_MID_Label.setText("Company Name");
        }
    }
    /** Goes to main screen.
     * @param actionEvent action event
     * @throws Exception
     */
    public void toMain(ActionEvent actionEvent) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/HomeForm.fxml"));
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Modify Product");
        stage.setScene(scene);
        stage.show();
    }
    /** RUNTIME ERROR (Fixed): Over zealous NumberFormatException catch occurred when you put in letters the companyName text field.
     * Makes new part.
     * @param event action event
     * @throws Exception
     */
    @FXML
    void save(ActionEvent event) throws Exception {
        try {
            //get Inputs
            int id = Inventory.getNextPartId();
            String name = Add_Part_Name_Field.getText();
            double price = Double.parseDouble(Add_Part_Price_Field.getText());
            int stock = Integer.parseInt(Add_Part_Inv_Field.getText());
            int min = Integer.parseInt(Add_Part_Min_Field.getText());
            int max = Integer.parseInt(Add_Part_Max_Field.getText());
            //validate stock
            if (Inventory.validateStock(stock,max,min,name) == true) {
                //add
                if (Add_Part_Radio_Outsourced.isSelected()) {
                    String companyName = Add_Part_MID_Field.getText();
                    Inventory.addPart(new Outsourced(id, name, price, stock, min, max, companyName));
                    Inventory.displayAlert(14);
                    Parent root = FXMLLoader.load(getClass().getResource("/view/HomeForm.fxml"));
                    Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
                    Scene scene = new Scene(root);
                    stage.setTitle("Home screen");
                    stage.setScene(scene);
                    stage.show();
                }
                if (Add_Part_Radio_Inhouse.isSelected()) {
                    int machineId = Integer.parseInt(Add_Part_MID_Field.getText());
                    Inventory.addPart(new InHouse(id, name, price, stock, min, max, machineId));
                    Inventory.displayAlert(14);
                    Parent root = FXMLLoader.load(getClass().getResource("/view/HomeForm.fxml"));
                    Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
                    Scene scene = new Scene(root);
                    stage.setTitle("Home Screen");
                    stage.setScene(scene);
                    stage.show();
                }
            }
        }
        catch (NumberFormatException e) {
           Inventory.displayAlert(6);
        }
    }
}

