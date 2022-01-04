package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/** Creates and controls product events.*/
public class Product {
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;
    /**creates products
     * @param id An int identifier (auto incremented).
     * @param name The name of part.
     * @param price The price of the Product.
     * @param stock How many are currently in stock.
     * @param min The minimum number in stock.
     * @param max The maximum number in stock.
     */
    public Product(int id, String name, double price, int stock, int min, int max) {
        this.id = Inventory.getNextProductId();
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    /** sets id.
     * @param id the id to set.
     */
    public void setId(int id) {
        this.id = id;
    }

    /** gets Id.
     * @return the id.
     */
    public int getId() {
        return id;
    }

    /** get name
     * @return the name.
     */
    public String getName() {
        return name;
    }

    /** set name
     * @param name the name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /** get price
     * @return the price.
     */
    public double getPrice() {
        return price;
    }

    /** set price
     * @param price the price to set.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /** get stock
     * @return the stock.
     */
    public int getStock() {
        return stock;
    }

    /** set stock
     * @param stock the stock to set.
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /** get min
     * @return the min.
     */
    public int getMin() {
        return min;
    }

    /** return min
     * @param min the min to set.
     */
    public void setMin(int min) {
        this.min = min;
    }

    /** get max
     * @return the max
     */
    public int getMax() {
        return max;
    }

    /** set max
     * @param max the max to set.
     */
    public void setMax(int max) {
        this.max = max;
    }

    /** list of all parts associated with a product
     * List of All Associated Parts.
     */
    private final ObservableList<Part> associatedParts = FXCollections.observableArrayList();

     /** adds a part to associated parts list
     * @param part add associated part
     */
    public void addAssociatedPart(Part part){
       associatedParts.add(part);
    }

    /** deletes a part from the associated part list
    * @param part delete Associated Part/
    */
    public void deleteAssociatedPart(Part part){
        associatedParts.remove(part);
    }

    /** gets associated parts list
     * @return Associated Parts list.
     */
    public ObservableList<Part> getAssociatedParts() {
        return associatedParts;
    }

}


