package gogreenclient.screens.window;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;

public class SwitchabScene {

    private HBox root;


    /**
     * Another constructor without the ability of customizing the width and the height of the scene.
     *
     * @param fxml the URI of the fxml file.
     */
    public SwitchabScene(final SceneController controller, URL fxml) {
        FXMLLoader loader = new FXMLLoader(fxml);
        try {
            loader.setControllerFactory(new Callback<Class<?>, Object>() {
                @Override
                public Object call(Class<?> aclass) {
                    return controller;
                }
            });
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Pane getRoot() {
        return root;
    }
}
