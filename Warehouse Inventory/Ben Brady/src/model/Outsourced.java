package model;

/** Creates,gets,sets Inhouse parts.*/
public class Outsourced extends Part{
    /** The company's name.*/
    private String companyName;
    /**creates Outsourced parts
     * @param id An int identifier (auto incremented).
     * @param name The name of part.
     * @param price The price of the Product.
     * @param stock How many are currently in stock.
     * @param min The minimum number in stock.
     * @param max The maximum number in stock.
     * @param companyName The name of the company that provides the part.*/
    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }
    /** gets company name
     * @return company name */
    public String getCompanyName() {
        return companyName;
    }

    /** sets company name
     * @param companyName sets the company name
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
