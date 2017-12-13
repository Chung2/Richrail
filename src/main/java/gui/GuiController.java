package gui;

import entity.Component;
import entity.ComponentType;
import entity.Train;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import models.ServiceProvider;

import java.io.IOException;
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

    @FXML
    private ComboBox allComponents;

    @FXML
    private Button switchView;

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
    public void  getTrains(){
        trainField.getItems().clear();
        List<Train> trainList = ServiceProvider.getTrainService().getAllTrains();
        for (Train train : trainList) {
            trainField.getItems().add(train.getName());
        }
        trainField.getSelectionModel().selectFirst();

    }

    public void getComponentTypes(){
        componentField.getItems().clear();
        List<ComponentType> componentTypes = ServiceProvider.getComponentTypeService().getAllComponentTypes();
        for (ComponentType componentType : componentTypes) {
            componentField.getItems().add(componentType.getName());
        }
        componentField.getSelectionModel().selectFirst();
    }

    public void getComponents(){
        allComponents.getItems().clear();
        List<Component> components = ServiceProvider.getTrainService().getTrainByName(selectedTrain.getText()).getComponents();
        for(Component component : components){
            allComponents.getItems().add(component.getCode());
        }
        allComponents.getSelectionModel().selectFirst();
    }

    @FXML
    public void initialize() {
        getTrains();
        getComponentTypes();
    }

    @FXML
    public void selectTrain(ActionEvent event) {
        selectedTrain.setText(trainField.getSelectionModel().getSelectedItem().toString());
        getComponents();
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
                initialize();
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
            cpt.setCode("");
            ServiceProvider.getComponentService().addComponent(cpt);
        }
    }

//    @FXML
//    public void switchGUI(ActionEvent event) {
//        switch (currentPane) {
//            case 0:
//                this.currentPane = 1;
//                commandPane.setDisable(false);
//                commandPane.setVisible(true);
//                displayPane.setVisible(false);
//                displayPane.setDisable(true);
//                canvas.setDisable(false);
//                canvas.setDisable(true);
//                // switch to Command view
//                System.out.println("Switching to Command view");
//                break;
//            case 1:
//                this.currentPane = 0;
//                commandPane.setDisable(true);
//                commandPane.setVisible(false);
//                displayPane.setDisable(false);
//                displayPane.setVisible(true);
//                canvas.setDisable(false);
//                canvas.setVisible(true);
//                // switch to Display view
//                System.out.println("Switching to Display view");
//                break;
//            default:
//                break;
//        }

    @FXML
    private void switchGUI(ActionEvent event) throws IOException {
        Stage stage;
        stage=(Stage) switchView.getScene().getWindow();
        //load up OTHER FXML document

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("commandgui.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        CommandController commandController = fxmlLoader.getController();

        //create a new scene with root and set the stage
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
