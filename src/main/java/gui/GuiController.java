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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import models.ServiceProvider;
import org.hibernate.boot.jaxb.SourceType;

import javax.swing.plaf.synth.SynthTextAreaUI;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chung on 12-Dec-17.
 */



public class GuiController {

    @FXML
    private HBox hbox;

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
    private ImageView cargoTrainImage;

    @FXML
    private Button selectTrainBtn;

    @FXML
    private Button createWagonBtn;

    @FXML
    private Label selectedTrain;

    @FXML
    private TextField trainNameField;

    @FXML
    private ComboBox componentfieldtype;

    @FXML
    private TextField seatsField;

    @FXML
    private ComboBox allComponents;

    @FXML
    private Button switchView;

    @FXML
    private ImageView locomotiveImage;

    @FXML
    private ImageView wagonImage;

    private Gui gui;

    public void setMain(Gui gui) {
        this.gui = gui;
    }


    @FXML
    public void initialize() {
        getTrains();
        getComponentTypes();
    }

    @FXML
    public void alertMessageNewTrain(int error) {
        Alert alert = new Alert(Alert.AlertType.ERROR);

        //error 1 = empty textfield
        //error 2 = train name exist
        //error 3 = component code exist
        //error 4 = Componenttype exist

        switch (error) {
            case 1:
                alert.setTitle("Error new train name");
                alert.setHeaderText("The new train name already empty!");
                alert.setContentText("Please insert a name for the train!");

                alert.showAndWait();

                break;

            case 2:

                alert.setTitle("Error new train name");
                alert.setHeaderText("The new train name already exist");
                alert.setContentText("Please insert a new name for the train!");

                alert.showAndWait();

                break;

            case 3:

                alert.setTitle("Error new component code");
                alert.setHeaderText("The new component code already exist");
                alert.setContentText("Please insert a new code for the component!");

                alert.showAndWait();
                break;

            case 4:

                alert.setTitle("Error new componenttype");
                alert.setHeaderText("The new componenttype already exist");
                alert.setContentText("Please insert a new componenttype!");

                alert.showAndWait();
                break;

            case 5:
                alert.setTitle("Error new component");
                alert.setHeaderText("The new component code or seat field is empty!");
                alert.setContentText("Please insert a code or seatnumbers in!");

                alert.showAndWait();

                break;

        }
    }

    @FXML
    public void getTrains(){
        trainField.getItems().clear();
        List<Train> trainList = ServiceProvider.getTrainService().getAllTrains();
        for (Train train : trainList) {
            trainField.getItems().add(train.getName());
        }
        trainField.getSelectionModel().selectFirst();

    }

    public List<Integer> componentTypeIds(){
        List<Integer> ids = new ArrayList<>();
        for(Component component: ServiceProvider.getTrainService().getTrainByName(selectedTrain.getText()).getComponents()){
            ids.add(component.getComponentType().getId());
        }
        return ids;
    }

