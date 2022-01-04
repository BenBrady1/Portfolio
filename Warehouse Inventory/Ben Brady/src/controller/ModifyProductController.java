package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/** Controls modify product screen.*/
public class ModifyProductController implements Initializable {
    /** User input for the information for id.*/
    @FXML
    public TextField Modify_Product_Text_Id;

    /** User input for the information for name.*/
    @FXML
    public TextField Modify_Product_Text_Name;

    /** User input for the information for stock.*/
    @FXML
    public TextField Modify_Product_Text_Stock;

    /** User input for the information for price.*/
    @FXML
    public TextField Modify_Product_Text_Price;

    /** User input for the information for max.*/
    @FXML
    public TextField Modify_Product_Text_Max;

    /** User input for the information for min.*/
    @FXML
    public TextField Modify_Product_Text_Min;

    /** Table for parts.*/
    @FXML
    public TableView <Part> Parts_Table;

    /** Table for associated parts.*/
    @FXML
    public TableView <Part> AssociatedPartsTable;

    /** User input for the information for cancel.*/
    @FXML
    public Button Cancel;

    /** Parts table column for id.*/
    @FXML
    public TableColumn <Part, Integer>Modify_Product_Pro_ID_Col;

    /** Parts table column for name.*/
    @FXML
    public TableColumn <Part, String>Modify_Product_Pro_Name_Col;

    /** Parts table column for stock.*/
    @FXML
    public TableColumn <Part, Integer>Modify_Product_Pro_Inv_Col;

    /** Parts table column for price.*/
    @FXML
    public TableColumn <Part, Double>Modify_Product_Pro_Price_Col;

    /** Parts table column for id.*/
    @FXML
    public TableColumn <Part, Integer>Modify_Product_Pro_ID_Col_Bottom;

    /** Parts table column for name.*/
    @FXML
    public TableColumn <Part, String>Modify_Product_Pro_Name_Col_Bottom;

    /** Parts table column for stock.*/
    @FXML
    public TableColumn <Part, Integer>Modify_Product_Pro_Inv_Col_Bottom;

    /** Parts table column for price.*/
    @FXML
    public TableColumn <Part, Double>Modify_Product_Pro_IPrice_Col_Bottom;

    /** User input for the information for search.*/
    @FXML
    public TextField part_text_search;

    /** Holds the selected product.
     */
    @FXML
    private static Product selectedProduct;
    /** List of parts associated with the product.
     * @param actionEvent Starts the method.*/
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();


    /** sets the selected product
     * @param selectedProduct Product the user selected.
     */
    public static void setSelectedProduct(Product selectedProduct) {
        ModifyProductController.selectedProduct = selectedProduct;
        System.out.println(ModifyProductController.selectedProduct.toString());
        System.out.println(selectedProduct);
    }

