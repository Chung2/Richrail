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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class CommandController{
    @FXML
    private TextField trainNameField;

    @FXML
    private TextField commandLine;

    @FXML
    private TextArea statusLogger;

    @FXML
    private TextArea commandLogger;

    @FXML
    private Button switchView;

    @FXML
    public void initialize() {
        updateStatusLogger();
    }

    public static String getCurrentTimeStamp() {
        SimpleDateFormat sdfDate = new SimpleDateFormat("HH:mm:ss");//dd/MM/yyyy
        Date now = new Date();
        String strDate = sdfDate.format(now);
        return strDate;
    }

    public void updateStatusLogger() {

        String statusLogger = "";
        List<Component> components = ServiceProvider.getComponentService().getAllComponents();
        List<Train> trains = ServiceProvider.getTrainService().getAllTrains();

        statusLogger += "trains (name) : (components_name) \n \n";

        for (Train train : trains) {
            statusLogger += "(" + train.getName() + ")";
            for (Component traincomponent : train.getComponents()){
                statusLogger += "-(" + traincomponent.getCode() + ")";
            }
            statusLogger += "\n";
        }

        statusLogger += "\n \n components (name : seats : type) \n \n";

        for (Component component : components) {
            statusLogger += "(" + component.getCode() + " : " + component.getSeats() + " : " + component.getComponentType().getName() + ")\n";
        }



        this.statusLogger.setText(statusLogger);

    }

    public void addStatusLoggerMessage(String s) {
        //this.statusLogger.setText(this.statusLogger.getText() + "\n" + s);
    }

    public void addCommandLoggerMessage(String s) {
        this.commandLogger.setText(this.commandLogger.getText() + "\n" + "[" + getCurrentTimeStamp() + "] :" + s);
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

                                // check whether name is unique
                                List<Train> trains = ServiceProvider.getTrainService().getAllTrains();
                                for (Train t : trains) {
                                    if (t.getName().equals(list.get(2))) {
                                        throw new Exception();
                                    }
                                }
                                train.setName(list.get(2));

                                ServiceProvider.getTrainService().addTrain(train);
                                addCommandLoggerMessage("train " + list.get(2) + " created");
                            } catch (Exception e) {
                                addCommandLoggerMessage("train " + list.get(2) + " could not be created");
                            }
                            i = 1;
                        }
                        break;

                    // create a new component
                    case "component":

                        if (list.get(2) != null) {

                            try {
                                component = new Component();

                                // check whether name is unique
                                List<Component> components = ServiceProvider.getComponentService().getAllComponents();
                                for (Component c : components) {
                                    if (c.getCode().equals(list.get(2))) {
                                        throw new Exception();
                                    }
                                }
                                component.setCode(list.get(2));

                                // assign component type
                                if (list.get(3).equals("type") && list.get(4) != null) {
                                    ComponentType type = ServiceProvider.getComponentTypeService().getComponentTypeByName(list.get(4));
                                    if (type != null) {
                                        component.setComponentType(type);
                                    } else throw new Exception();
                                } else if (list.get(5).equals("type") && list.get(6) != null) {
                                    ComponentType type = ServiceProvider.getComponentTypeService().getComponentTypeByName(list.get(6));
                                    if (type != null) {
                                        component.setComponentType(type);
                                    } else throw new Exception();
                                } else {
                                    List<ComponentType> types = ServiceProvider.getComponentTypeService().getAllComponentTypes();
                                    component.setComponentType(types.get(0));
                                }

                                // assign seats
                                if (list.get(3).equals("numseats") && list.get(4) != null) {
                                    component.setSeats(Integer.parseInt(list.get(4)));
                                } else if (list.get(5).equals("numseats") && list.get(6) != null) {
                                    component.setSeats(Integer.parseInt(list.get(6)));
                                } else {
                                    component.setSeats(20);
                                }

                                // create component
                                ServiceProvider.getComponentService().addComponent(component);
                                addCommandLoggerMessage("component " + list.get(2) + " created with " + component.getSeats() + " seats and componentType " + component.getComponentType().getName());
                            } catch (Exception e) {
                                addCommandLoggerMessage("component " + list.get(2) + " could not be created");
                            }
                            i = 1;
                        }
                        break;

                    // create a new component type
                    case "type":

                        if (list.get(2) != null) {

                            try {
                                componentType = new ComponentType();

                                // check whether name is unique
                                List<ComponentType> types = ServiceProvider.getComponentTypeService().getAllComponentTypes();
                                for (ComponentType t : types) {
                                    if (t.getName().equals(list.get(2))) {
                                        throw new Exception();
                                    }
                                }
                                componentType.setName(list.get(2));

                                // create component type
                                ServiceProvider.getComponentTypeService().addComponentType(componentType);
                                addCommandLoggerMessage("componentType " + list.get(2) + " created");
                            } catch (Exception e) {
                                addCommandLoggerMessage("componentType " + list.get(2) + " could not be created");
                            }
                            i = 1;
                        }
                        break;

                }
                break;

            // add a component to a train
            case "add":
                if (list.get(2).equals("to") && list.get(3) != null) {

                    try {
                        // check whether id's actually exist
                        //TODO implement getComponentByCode()
                        Component c = ServiceProvider.getComponentService().getComponentById(Integer.parseInt(list.get(1)));
                        Train t = ServiceProvider.getTrainService().getTrainByName(list.get(3));

                        if (c != null && t != null) {
                            c.setTrain(t);
                        }

                        ServiceProvider.getComponentService().updateComponent(c);
                        addCommandLoggerMessage("component " + list.get(1) + " added to train " + list.get(3));
                    } catch (Exception e) {
                        addCommandLoggerMessage("componentType " + list.get(1) + " could not be created");
                    }
                    i = 1;
                }
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
        updateStatusLogger();
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
