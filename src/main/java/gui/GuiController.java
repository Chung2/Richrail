package gui;

import entity.Component;
import entity.ComponentType;
import entity.Train;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import models.ServiceProvider;

import java.util.List;

/**
 * Created by Chung on 12-Dec-17.
 */



public class GuiController {


    @FXML
    private Pane commandPane;

    @FXML
    private Pane displayPane;

    @FXML
    private Pane canvas;

    // 0 = display GUI, 1 = command GUI
    private int currentPane = 0;

    @FXML
    private ChoiceBox trainField;

    @FXML
    private Button selectTrainBtn;

    @FXML
    private Button createWagonBtn;

    @FXML
    private Label selectedTrain;

    @FXML
    private TextField trainNameField;

    @FXML
    private ChoiceBox componentField;

    @FXML
    private TextField seatsField;

    private Gui gui;

    public void setMain(Gui gui) {
        this.gui = gui;
    }

    @FXML
    public void alertMessageNewTrain(int error) {
        Alert alert = new Alert(Alert.AlertType.ERROR);

        //error 1 = empty textfield
        //error 2 = train name exist
        switch (error) {
            case 1:
                alert.setTitle("Error new train name");
                alert.setHeaderText("The new train name is empty!");
                alert.setContentText("Please insert a name for the train!");

                alert.showAndWait();

                break;
            case 2:

                alert.setTitle("Error new train name");
                alert.setHeaderText("The new train name is exist");
                alert.setContentText("Please insert a new name for the train!");

                alert.showAndWait();

                break;
        }
    }

    @FXML
    public void initialize() {
        //choiceBox.getItems().removeAll(choiceBox.getItems());
        List<Train> trainList = ServiceProvider.getTrainService().getAllTrains();
        for (Train train : trainList) {
            trainField.getItems().add(train.getName());
        }
        trainField.getSelectionModel().selectFirst();

        List<ComponentType> componentTypes = ServiceProvider.getComponentTypeService().getAllComponentTypes();
        for (ComponentType componentType : componentTypes) {
            componentField.getItems().add(componentType.getName());
        }
        componentField.getSelectionModel().selectFirst();
    }

    @FXML
    public void selectTrain(ActionEvent event) {
        selectedTrain.setText(trainField.getSelectionModel().getSelectedItem().toString());

    }

    @FXML
    public void insertTrain(ActionEvent event) {
        if (trainNameField.getText() == null || trainNameField.getText().trim().isEmpty()) {
            alertMessageNewTrain(1);
        } else {
            if (ServiceProvider.getTrainService().getTrainByName(trainNameField.getText()) == null) {
                Train train = new Train();
                train.setName(trainNameField.getText());
                ServiceProvider.getTrainService().addTrain(train);
            } else {
                alertMessageNewTrain(2);
            }
        }
    }

    @FXML
    public void insertComponent(ActionEvent event) {
        if (seatsField.getText() == null || seatsField.getText().trim().isEmpty()) {
            alertMessageNewTrain(1);
        } else {
            Component cpt = new Component();
            cpt.setTrain(ServiceProvider.getTrainService().getTrainByName(selectedTrain.getText()));
            cpt.setSeats(Integer.parseInt(seatsField.getText().toString()));
            cpt.setComponentType(ServiceProvider.getComponentTypeService().getComponentTypeByName(componentField.getSelectionModel().getSelectedItem().toString()));
            cpt.setOrder(99);
            ServiceProvider.getComponentService().addComponent(cpt);
        }
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
