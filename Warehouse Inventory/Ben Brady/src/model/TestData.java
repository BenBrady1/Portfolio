package model;


/** Adds test data for dev purposes */
public class TestData {

    public static void addTestData(){

        Outsourced beads_red = new Outsourced(Inventory.getNextPartId(),"red beads",1.00,3,2,5,"Billys beads");
        Inventory.addPart(beads_red);
        Outsourced beads_blue = new Outsourced(Inventory.getNextPartId(),"blue beads",1.00,3,2,5,"Billys beads");
        Inventory.addPart(beads_blue);
        Outsourced beads_yellow = new Outsourced(Inventory.getNextPartId(),"yellow beads",1.00,3,2,5,"Billys beads");
        Inventory.addPart(beads_yellow);
        Outsourced part12 = new Outsourced(Inventory.getNextPartId(),"Part 12",0.1,3,2,5,"Billys beads");
        Inventory.addPart(part12);

        InHouse beads_black = new InHouse(Inventory.getNextPartId(),"black beads",1.00,3,2,5,651);
        Inventory.addPart(beads_black);
        InHouse beads_white = new InHouse(Inventory.getNextPartId(),"white beads",1.00,3,2,5,518);
        Inventory.addPart(beads_white);

        Product cat_hammock = new Product(Inventory.getNextPartId(),"cat hammock",10.00,4,1,10);
        Inventory.addProduct(cat_hammock);
        cat_hammock.addAssociatedPart(beads_black);
        cat_hammock.addAssociatedPart(beads_blue);
        Product ferret_hammock = new Product(Inventory.getNextPartId(),"ferret hammock",10.00,4,1,10);
        Inventory.addProduct(ferret_hammock);
        cat_hammock.addAssociatedPart(beads_black);
        cat_hammock.addAssociatedPart(beads_yellow);
        Product human_hammock = new Product(Inventory.getNextPartId(),"human hammock",10.00,4,1,10);
        Inventory.addProduct(human_hammock);
    }


}