    //create list<Image> to add in addElementsHbox
    public List<Image> createImage(List<Integer> componenttypeids){
        List imageViews = new ArrayList<Image>();
        try {
            FileInputStream input;
            for (Integer id : componenttypeids){
                switch (id) {
                    case 1:
                        input = new FileInputStream(System.getProperty("user.dir") + "/" +
                                "src/main/resources/assets/locomotive.jpg");
                        Image imageLocomotive = new Image(input);
                        System.out.println("DEBUG LOCOMOTIVE");
                        imageViews.add(imageLocomotive);
                        System.out.println("locomotive image is created");
                        break;

                    case 2:
                        input = new FileInputStream(System.getProperty("user.dir") + "/" +
                                "src/main/resources/assets/wagon.jpg");
                        Image imageWagon = new Image(input);
                        System.out.println("DEBUG WAGON");
                        imageViews.add(imageWagon);

                        System.out.println("wagon image is created");
                        break;
                    case 3:
                        input = new FileInputStream(System.getProperty("user.dir") + "/" +
                                "src/main/resources/assets/cargotrain.jpg");
                        Image imageCargoTrain = new Image(input);
                        System.out.println("DEBUG CARGOTRAIN");
                        imageViews.add(imageCargoTrain);

                        System.out.println("cargoTrain image is created");
                        break;
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return imageViews;
    }

    public void addElementsHbox(){
        hbox.getChildren().clear();
        for(Image image : createImage(componentTypeIds())){
            hbox.getChildren().add(new ImageView(image));
        }
    }

    public void getComponentTypes(){
        componentfieldtype.getItems().clear();
        List<ComponentType> componentTypes = ServiceProvider.getComponentTypeService().getAllComponentTypes();
        for (ComponentType componentType : componentTypes) {
            componentfieldtype.getItems().add(componentType.getName());
        }
        componentfieldtype.getSelectionModel().selectFirst();
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
    public void selectTrain(ActionEvent event) {
        selectedTrain.setText(trainField.getSelectionModel().getSelectedItem().toString());
        getComponents();
        addElementsHbox();
    }

    @FXML
    public void insertTrain(ActionEvent event) {
        if (checktemptyfieldtrain()) {
            alertMessageNewTrain(1);
        } else {
            if (checkTrainName()) {
                Train train = new Train();
                train.setName(trainNameField.getText());
                ServiceProvider.getTrainService().addTrain(train);
                initialize();
            } else {
                alertMessageNewTrain(2);
            }
        }
    }


    public boolean checktemptyfieldtrain(){
        boolean empty = false;

        if(trainNameField.getText() == null || trainNameField.getText().trim().isEmpty()){
            empty = true;
        }
        return empty;
    }

    public boolean checkcomponentfield(){
        boolean input = false;
        if(allComponents.getSelectionModel().getSelectedItem().toString() == null || componentfieldtype.getSelectionModel().getSelectedItem().toString().isEmpty()){
            input = true;
        }
        return input;
    }

    public boolean checkemptyfieldseats(){
        boolean input = false;
        if (seatsField.getText() == null || seatsField.getText().trim().isEmpty()){
            input = true;
        }
        return input;
    }

    public boolean checkemptycomponenttype(){
        boolean empty = false;

        System.out.println(componentfieldtype.getSelectionModel().getSelectedItem().toString());
        if (componentfieldtype.getSelectionModel().getSelectedItem().toString() == null || componentfieldtype.getSelectionModel().getSelectedItem().toString().trim().isEmpty()){
            empty = true;
        }

        return empty;
    }

    public boolean checkTrainName(){
        boolean exist = false;

        if(ServiceProvider.getTrainService().getTrainByName(trainNameField.getText()) == null){
            exist = true;
        }
        return exist;
    }

    public boolean checkComponentCode(){
        boolean exist = true;

        if(ServiceProvider.getTrainService().getTrainByName(allComponents.getSelectionModel().getSelectedItem().toString()) == null){
            exist = false;
        }
        return exist;
    }

    public boolean checkComponentType(){
        boolean exist = true;
        if(ServiceProvider.getComponentTypeService().getComponentTypeByName(componentfieldtype.getSelectionModel().getSelectedItem().toString()) == null){
            exist = false;
        }
        return exist;
    }

    public void insertComponentType(){
        if (checkemptycomponenttype()) {
            alertMessageNewTrain(1);
        } else {
            if(!checkComponentType()) {
                ComponentType componentType = new ComponentType();
                componentType.setName(componentfieldtype.getSelectionModel().getSelectedItem().toString());
                ServiceProvider.getComponentTypeService().addComponentType(componentType);
                //initialize();
            }
        }
    }

    public void insertComponent(){
        Component cpt = new Component();
        cpt.setTrain(ServiceProvider.getTrainService().getTrainByName(selectedTrain.getText()));
        cpt.setSeats(Integer.parseInt(seatsField.getText().toString()));
        cpt.setComponentType(ServiceProvider.getComponentTypeService().getComponentTypeByName(componentfieldtype.getSelectionModel().getSelectedItem().toString()));
        cpt.setCode(allComponents.getSelectionModel().getSelectedItem().toString());
        ServiceProvider.getComponentService().addComponent(cpt);
    }

    @FXML
    public void insertComponentProcess(ActionEvent event) {
        if (checkcomponentfield() || checkemptyfieldseats()) {
            alertMessageNewTrain(5);
        } else {
            System.out.println("test");
            System.out.println(checkComponentCode());
            if(!checkComponentCode()){
                if(checkComponentType()){
                    insertComponent();
                }
                else{
                    insertComponentType();
                    insertComponent();
                }
            }
            else{
                alertMessageNewTrain(3);
            }
        }
    }

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