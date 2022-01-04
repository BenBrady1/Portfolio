package controller;

import model.*;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/** Controls main screen.*/
public class HomeController implements Initializable {
    /** User input for the information for part search.*/
    @FXML
    public TextField part_text_search;

    /** User input for the information for product search.*/
    @FXML
    public TextField product_text_search;

    /** User input for the information for starting parts search.*/
    @FXML
    public Button parts_button_search;

    /** User input for the information for starting delete.*/
    @FXML
    public Button part_button_delete;

    /** User input for the information for starting product search.*/
    @FXML
    public Button products_button_search;

    /** User input for the information for starting delete.*/
    @FXML
    public Button product_button_delete;

    /** Table for Parts*/
    @FXML
    private TableView<Part> Parts_Table;

    /** Products table column for id.*/
    @FXML
    private TableColumn<Part, Integer> part_id_col;

    /** Parts table column for name.*/
    @FXML
    private TableColumn<Part, String> part_name_col;

    /** Parts table column for stock.*/
    @FXML
    private TableColumn<Part, Integer> part_inv_col;

    /** Parts table column for cost.*/
    @FXML
    private TableColumn<Part, Double> part_cost_col;

    /** Table for products.*/
    @FXML
    private TableView<Product> Product_Table;

    /** Products table column for id.*/
    @FXML
    private TableColumn<Product, Integer> product_id_col;

    /** Products table column for name.*/
    @FXML
    private TableColumn<Product, String> product_name_col;

    /** Products table column for stock.*/
    @FXML
    private TableColumn<Product, Integer> product_inv_col;

    /** Products table column for cost.*/
    @FXML
    private TableColumn<Product, Double> product_cost_col;

    /** Goes to the add part screen.
     * @param actionEvent Starts the method.
     * @throws Exception*/
    public void toAddPart(ActionEvent actionEvent) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/AddPart.fxml"));
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Add Part");
        stage.setScene(scene);
        stage.show();
    }

    /** Goes to the modify part screen.
     * @param actionEvent Starts the method.
     * @throws Exception*/
    public void toModifyPart(ActionEvent actionEvent) throws Exception {

        Part selectedPart = Parts_Table.getSelectionModel().getSelectedItem();
        if (selectedPart == null) {
            Inventory.displayAlert(4);
            return;
        }
        //Pass part to modify part screen
        ModifyPartController.setSelectedPart(selectedPart);
        //load screen
        Parent root = FXMLLoader.load(getClass().getResource("/view/ModifyPartForm.fxml"));
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Modify Part");
        stage.setScene(scene);
        stage.show();
    }

    /** Goes to the add product screen.
     * @param actionEvent Starts the method.*/
    public void toAddProduct(ActionEvent actionEvent) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/AddProductForm.fxml"));
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Add Product");
        stage.setScene(scene);
        stage.show();
    }

    /** Goes to the modify product screen
     * @param actionEvent Starts the method.*/
    public void toModifyProduct(ActionEvent actionEvent) throws Exception {
        Product selectedProduct = Product_Table.getSelectionModel().getSelectedItem();
        if (selectedProduct == null) {
            Inventory.displayAlert(4);
            return;
        }
        ModifyProductController.setSelectedProduct(selectedProduct);
        //load screen
        Parent root = FXMLLoader.load(getClass().getResource("/view/Modify_Product_Form.fxml"));
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Modify Product");
        stage.setScene(scene);
        stage.show();
    }

    /** Goes to the exit screen
     * @param actionEvent Starts the method.*/
    public void toExit(ActionEvent actionEvent) {
        System.exit(0);
    }

    /** deletes product in table
     * @param event action event.*/
    public void productDelete(ActionEvent event) {

        Product selectedProduct = Product_Table.getSelectionModel().getSelectedItem();
        //Empty table delete
        if (Product_Table.getItems().size() == 0)
            Inventory.displayAlert(21);
        //No item selected
        else if (selectedProduct == null) {
            Inventory.displayAlert(4);
        }
        //delete product
        else if (!(selectedProduct.getAssociatedParts().isEmpty())) {
            Inventory.displayAlert(15);
        } else {
                //do not delete if associated to parts
                if (Inventory.displayAlert(19).get() == ButtonType.OK) {
                ObservableList<Part> assocParts = selectedProduct.getAssociatedParts();
                    if (assocParts.size() >= 1) {
                        Inventory.displayAlert(5);
                    } else
                            Inventory.deleteProduct(selectedProduct);

                }
        }
    }


    /**
     * deletes part in table.
     * @param actionEvent Starts the method.*/
     public void partDelete(ActionEvent actionEvent) {
        Part selectedPart = Parts_Table.getSelectionModel().getSelectedItem();
         //Empty table delete
         if (Parts_Table.getItems().size() == 0)
             Inventory.displayAlert(21);
         else if (selectedPart == null) {
            Inventory.displayAlert(3);
        }
        else {
            if (Inventory.displayAlert(19).get() == ButtonType.OK) {
                Inventory.deletePart(selectedPart);
            }
        }
    }

    /** RUNTIME ERROR (Fixed): Product search will only work if you run a part search first.
     * search product table.
     * @param actionEvent Starts the method.
      */
    public void searchProducts(ActionEvent actionEvent) {
        if (product_text_search.getText().isBlank())
        {
            Product_Table.setItems(Inventory.getAllProducts());
            return;
        }
        try {
            Product product = Inventory.lookupProdcut(Integer.parseInt(product_text_search.getText()));
            if (product == null){
                System.out.println("Product is null");
                Product_Table.setItems(Inventory.lookupProduct(product_text_search.getText()));
                product_text_search.setText("");
            }
            else {
                System.out.println("Product is not null");
                Product_Table.getSelectionModel().select(product);
            }

        } catch (NumberFormatException e) {
            System.out.println("Product not a number");
            Product_Table.setItems(Inventory.lookupProduct(product_text_search.getText()));
            product_text_search.setText("");
        }
    }

    /** search part table.
     * @param actionEvent Starts the method.*/
    public void searchParts(ActionEvent actionEvent) {
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

    /** Puts information in the tables.*/
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Parts_Table.setItems(Inventory.getAllParts());  // Tell Table where to get info
        part_id_col.setCellValueFactory(new PropertyValueFactory<>("id")); //Get Col info
        part_name_col.setCellValueFactory(new PropertyValueFactory<>("name")); //Get Col info
        part_inv_col.setCellValueFactory(new PropertyValueFactory<>("stock")); //Get Col info
        part_cost_col.setCellValueFactory(new PropertyValueFactory<>("price")); //Get Col info

        Product_Table.setItems(Inventory.getAllProducts());  // Tell Table where to get info
        product_id_col.setCellValueFactory(new PropertyValueFactory<>("id")); //Get Col info
        product_name_col.setCellValueFactory(new PropertyValueFactory<>("name")); //Get Col info
        product_inv_col.setCellValueFactory(new PropertyValueFactory<>("stock")); //Get Col info
        product_cost_col.setCellValueFactory(new PropertyValueFactory<>("price")); //Get Col info
    }
}