package gogreenclient.screens;

import gogreenclient.screens.window.WindowController;
import gogreenclient.screens.window.windows;
import javafx.scene.Scene;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;


/**
 * Main stage which contains the default.
 */
public class ActivityController implements WindowController {

    @Autowired
    private ScreenConfiguration screens;
    private windows window;
    @Autowired
    private HashMap<String, Scene> sceneMap;

    public ActivityController(ScreenConfiguration screens) {
        this.screens = screens;
    }

    @Override
    public void setWindow(windows window) {
        this.window = window;
        window.setScene(screens.foodScene().getScene());
    }


}
