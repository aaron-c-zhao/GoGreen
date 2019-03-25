package gogreenclient.screens;

import com.jfoenix.controls.JFXTextField;
import gogreenclient.screens.window.SceneController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;


public class AddSolarPanelController implements SceneController {

    @FXML
    private JFXTextField sizeOfSolarPanel;
    @FXML
    private Label intialSize;
    @FXML
    private Label addDate;
    private ScreenConfiguration screens;

    public AddSolarPanelController(ScreenConfiguration screens) {
        this.screens = screens;
    }

    public void initialize() throws Exception {
        validateInputDistance();
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

    @FXML
    public void switchPlantTree() {
        screens.activityController()
            .getWindow().getScene().setRoot(screens.plantTreeScene().getRoot());
    }

    /**
     * Limiting the input of a text field to be only numbers.
     */
    public void validateInputDistance() {
        sizeOfSolarPanel.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    sizeOfSolarPanel.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }
}
