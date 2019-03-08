package gogreenclient.screens;

import gogreenclient.screens.window.WindowController;
import gogreenclient.screens.window.windows;
import javafx.fxml.FXML;
import org.springframework.beans.factory.annotation.Autowired;

public class SampleController implements WindowController {

    @Autowired
    private ScreenConfiguration screens;
    private windows dialog;

    public SampleController(ScreenConfiguration screens) {
        this.screens = screens;
    }

    @Override
    public void setWindow(windows dialog) {
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
