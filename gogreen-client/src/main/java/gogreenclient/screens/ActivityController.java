package gogreenclient.screens;

import gogreenclient.screens.Window.WindowController;
import gogreenclient.screens.Window.Windows;
import javafx.scene.Scene;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;


/**
 * Main stage which contains the default
 */
public class ActivityController implements WindowController {

    @Autowired
    private ScreenConfiguration screens;
    private Windows window;
    @Autowired
    private HashMap<String, Scene> sceneMap;

    @Override
    public void setWindow(Windows window) {
        this.window = window;
        window.setScene(screens.foodScene().getScene());
    }

    public ActivityController(ScreenConfiguration screens) {
        this.screens = screens;
    }






}
