package gogreenclient.screens;

import com.jfoenix.controls.JFXTextField;
import gogreenclient.screens.window.SceneController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;

public class AddRoomController implements SceneController {

    private ScreenConfiguration screens;

    @FXML
    private JFXTextField minutes;

    @FXML
    private JFXTextField tempDiff;

    @FXML
    private JFXTextField roomArea;

    public AddRoomController(ScreenConfiguration screens) {
        this.screens = screens;
    }

    public void initialize() throws Exception{
        validateMinutes();
        validateRoomArea();
        validateTempDiff();
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
    public void switchPlantTree() {
        screens.activityController()
                .getWindow().getScene().setRoot(screens.plantTreeScene().getRoot());
    }

    public void validateMinutes(){
        minutes.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    minutes.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }
    public void validateTempDiff(){
        tempDiff.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    tempDiff.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }
    public void validateRoomArea(){
        roomArea.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    roomArea.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }
}
