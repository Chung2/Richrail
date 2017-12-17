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
        List<Train> trains = ServiceProvider.getTrainService().getAllTrains();
        List<Component> components = ServiceProvider.getComponentService().getAllComponents();

        statusLogger += "trains [name] \n(component_name)-(component_name)-..... \n \n";

        for (Train train : trains) {
            statusLogger += "[" + train.getName() + "]\n";
            for (Component traincomponent : train.getComponents()){
                statusLogger += "(" + traincomponent.getCode() + ")-";
            }

            // remove the "-" from the final component
            if (!train.getComponents().isEmpty()) {
                statusLogger = statusLogger.substring(0, (statusLogger.length() - 1));
            }

            statusLogger += "\n";
        }

        statusLogger += "\n \n components (name : seats : type) \n \n";

        for (Component component : components) {
            statusLogger += "(" + component.getCode() + " : " + component.getSeats() + " : " + component.getComponentType().getName() + ")\n";
        }



        this.statusLogger.setText(statusLogger);

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
                                System.out.println(component.getCode());

                                try {
                                    // assign component type
                                    if (list.get(3).equals("type") && list.get(4) != null) {
                                        ComponentType type = ServiceProvider.getComponentTypeService().getComponentTypeByName(list.get(4));
                                        if (type != null) {
                                            component.setComponentType(type);
                                        } else {throw new Exception();}
                                    } else if (list.get(5).equals("type") && list.get(6) != null) {
                                        ComponentType type = ServiceProvider.getComponentTypeService().getComponentTypeByName(list.get(6));
                                        if (type != null) {
                                            component.setComponentType(type);
                                        } else {throw new Exception();}
                                    } else {
                                        ComponentType type = ServiceProvider.getComponentTypeService().getComponentTypeByName("wagon");
                                        component.setComponentType(type);
                                    }
                                } catch (Exception e) {
                                    ComponentType type = ServiceProvider.getComponentTypeService().getComponentTypeByName("wagon");
                                    component.setComponentType(type);
                                }

                                System.out.println(component.getComponentType().getName());

                                try {
                                    // assign seats
                                    if (list.get(3).equals("numseats") && list.get(4) != null) {
                                        component.setSeats(Integer.parseInt(list.get(4)));
                                    } else if (list.get(5).equals("numseats") && list.get(6) != null) {
                                        component.setSeats(Integer.parseInt(list.get(6)));
                                    } else {
                                        component.setSeats(20);
                                    }
                                } catch (Exception e) {
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
                        //Component c = ServiceProvider.getComponentService().getComponentById(Integer.parseInt(list.get(1)));
                        Component c = ServiceProvider.getComponentService().getComponentByCode(list.get(1));
                        Train t = ServiceProvider.getTrainService().getTrainByName(list.get(3));

                        if (c != null && t != null) {
                            c.setTrain(t);
                            ServiceProvider.getComponentService().updateComponent(c);
                            addCommandLoggerMessage("component " + list.get(1) + " added to train " + list.get(3));
                        } else {
                            throw new Exception();
                        }
                    } catch (Exception e) {
                        addCommandLoggerMessage("component " + list.get(1) + " could not be added to " + list.get(4));
                    }
                    i = 1;
                }
                break;

            // get number of seats from a component
            case "getnumseats":

                switch (list.get(1)) {

                    // get number of seats in train
                    case "train":

                        try {
                            if (list.get(2) != null) {
                                // check whether id actually exist
                                Train t = ServiceProvider.getTrainService().getTrainByName(list.get(2));

                                if (t != null) {
                                    int result = 0;
                                    for (Component c : ServiceProvider.getComponentService().getComponentsByTrainId(t.getId())) {
                                        result += c.getSeats();
                                    }
                                    addCommandLoggerMessage("number of seats in train " + list.get(2) + ": " + result);
                                } else {
                                    throw new Exception();
                                }
                            }
                        } catch (Exception e) {
                            addCommandLoggerMessage("seat number from train " + list.get(2) + " could not be counted");
                        }
                        i = 1;
                        break;

                    // get number of seats in component
                    case "component":

                        try {
                            if (list.get(2) != null) {
                                // check whether id actually exist
                                //TODO implement getComponentByCode()
                                //Component c = ServiceProvider.getComponentService().getComponentById(Integer.parseInt(list.get(2)));
                                Component c = ServiceProvider.getComponentService().getComponentByCode(list.get(2));
                                if (c != null) {
                                    addCommandLoggerMessage("number of seats in component " + list.get(2) + ": " + c.getSeats());
                                } else {
                                    throw new Exception();
                                }
                            }

                        } catch (Exception e) {
                            addCommandLoggerMessage("seat number from component " + list.get(2) + " could not be counted");
                        }
                        i = 1;
                        break;
                }

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

                        if (list.get(2) != null) {
                            try {
                                component = ServiceProvider.getComponentService().getComponentByCode(list.get(2));
                                if (component == null) {throw new Exception();}
                                ServiceProvider.getComponentService().deleteComponentById(component.getId());
                                addCommandLoggerMessage("component " + list.get(2) + " deleted");
                            } catch (Exception e) {
                                addCommandLoggerMessage("component " + list.get(2) + " does not exist");
                            }
                            i = 1;
                        }
                        break;


                }
                break;

            // remove a component from a train
            case "remove":

                if (list.get(1) != null && list.get(2).equals("from") && list.get(3) != null) {

                    try {
                        // check whether id's actually exist
                        //TODO implement getComponentByCode()
                        //Component c = ServiceProvider.getComponentService().getComponentById(Integer.parseInt(list.get(1)));
                        Component c = ServiceProvider.getComponentService().getComponentByCode(list.get(1));
                        Train t = ServiceProvider.getTrainService().getTrainByName(list.get(3));

                        if (c != null && t != null) {
                            c.setTrain(null);
                            ServiceProvider.getComponentService().updateComponent(c);
                        } else {
                            throw new Exception();
                        }
                    } catch (Exception e) {
                        addCommandLoggerMessage("component " + list.get(1) + " could not be removed from train " + list.get(3));
                    }
                    i = 1;
                }
                break;

            case "help":

                String helpMessage = "grammar RichRail;\n\n";
                helpMessage += "command\t: newcommand | addcommand | getcommand | remcommand | helpcommand;\n\n";
                helpMessage += "newcommand\t: newtraincommand | newcomponentcommand | newtypecommand;\n";
                helpMessage += "newtraincommand\t: \'new\' \'train\' ID;\n";
                helpMessage += "newcomponentcommand\t: \'new\' \'component\' ID (\'numseats\' NUMBER) | (\'type\' NUMBER)?;\n";
                helpMessage += "newtypecommand\t: \'new\' \'type\' ID;\n";
                helpMessage += "addcommand\t: \'add\' ID \'to\' ID;\n";
                helpMessage += "getcommand\t: \'getnumseats\' type ID;\n";
                helpMessage += "delcommand\t: \'delete\' type ID;\n";
                helpMessage += "remcommand\t: \'remove\' ID \'from\' ID;\n";
                helpMessage += "helpcommand\t: \'help\';\n\n";
                helpMessage += "type \t: (\'train\') | (\'component\');\n\n";
                helpMessage += "ID\t: (\'a\'..\'z\')(\'a\'..\'z\'|\'0\'..\'9\')*;\n";
                helpMessage += "NUMBER\t: (\'0\'..\'9\')+;\n";
                helpMessage += "WHITESPACE\t: (\'\\t\' | \' \' | \'\\r\' | \'\\n\' | \'\\u000C\' )+;";

                addCommandLoggerMessage(helpMessage);

                i = 1;
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
