package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/** Controls Add Product screen.*/
public class AddProductController implements Initializable{
    /** User input for the information for name.*/
    @FXML
    public TextField Add_Product_Text_Name;

    /** User input for the information for stock.*/
    @FXML
    public TextField Add_Product_Text_Stock;

    /** User input for the information for price.*/
    @FXML
    public TextField Add_Product_Text_Price;

    /** User input for the information for max.*/
    @FXML
    public TextField Add_Product_Text_Max;

    /** User input for the information for min*/
    @FXML
    public TextField Add_Product_Text_Min;

    /** User input for the information for search*/
    @FXML
    public TextField part_text_search;

    /** Table for parts.*/
    public TableView<Part> associatedPartsTable;

    /** Table for Associated products.*/
    public TableView<Part> Parts_Table;

    /** Parts table column for id.*/
    public TableColumn Add_Product_Pro_ID_Col;

    /** Parts table column for name.*/
    public TableColumn Add_Product_Pro_Name_Col;

    /** Parts table column for stock.*/
    public TableColumn Add_Product_Inv_Col;

    /** Parts table column for price.*/
    public TableColumn Add_Product_Price_Col;

    /** Associated parts table column for id.*/
    public TableColumn Add_Product_Pro_ID_Col_Bottom;

    /** Associated parts table column for name.*/
    public TableColumn Add_Product_Pro_Name_Col_Bottom;

    /** Associated parts table column for stock.*/
    public TableColumn Add_Product_Inv_Col_Bottom;

    /** Associated parts table column for price.*/
    public TableColumn Add_Product_Price_Col_Bottom;

    /** Goes to main screen.
     * @param actionEvent an Action event
     * @throws Exception
     */
    @FXML
    public void toMain(ActionEvent actionEvent) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/HomeForm.fxml"));
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Main Screen");
        stage.setScene(scene);
        stage.show();
    }
    /** Adds associated part to product.
     * @param actionEvent an Action event*/
    public void addAssociatedPart(ActionEvent actionEvent) {
        Part selectedPart = Parts_Table.getSelectionModel().getSelectedItem();

        if (selectedPart == null) {
            Inventory.displayAlert(4);
        }
        else {
            associatedParts.add(selectedPart);
            associatedPartsTable.setItems(associatedParts);
        }
    }
    /** removes an associated part.
     * @param actionEvent an Action event*/
    public void removeAssociatedPart(ActionEvent actionEvent) {
        Part selectedPart = associatedPartsTable.getSelectionModel().getSelectedItem();

        if (associatedPartsTable.getItems().size() == 0)
            Inventory.displayAlert(21);

        else if (selectedPart == null) {
            Inventory.displayAlert(3);
        }
        else {
            if (Inventory.displayAlert(20).get() == ButtonType.OK)
            associatedParts.remove(selectedPart);
        }
    }

    /** A list of parts associated with the product.*/
    private final ObservableList<Part> associatedParts = FXCollections.observableArrayList();

    /** Creates new product.
     * @param actionEvent action event*/
    public void save(ActionEvent actionEvent) {
        try {
            int id = Inventory.getNextPartId();
            String name = Add_Product_Text_Name.getText();
            double price = Double.parseDouble(Add_Product_Text_Price.getText());
            int stock = Integer.parseInt(Add_Product_Text_Stock.getText());
            int min = Integer.parseInt(Add_Product_Text_Min.getText());
            int max = Integer.parseInt(Add_Product_Text_Max.getText());

            //validate stock
            if (Inventory.validateStock(stock,max,min,name) == true) {
            //Set new variables
            Product upProduct = new Product(id, name, price, stock, min, max);
            Inventory.addProduct(upProduct);
            for (Part part : associatedParts) {
                upProduct.addAssociatedPart(part);
            }
            Inventory.displayAlert(16);

            Parent root = FXMLLoader.load(getClass().getResource("/view/HomeForm.fxml"));
            Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setTitle("Main Screen");
            stage.setScene(scene);
            stage.show();

            }
        }

        catch (NumberFormatException | IOException e) {
          Inventory.displayAlert(6);
        }
    }
    /** Searches for part
     * @param actionEvent action event*/
    public void search(ActionEvent actionEvent) {
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

    /** Fills tables with information.*/
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Top Table
        Parts_Table.setItems(Inventory.getAllParts());  // Tell Table where to get info
        Add_Product_Pro_ID_Col.setCellValueFactory(new PropertyValueFactory<>("id")); //Get Col info
        Add_Product_Pro_Name_Col.setCellValueFactory(new PropertyValueFactory<>("name")); //Get Col info
        Add_Product_Inv_Col.setCellValueFactory(new PropertyValueFactory<>("stock")); //Get Col info
        Add_Product_Price_Col.setCellValueFactory(new PropertyValueFactory<>("price")); //Get Col info

        //Bottom Table
        associatedPartsTable.setItems(associatedParts);  // Tell Table where to get info
        Add_Product_Pro_ID_Col_Bottom.setCellValueFactory(new PropertyValueFactory<>("id")); //Get Col info
        Add_Product_Pro_Name_Col_Bottom.setCellValueFactory(new PropertyValueFactory<>("name")); //Get Col info
        Add_Product_Inv_Col_Bottom.setCellValueFactory(new PropertyValueFactory<>("stock")); //Get Col info
        Add_Product_Price_Col_Bottom.setCellValueFactory(new PropertyValueFactory<>("price")); //Get Col info
    }
}

