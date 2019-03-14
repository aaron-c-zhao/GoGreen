package gogreenclient.screens;

import gogreenclient.screens.window.WindowController;
import gogreenclient.screens.window.Windows;
import javafx.fxml.FXML;
import org.springframework.beans.factory.annotation.Autowired;

public class SampleController implements WindowController {

    @Autowired
    private ScreenConfiguration screens;
    private Windows dialog;

    public SampleController(ScreenConfiguration screens) {
        this.screens = screens;
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
