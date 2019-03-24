package gogreenclient.screens;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import gogreenclient.screens.window.SceneController;
import javafx.fxml.FXML;


public class AddTransportController implements SceneController {


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

}