    /** Sends to main screen.
     * @param actionEvent Starts the method.*/
    public void toMain(ActionEvent actionEvent) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/HomeForm.fxml"));
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Modify Product");
        stage.setScene(scene);
        stage.show();
    }

    /** Starts search.
     * @param actionEvent Starts the method.*/
    public void search(ActionEvent actionEvent)  {
        if (part_text_search.getText().isBlank())
        {
            Parts_Table.setItems(Inventory.getAllParts());
            return;
        }
        try {
            Part part = Inventory.lookupPart(Integer.parseInt(part_text_search.getText()));
            if (part == null){
                System.out.println("Part was null");
                Parts_Table.setItems(Inventory.lookupPart(part_text_search.getText()));
                part_text_search.setText("");
            }
            else {
                Parts_Table.getSelectionModel().select(part);
            }

        } catch (NumberFormatException e) {
            System.out.println("Part was not number");
            Parts_Table.setItems(Inventory.lookupPart(part_text_search.getText()));
            part_text_search.setText("");
        }
    }

    /** Saves changes to Product.
     * @param actionEvent Starts the method.*/
    public void save(ActionEvent actionEvent) throws IOException {
        try {
            //Get new variables
            int id = Integer.parseInt(Modify_Product_Text_Id.getText());
            String name = Modify_Product_Text_Name.getText();
            double price = Double.parseDouble(Modify_Product_Text_Price.getText());
            int stock = Integer.parseInt(Modify_Product_Text_Stock.getText());
            int max = Integer.parseInt(Modify_Product_Text_Max.getText());
            int min = Integer.parseInt(Modify_Product_Text_Min.getText());
            int index = Inventory.getAllProducts().indexOf(selectedProduct);
            System.out.println("index equals " + index);

            //validate stock
            if (Inventory.validateStock(stock,max,min,name) == true) {
            //Set new variables
            Product upProduct = new Product(id, name, price, stock, min, max);
            Inventory.updateProduct(index, upProduct);

            for (Part part : associatedParts) {
                upProduct.addAssociatedPart(part);
            }
            //Go to main
            Parent root = FXMLLoader.load(getClass().getResource("/view/HomeForm.fxml"));
            Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
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

    /** Removes associated parts.
     * @param actionEvent Starts the method.*/
    public void removeAssociatedPart(ActionEvent actionEvent) {
        Part selectedPart = AssociatedPartsTable.getSelectionModel().getSelectedItem();

        if (AssociatedPartsTable.getItems().size() == 0)
            Inventory.displayAlert(21);
        else if  (selectedPart == null) {
        Inventory.displayAlert(3);
        }
        else {
            if (Inventory.displayAlert(20).get() == ButtonType.OK)
            associatedParts.remove(selectedPart);
        }
    }
    /** Adds associated parts.
     * @param actionEvent Starts the method.*/
    public void addAssociatedPart(ActionEvent actionEvent) {
        Part selectedPart = Parts_Table.getSelectionModel().getSelectedItem();

        if (selectedPart == null) {
            Inventory.displayAlert(3);
        }
        else {
            associatedParts.add(selectedPart);
            AssociatedPartsTable.setItems(associatedParts);
        }
    }

    /** Puts information in tables and text fields.*/
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Set inputs based on selectedProduct
        Modify_Product_Text_Id.setText(String.valueOf(selectedProduct.getId()));
        Modify_Product_Text_Name.setText(selectedProduct.getName());
        Modify_Product_Text_Stock.setText(String.valueOf(selectedProduct.getStock()));
        Modify_Product_Text_Price.setText(String.valueOf(selectedProduct.getPrice()));
        Modify_Product_Text_Max.setText(String.valueOf(selectedProduct.getMax()));
        Modify_Product_Text_Min.setText(String.valueOf(selectedProduct.getMin()));

        //Top Table
        Parts_Table.setItems(Inventory.getAllParts());  // Tell Table where to get info
        Modify_Product_Pro_ID_Col.setCellValueFactory(new PropertyValueFactory<>("id")); //Get Col info
        Modify_Product_Pro_Name_Col.setCellValueFactory(new PropertyValueFactory<>("name")); //Get Col info
        Modify_Product_Pro_Inv_Col.setCellValueFactory(new PropertyValueFactory<>("stock")); //Get Col info
        Modify_Product_Pro_Price_Col.setCellValueFactory(new PropertyValueFactory<>("price")); //Get Col info

        //Bottom Table
        associatedParts = selectedProduct.getAssociatedParts();
        AssociatedPartsTable.setItems(associatedParts);
        Modify_Product_Pro_ID_Col_Bottom.setCellValueFactory(new PropertyValueFactory<>("id")); //Get Col info
        Modify_Product_Pro_Name_Col_Bottom.setCellValueFactory(new PropertyValueFactory<>("name")); //Get Col info
        Modify_Product_Pro_Inv_Col_Bottom.setCellValueFactory(new PropertyValueFactory<>("stock")); //Get Col info
        Modify_Product_Pro_IPrice_Col_Bottom.setCellValueFactory(new PropertyValueFactory<>("price")); //Get Col info
    }
}
