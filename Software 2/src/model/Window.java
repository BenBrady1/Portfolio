package model;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import viewController.homeController;

import javax.swing.*;
import java.io.IOException;

public interface Window {

    /** Brings user to given screen.*/
    public static void switchScreen(String screen, ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(homeController.class.getResource(screen));
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("InteliDoc");
        stage.setScene(scene);
        stage.show();
    }

    /** Brings user to home screen.*/
    public static void goHome (ActionEvent actionEvent)throws IOException {
        switchScreen("/viewController/homeView.fxml",actionEvent);
    }
}
