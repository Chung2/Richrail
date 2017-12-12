package gui;

import entity.Component;
import entity.ComponentType;
import entity.Train;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import models.ServiceProvider;

import javax.swing.*;
import java.io.IOException;
import java.util.List;

public class Gui extends Application{

    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("gui.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            GuiController guiController = fxmlLoader.getController();

            Scene scene = new Scene(root, 600, 500);

            primaryStage.setTitle("RichRail");
            primaryStage.setScene(scene);
            primaryStage.show();

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
