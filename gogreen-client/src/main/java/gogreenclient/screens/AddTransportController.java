package gogreenclient.screens;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import gogreenclient.screens.window.SceneController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;


public class AddTransportController implements SceneController {


    ObservableList<String> transportList = FXCollections
        .observableArrayList("walk", "bike", "train", "bus", "car", "motorcycle", "plane");

    @FXML
    private JFXComboBox takenTransportBox;

    @FXML
    private JFXComboBox insteadOfTransportBox;

    @FXML
    private JFXTextField distance;

    private ScreenConfiguration screens;

    public AddTransportController(ScreenConfiguration screens) {
        this.screens = screens;
    }

    /**
     * initializes the dropdown menus.
     * @throws Exception when incorrect text input.
     */
    public void initialize() throws Exception {
        validateInputDistance();
        takenTransportBox.setItems(transportList);
        insteadOfTransportBox.setItems(transportList);
    }

    @FXML
    public void switchFood() {
        screens.activityController()
            .getWindow().getScene().setRoot(screens.foodScene().getRoot());
    }

    @FXML
    public void switchSolar() {
        screens.activityController()
            .getWindow().getScene().setRoot(screens.addSolarPanelScene().getRoot());
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

    /**
     * Limiting the input of a text field to be only numbers.
     */
    public void validateInputDistance() {
        distance.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    distance.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }
}
