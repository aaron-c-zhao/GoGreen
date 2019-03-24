package gogreenclient.screens;

import com.jfoenix.controls.JFXTextField;
import gogreenclient.screens.window.SceneController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;


public class AddSolarPanelController implements SceneController {

    @FXML
    private JFXTextField sizeOfSolarPanel;

    @FXML
    private Label intialSize;

    @FXML
    private Label fillAll;

    private ScreenConfiguration screens;

    public AddSolarPanelController(ScreenConfiguration screens) {
        this.screens = screens;
    }

    public void initialize() {
        fillAll.setVisible(false);
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
    public void switchRoom() {
        screens.activityController()
            .getWindow().getScene().setRoot(screens.roomScene().getRoot());
    }

}
