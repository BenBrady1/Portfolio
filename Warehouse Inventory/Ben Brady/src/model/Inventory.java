package model;
/**
 * @author Ben Brady
 */

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

/** Functions for Products and Parts.*/
public class Inventory {
    /** Gives unique ids for parts */
    private static int nextPartId;

    /** gets unique ID for parts.
     * @return auto generated part id. */
    public static int getNextPartId(){
        nextPartId++;
        return nextPartId;
    }

    /** gives unique ids for products*/
    private static int nextProductId;

    /** Returns a unique ID for products.
     * @return auto generated product id. */
    public static int getNextProductId(){
        nextProductId++;
        return nextProductId;
    }

    /** Contains a list of objects type Part in the system.
     * @param allParts list of all parts */
    private static final ObservableList<Part> allParts = FXCollections.observableArrayList();

    /** list of all products
     * @param allProducts a list of all products */
    private static final ObservableList<Product> allProducts = FXCollections.observableArrayList();

    /** list of parts that were searched for
     * @param filteredAllProducts the list of parts that was searched for*/
    private static final ObservableList<Product> filteredAllProducts = FXCollections.observableArrayList();

    /** list of parts that were searched for
     * @param filteredAllParts the list of parts that was searched for.*/
    private static final ObservableList<Part> filteredAllParts = FXCollections.observableArrayList();

    /** gets list of filtered parts
     * @return filteredAllParts a list of searched parts*/
    public static ObservableList<Part> getFilteredAllParts() {
        return filteredAllParts;}

    /** gets a list of filered products
     * @return filteredAllProducts  a list of searched products*/
     public static ObservableList<Product> getFilteredAllProducts()
     {
        return filteredAllProducts;
     }

     /** adds part to allParts
      * @param part  the things products are made of.*/
    public static void addPart(Part part) {
        allParts.add(part);
    }

    /** adds product to allProducts
     * @param product a thing made of parts.*/
    public static void addProduct(Product product) {
        allProducts.add(product);
    }

    /** looks up product
     * @param id the id of the product you are looking
     * @return product the product it was looking for*/
    public static Product lookupProdcut(int id)
    {
        for (Product product : Inventory.getAllProducts())
        {
            if (product.getId() == id)
                return product;
        }
        return null;
    }

    /** function to look up parts
     * @param id an int that is passed in.
     * @return part the part that it was searching for  */
    public static Part lookupPart(int id)
    {
        for (Part part : Inventory.getAllParts())
        {
            if (part.getId() == id)
                return part;
        }
        return null;
    }

    /** list to look up parts
     *  @param name name is the name of the part
     *  @return  getFilteredAllParts a list of all parts that meet the look up criteria.*/
    public static ObservableList<Part> lookupPart (String name)
    {
        //if empty
        if (!(Inventory.getFilteredAllParts().isEmpty()))
        {
            Inventory.getFilteredAllParts().clear();
        }

        //look up by name
        for(Part part : Inventory.getAllParts())
        {
            if(part.getName().contains(name))
            {
                Inventory.getFilteredAllParts().add(part);
            }
        }
        //Part is not found
        if (Inventory.getFilteredAllParts().isEmpty())
        {
            Inventory.displayAlert(10);
            return Inventory.getAllParts();
        }
        else
        {
            return getFilteredAllParts();
        }
    }

    /**
     * finds product.
     * @param name the name of the item you are looking for.
     * @return getFilteredAllProducts() a list of products that has been filtered by name or ID.
     * @return Inventory.getAllProducts() The full list of products.
     */
    public static ObservableList<Product> lookupProduct (String name)
    {
        //if empty
        if (!(Inventory.getFilteredAllProducts().isEmpty()))
        {
            Inventory.getFilteredAllProducts().clear();
        }

        //look up by name
        for(Product product : Inventory.getAllProducts())
        {
            if(product.getName().contains(name))
            {
                Inventory.getFilteredAllProducts().add(product);
            }
        }
        //Part is not found
        if (Inventory.getFilteredAllProducts().isEmpty()) //replaced ".getFilterAllParts()" with ".getFilteredAllProducts()"
        {
            Inventory.displayAlert(10);
            return Inventory.getAllProducts();
        }
        else
        {
            return getFilteredAllProducts();
        }
    }

    /** updates part
     * @param index where in memory the part is
     * @param part part with new information
     */
    public static void updatePart(int index, Part part) {
        allParts.set(index, part);
    }

    /** upadates product
     * @param index an index
     * @param product a product
     */
    public static void updateProduct(int index, Product product) { allProducts.set(index, product);}

    /** deletes part
     * @param selectedPart The item the user selected.
     * @return True/false showing if the item was deleted or not.
     */
    public static boolean deletePart (Part selectedPart) {
        if (allParts.contains(selectedPart)) {
            allParts.remove(selectedPart);
            return true;
        }
        else {
            return false;
        }
    }
    /** deletes product
     * @param selectedProduct The item the user selected.
     * @return true/false True/false showing if the item was deleted or not.
     */
    public static boolean deleteProduct (Product selectedProduct) {
        if (allProducts.contains(selectedProduct)) {
                allProducts.remove(selectedProduct);
            return true;
        }
        else {
            return false;
        }
    }

