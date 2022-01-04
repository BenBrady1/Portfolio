package main;
import model.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.fxml.FXMLLoader.load;


/** Javadocs in first level folder of project /Software I/JavaDocs Software 1.
 * Main class and entry in to program.
 * FUTURE ENHANCEMENT: replaced ".getFilterAllParts" with "getFilteredAllProducts" to fix glitch where product search would not work unless part search was done first.
 * FUTURE ENHANCEMENT: Moved companyName and machineId inside their if statements so to fix the Over zealous NumberFormatException catch occurred when you put in letters the companyName text field.
 * FUTURE ENHANCEMENT: Abstracted delete conformation and added it inventory.displayAlerts. Then added it to deletes and removes.
 * FUTURE ENHANCEMENT: In the constructor of modify parts/products the min/max were switched. Put in right order.
 * FUTURE ENHANCEMENT: Added an "is table empty" validation to all tables.
 */
public class Main extends Application {
    /**
     * Sets main stage and starts User Interface.
     * @param primaryStage is the starting stage.
     * @throws IOException
     */
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/HomeForm.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Inventory Management System");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Entry point to application.
     * @param args unused
     */
    public static void main(String[] args){
        //Add test data
       TestData.addTestData();

    launch(args);}
}
