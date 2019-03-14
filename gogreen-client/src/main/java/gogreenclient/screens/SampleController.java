package gogreenclient.screens;

import gogreenclient.screens.window.WindowController;
import gogreenclient.screens.window.Windows;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.springframework.beans.factory.annotation.Autowired;

public class SampleController implements WindowController {

    @FXML
    public Label total;

    @Autowired
    private ScreenConfiguration screens;
    private Windows dialog;

    public SampleController(ScreenConfiguration screens) {
        this.screens = screens;
    }

    public void initialize() {
        //TODO  set the value for the text field displaying the total
        total.setText("total");
    }

    @Override
    public void setWindow(Windows dialog) {
        this.dialog = dialog;
    }


    @FXML
    public void switchToFood() {
        dialog.close();
        screens.activityScreen().show();
    }

    //close the program + call the pop-up
    @FXML
    public void closeProgram() {
        screens.exitDialog().showAndWait();
    }
}
