package gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class Gui extends Application{
    @FXML
    private TextField trainNameField;

    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("gui.fxml"));

            Scene scene = new Scene(root, 600, 500);

            primaryStage.setTitle("RichRail");
            primaryStage.setScene(scene);
            primaryStage.show();

        }
        catch (IOException e) {
            e.printStackTrace();
        }}

        @FXML
        public void test(ActionEvent event){
        System.out.println(trainNameField.getText());
    }

    }
