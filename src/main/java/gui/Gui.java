package gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class Gui extends Application{
    @FXML
    private TextField trainNameField;

    @FXML
    private Pane commandPane;

    @FXML
    private Pane displayPane;

    @FXML
    private Pane canvas;

    // 0 = display GUI, 1 = command GUI
    private int currentPane = 0;

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

        @FXML
        public void switchGUI(ActionEvent event) {
            switch (currentPane) {
                case 0:
                    this.currentPane = 1;
                    commandPane.setDisable(false);
                    commandPane.setVisible(true);
                    displayPane.setVisible(false);
                    displayPane.setDisable(true);
                    canvas.setDisable(false);
                    canvas.setDisable(true);
                    // switch to Command view
                    System.out.println("Switching to Command view");
                    break;
                case 1:
                    this.currentPane = 0;
                    commandPane.setDisable(true);
                    commandPane.setVisible(false);
                    displayPane.setDisable(false);
                    displayPane.setVisible(true);
                    canvas.setDisable(false);
                    canvas.setVisible(true);
                    // switch to Display view
                    System.out.println("Switching to Display view");
                    break;
                default:
                    break;
            }
        }

    }
