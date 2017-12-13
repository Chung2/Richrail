package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

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
