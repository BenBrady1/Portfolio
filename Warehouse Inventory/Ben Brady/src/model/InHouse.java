package model;

/** Creates,gets,sets Inhouse parts.*/
public class InHouse extends Part{
    private int machineId;

    /** constructor for inhouse.
     * @param id An int identifier (auto incremented).
     * @param name The name of part.
     * @param price The price of the Product.
     * @param stock How many are currently in stock.
     * @param min The minimum number in stock.
     * @param max The maximum number in stock.
     * @param machineId The machine id of the part.*/
    public InHouse(int id , String name, double price, int stock, int min, int max, int machineId) {
        super(id, name, price, stock, min, max);
        this.machineId = machineId;
    }

    /** return the machineId.
     * @return machineId the identifier for Inhouse items*/
    public int getMachineId() {
        return machineId;
    }

    /** Set machine id.
     * @param machineId the identifier for Inhouse items.*/
    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }

}
