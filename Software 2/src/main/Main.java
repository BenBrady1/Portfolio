package main;
// Ben Brady
// Student ID: 001160134
// mySQL Connector:8.0.22

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.jdbc.DBDriver;
import model.LoginAttempt;

import java.sql.SQLException;

/** mySQL Connector:8.0.22
 * Main class and entry in to program.
 * FUTURE ENHANCEMENTS:
 */
public class Main<myFrame> extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/viewController/logInView.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("IntelliDoc");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    //Create LoginAttempt monitor
    public static LoginAttempt snitch = new LoginAttempt("",0,null,null,null);

    public static void main(String[] args) throws SQLException {
        //Test French
        //Locale.setDefault(new Locale("fr"));

        DBDriver.startConnection();
        launch(args);
        DBDriver.closeConnection();
    }
}
