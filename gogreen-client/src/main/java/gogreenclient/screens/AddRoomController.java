package gogreenclient.screens;

import gogreenclient.screens.window.SceneController;
import javafx.fxml.FXML;

public class AddRoomController implements SceneController {

    private ScreenConfiguration screens;

    public AddRoomController(ScreenConfiguration screens) {
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
    public void switchPlantTree() {
        screens.activityController()
                .getWindow().getScene().setRoot(screens.plantTreeScene().getRoot());
    }

}
