package gogreenclient.screens;

import gogreenclient.screens.window.WindowController;
import gogreenclient.screens.window.Windows;
import javafx.scene.Scene;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * Main stage which contains the default.
 */
public class AddActivityController implements WindowController {

    private ScreenConfiguration screens;
    private Windows window;


    public AddActivityController(ScreenConfiguration screens) {
        this.screens = screens;
    }

    @Override
    public void setWindow(Windows window) {
        this.window = window;
        Scene activityScene = new Scene(screens.foodScene().getRoot());
        window.setScene(activityScene);
    }

    public Windows getWindow() {
        return window;
    }
}
