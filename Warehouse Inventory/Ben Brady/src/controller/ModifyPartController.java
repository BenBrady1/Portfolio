package controller;

import javafx.fxml.FXML;
import model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/** Controls modify parts screen.*/
public class ModifyPartController implements Initializable{
    /** User input for the information for Inhouse.*/
    @FXML
    public RadioButton Modify_Part_Radio_Inhouse;

    /** User input for the information for Outsourced.*/
    @FXML
    public RadioButton Modify_Part_Radio_Outsourced;

    /** Text label for Machine Id and Company name.*/
    @FXML
    public Label Modify_Part_Label_MIdCName;

    /** User input for the information for name.*/
    @FXML
    public TextField Modify_Part_Text_Name;

    /** User input for the information for inventory.*/
    @FXML
    public TextField Modify_Part_Text_Inv;

    /** User input for the information for price.*/
    @FXML
    public TextField Modify_Part_Text_Price;

    /** User input for the information for max.*/
    @FXML
    public TextField Modify_Part_Text_Max;

    /** User input for the information for min.*/
    @FXML
    public TextField Modify_Part_Text_Min;

    /** User input for the information for Machine Id and Company name.*/
    @FXML
    public TextField Modify_Part_Text_MidComp;

    /** User input for the information for save.*/
    @FXML
    public Button Modify_Part_Button_Save;

    /** radio group of Inhouse and Company name.*/
    @FXML
    public ToggleGroup modify_part_TG;

    /** User input for the information for id.*/
    @FXML
    public TextField Modify_Part_Text_Id;

    /** variable for passing part from screen to screen
     * @param selectedPart part that the user selected.
     */
    private static Part selectedPart;

    /** sets selected part
     * @param selectedPart Part that user selected.
     */
    public static void setSelectedPart(Part selectedPart) {
        ModifyPartController.selectedPart = selectedPart;
    }

    /** Goes to main screen.
     * @param actionEvent Starts the method.*/
    public void toMain(ActionEvent actionEvent) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/HomeForm.fxml"));
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Modify Product");
        stage.setScene(scene);
        stage.show();
    }

    /** Sets label to Company name.
     * @param actionEvent Starts the method.*/
    public void radioOutsourced(ActionEvent actionEvent) {
        if (Modify_Part_Radio_Outsourced.isSelected()) {
            Modify_Part_Label_MIdCName.setText("Company Name");
        }
    }

    /** Sets label to MachineID.
     * @param actionEvent Starts the method. */
    public void radioInhouse(ActionEvent actionEvent) {
        if (Modify_Part_Radio_Inhouse.isSelected()) {
            Modify_Part_Label_MIdCName.setText("Machine ID");}
    }
    /** Saves part.
     * @param actionEvent Starts the method.
     */
    public void save(ActionEvent actionEvent) throws IOException {
        try {
            //Get new variables
            int id = Integer.parseInt(Modify_Part_Text_Id.getText());
            String name = Modify_Part_Text_Name.getText();
            double price = Double.parseDouble(Modify_Part_Text_Price.getText());
            int stock = Integer.parseInt(Modify_Part_Text_Inv.getText());
            int max = Integer.parseInt(Modify_Part_Text_Max.getText());
            int min = Integer.parseInt(Modify_Part_Text_Min.getText());
            int index = Inventory.getAllParts().indexOf(selectedPart);

            //validate stock
            if (Inventory.validateStock(stock,max,min,name) == true) {
                //Set new variables
                if (Modify_Part_Radio_Outsourced.isSelected()) {
                    String companyName = Modify_Part_Text_MidComp.getText();
                    Outsourced upPart = new Outsourced(id, name, price, stock, min, max, companyName);
                    Inventory.updatePart(index, upPart);
                } else {
                    int machineId = Integer.parseInt(Modify_Part_Text_MidComp.getText());
                    InHouse upPart = new InHouse(id, name, price, stock, min, max, machineId);
                    Inventory.updatePart(index, upPart);
                }
                //Go to main
                Parent root = FXMLLoader.load(getClass().getResource("/view/HomeForm.fxml"));
                Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setTitle("Modify Product");
                stage.setScene(scene);
                stage.show();
            }
        }
        catch ( NumberFormatException e){
            Inventory.displayAlert(6);
        }
    }

    /**Populates table views.*/
    @Override
    public void initialize (URL location, ResourceBundle resources) {
        //Set inputs based on selectedPart
        Modify_Part_Text_Id.setText(String.valueOf(selectedPart.getId()));
        Modify_Part_Text_Name.setText(selectedPart.getName());
        Modify_Part_Text_Inv.setText(String.valueOf(selectedPart.getStock()));
        Modify_Part_Text_Price.setText(String.valueOf(selectedPart.getPrice()));
        Modify_Part_Text_Max.setText(String.valueOf(selectedPart.getMax()));
        Modify_Part_Text_Min.setText(String.valueOf(selectedPart.getMin()));
        if (selectedPart instanceof InHouse) {
            Modify_Part_Radio_Inhouse.setSelected(true);
            Modify_Part_Label_MIdCName.setText("Machine ID");
            Modify_Part_Text_MidComp.setText(String.valueOf(((InHouse) selectedPart).getMachineId()));
        }
        if (selectedPart instanceof Outsourced){
            Modify_Part_Radio_Outsourced.setSelected(true);
            Modify_Part_Label_MIdCName.setText("Company Name");
            Modify_Part_Text_MidComp.setText(((Outsourced) selectedPart).getCompanyName());
        }
    }
}

