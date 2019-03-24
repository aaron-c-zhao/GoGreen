package gogreenclient.screens;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import gogreenclient.screens.window.SceneController;
import javafx.fxml.FXML;

public class PlantTreeController implements SceneController {
    @FXML
    private JFXComboBox plantedTree;

    private ScreenConfiguration screens;

    public PlantTreeController(ScreenConfiguration screens) {
        this.screens = screens;
    }

    @FXML
    public void switchFood() {
        screens.activityController()
                .getWindow().getScene().setRoot(screens.foodScene().getRoot());
    }

    @FXML
    public void switchTransport() {
        screens.activityController()
                .getWindow().getScene().setRoot(screens.transportScene().getRoot());
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

}