    /** gets a list of all parts
     * @return allParts list of all parts*/
    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    /**gets a list of all products
     * @return allProducts */
    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }

    /** Validates stock.
     * @param stock how many in stock
     * @param max max in stock
     * @param min min in stock */
    public static boolean validateStock(int stock, int max, int min,String name){
        boolean passed = true;

        if (max < min){
        Inventory.displayAlert(13);
        passed = false;
        }
        if (stock < min) {
            Inventory.displayAlert(11);
            passed = false;
        }
        if (stock > max) {
            Inventory.displayAlert(12);
            passed = false;
        }
        if (name.isBlank()){
            Inventory.displayAlert(17);
            passed = false;
        }
        return passed;
    }

    /** shows alerts
     * @param alertType tells which alert to display
     * @return*/
    public static Optional<ButtonType> displayAlert(int alertType) {

        Alert alertInfo = new Alert(Alert.AlertType.INFORMATION);
        Alert alertError = new Alert(Alert.AlertType.ERROR);
        Alert alertConfirm = new Alert(Alert.AlertType.CONFIRMATION);
        Alert alertWarning = new Alert(Alert.AlertType.WARNING);
        Optional<ButtonType> result;

        switch (alertType) {
            case 1:
                alertInfo.setTitle("Information");
                alertInfo.setHeaderText("Part not found");
                alertInfo.showAndWait();
                break;
            case 2:
                alertInfo.setTitle("Information");
                alertInfo.setHeaderText("Product not found");
                alertInfo.showAndWait();
                break;
            case 3:
                alertError.setTitle("Error");
                alertError.setHeaderText("No parts selected");
                alertError.showAndWait();
                break;
            case 4:
                alertError.setTitle("Error");
                alertError.setHeaderText("No Product selected");
                alertError.showAndWait();
                break;
            case 5:
                alertError.setTitle("Error");
                alertError.setHeaderText("Parts Associated");
                alertError.setContentText("All parts must be removed from the product before deletion.");
                alertError.showAndWait();
                break;
            case 6:
                alertError.setTitle("Error");
                alertError.setHeaderText("Invalid Inputs.");
                alertError.setContentText("Please check all text fields for valid input.");
                alertError.showAndWait();
                break;
            case 7:
                alertConfirm.setTitle("Confirmation");
                alertConfirm.setHeaderText("Are you sure you want to delete?");
                alertConfirm.setContentText("Press OK to delete or cancel to return to the previous screen.");
                alertConfirm.showAndWait();
                break;
            case 8:
                alertConfirm.setTitle("Confirmation");
                alertConfirm.setHeaderText("Are you sure you want to remove this part?");
                alertConfirm.setContentText("Press OK to remove or cancel to return to the previous screen.");
                alertConfirm.showAndWait();
                break;
            case 9:
                alertError.setTitle("Error");
                alertError.setHeaderText("Minimum great than maximum.");
                alertError.setContentText("Min should be less than Max; and Inv should be between those two values.");
                alertError.showAndWait();
                break;
            case 10:
                alertWarning.setTitle("Error");
                alertWarning.setHeaderText("Item not found.");
                alertWarning.setContentText("The item you are searching for was not found");
                alertWarning.showAndWait();
                break;
            case 11:
                alertError.setTitle("Error");
                alertError.setHeaderText("Item out of range.");
                alertError.setContentText("The inventory is less than the minimum.");
                alertError.showAndWait();
                break;
            case 12:
                alertError.setTitle("Error");
                alertError.setHeaderText("Item out of range.");
                alertError.setContentText("The inventory is more than the maximum.");
                alertError.showAndWait();
                break;
            case 13:
                alertError.setTitle("Error");
                alertError.setHeaderText("Item out of range.");
                alertError.setContentText("The minimum is more than the maximum.");
                alertError.showAndWait();
                break;
            case 14:
                alertInfo.setTitle("Information");
                alertInfo.setHeaderText("Part added");
                alertInfo.setContentText("Your part was added to the list.");
                alertInfo.showAndWait();
                break;
            case 15:
                alertError.setTitle("Error");
                alertError.setHeaderText("Can not delete.");
                alertError.setContentText("Please remove associated parts before deleting.");
                alertError.showAndWait();
                break;
            case 16:
                alertInfo.setTitle("Information");
                alertInfo.setHeaderText("Product added");
                alertInfo.setContentText("Your product was added to the list.");
                alertInfo.showAndWait();
                break;
            case 17:
                alertError.setTitle("Error");
                alertError.setHeaderText("Name is empty.");
                alertError.setContentText("Please add a name.");
                alertError.showAndWait();
                break;
            case 18:
                alertConfirm.setTitle("Alert");
                alertConfirm.setContentText("Do you want to delete this part?");
                result = alertConfirm.showAndWait();
                return result;
            case 19:
                alertConfirm.setTitle("Alert");
                alertConfirm.setContentText("Do you want to delete this product?");
                result = alertConfirm.showAndWait();
                return result;
            case 20:
                alertConfirm.setTitle("Alert");
                alertConfirm.setContentText("Do you want to remove the associated part?");
                result = alertConfirm.showAndWait();
                return result;
            case 21:
                alertError.setTitle("Error");
                alertError.setHeaderText("The table is empty.");
                alertError.setContentText("Can not delete from an empty table.");
                alertError.showAndWait();
                break;
        }
        return null;
    }
}




