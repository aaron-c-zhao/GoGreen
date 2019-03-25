package gogreenclient.screens;

import com.jfoenix.controls.JFXTextField;
import gogreenclient.datamodel.UserInputValidator;
import gogreenclient.screens.window.SceneController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.springframework.beans.factory.annotation.Autowired;

public class AddRoomController implements SceneController {

    private ScreenConfiguration screens;

    @FXML
    private Label fillAll;

    @FXML
    private JFXTextField minutes;

    @FXML
    private JFXTextField tempDiff;

    @FXML
    private JFXTextField roomArea;

    @Autowired
    private UserInputValidator validator;

    public AddRoomController(ScreenConfiguration screens) {
        this.screens = screens;
    }


    /**
     * initialing the validations.
     *
     * @throws Exception exception if not correct.
     */
    public void initialize() {
        validator.validateFraction(tempDiff);
        validator.validateFraction(roomArea);
        validator.validateInteger(minutes);
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
    public void switchSolar() {
        screens.activityController()
            .getWindow().getScene().setRoot(screens.solarPanelScene().getRoot());
    }

    @FXML
    public void switchPlantTree() {
        screens.activityController()
            .getWindow().getScene().setRoot(screens.plantTreeScene().getRoot());
    }

    /**
     * Limiting the input of a text field to be only numbers.
     */

}
