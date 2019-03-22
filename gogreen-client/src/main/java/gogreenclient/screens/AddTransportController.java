package gogreenclient.screens;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
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

    @FXML
    private JFXDatePicker date;



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
            .getWindow().getScene().setRoot(screens.solarPanelScene().getRoot());
    }

    @FXML
    public void switchRoom() {
        screens.activityController()
            .getWindow().getScene().setRoot(screens.roomScene().getRoot());
    }

}
