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
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import models.ServiceProvider;

import java.io.IOException;
import java.rmi.server.ExportException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class CommandController{
    @FXML
    private TextField trainNameField;

    @FXML
    private TextField commandLine;

    @FXML
    private Pane commandPane;

    @FXML
    private Pane displayPane;

    @FXML
    private Pane canvas;

    @FXML
    private TextArea statusLogger;

    @FXML
    private TextArea commandLogger;

    @FXML
    private Button switchView;

    // 0 = display GUI, 1 = command GUI
    private int currentPane = 0;

    public static String getCurrentTimeStamp() {
        SimpleDateFormat sdfDate = new SimpleDateFormat("HH:mm:ss");//dd/MM/yyyy
        Date now = new Date();
        String strDate = sdfDate.format(now);
        return strDate;
    }

    public void addStatusLoggerMessage(String s) {
        this.statusLogger.setText(this.statusLogger.getText() + "\n" + s);
    }

    public void addCommandLoggerMessage(String s) {
        this.commandLogger.setText(this.commandLogger.getText() + "\n" + "[" + getCurrentTimeStamp() + "] :" + s);
    }

    @FXML
    public void test(ActionEvent event){
        System.out.println(trainNameField.getText());
    }

    @FXML
    public void readCommandLine(ActionEvent event) {
        String input = commandLine.getText();
        List<String> list = Arrays.asList(input.split(" "));
        Train train;
        Component component;
        ComponentType componentType;

        // set i to 1 if command is correct, else commandLogger will display "command not correct" if i = 0
        int i = 0;

        addStatusLoggerMessage(input);

        switch (list.get(0)) {
            // create a new train or component
            case "new":

                switch (list.get(1)) {

                    // create a new train
                    case "train":

                        if (list.get(2) != null) {

                            try {
                                train = new Train();
                                train.setName(list.get(2));
                                ServiceProvider.getTrainService().addTrain(train);
                                addCommandLoggerMessage("train " + list.get(2) + " created");
                            } catch (Exception e) {
                                addCommandLoggerMessage("train" + list.get(2) + " could not be created");
                            }
                            i = 1;
                        }
                        break;

                    // create a new component
                    case "component":
                        break;


                }
                break;

            // add a component to a train
            case "add":

                break;

            // get number of seats from a component
            case "getnumseats":

                break;

            // delete a component or train
            case "delete":

                switch (list.get(1)) {

                    // delete a train
                    case "train":

                        if (list.get(2) != null) {
                            try {
                                train = ServiceProvider.getTrainService().getTrainByName(list.get(2));
                                if (train == null) {throw new Exception();}
                                ServiceProvider.getTrainService().deleteTrain(train);
                                addCommandLoggerMessage("train " + list.get(2) + " deleted");
                            } catch (Exception e) {
                                addCommandLoggerMessage("train " + list.get(2) + " does not exist");
                            }
                            i = 1;
                        }
                        break;

                    // delete a component
                    case "component":

                        break;


                }
                break;

            // remove a component from a train
            case "remove":

                break;
        }

        if (i == 0) {
            addCommandLoggerMessage("command not correct");
        }
        i = 0;
    }

    @FXML
    private void switchGUI(ActionEvent event) throws IOException {
        Stage stage;
        stage=(Stage) switchView.getScene().getWindow();
        //load up OTHER FXML document

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("gui.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        GuiController guiController = fxmlLoader.getController();

        //create a new scene with root and set the stage
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
