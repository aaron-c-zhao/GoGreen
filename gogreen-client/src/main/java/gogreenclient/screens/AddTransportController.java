package gogreenclient.screens;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import gogreenclient.datamodel.UserInputValidator;
import gogreenclient.screens.window.SceneController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.springframework.beans.factory.annotation.Autowired;


public class AddTransportController implements SceneController {


    ObservableList<String> transportList = FXCollections
        .observableArrayList("walk", "bike", "train", "bus", "car", "motorcycle", "plane");

    @FXML
    private JFXComboBox takenTransportBox;

    @FXML
    private JFXComboBox insteadOfTransportBox;

    @FXML
    private JFXTextField distance;

    @FXML
    private JFXDatePicker date;

    @FXML
    private Label fillAll;

    @Autowired
    private UserInputValidator validator;

    private ScreenConfiguration screens;

    public AddTransportController(ScreenConfiguration screens) {
        this.screens = screens;
    }

    /**
     * initializes the dropdown menus.
     *
     * @throws Exception when incorrect text input.
     */
    public void initialize() {
        validator.validateFraction(distance);
        takenTransportBox.setItems(transportList);
        insteadOfTransportBox.setItems(transportList);
        fillAll.setVisible(false);
    }

    @FXML
    public void switchFood() {
        screens.activityController()
            .getWindow().getScene().setRoot(screens.foodScene().getRoot());
    }

    @FXML
    public void switchSolar() {
        screens.activityController()
            .getWindow().getScene().setRoot(screens.solarPanelScene().getRoot());
    }

    @FXML
    public void switchRoom() {
        screens.activityController()
            .getWindow().getScene().setRoot(screens.roomScene().getRoot());
    }

    @FXML
    public void switchPlantTree() {
        screens.activityController()
            .getWindow().getScene().setRoot(screens.plantTreeScene().getRoot());
    }

}
