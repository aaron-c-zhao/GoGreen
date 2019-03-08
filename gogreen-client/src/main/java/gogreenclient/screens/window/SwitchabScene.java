package gogreenclient.screens.window;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;

public class SwitchabScene {

    private Scene scene;

    /**
     * The constructor that allows you to set the width and height of the scene that will
     * be shown on the stage.
     *
     * @param controller your scene controller.
     * @param fxml       the URI of the fxml file.
     * @param title      the title of your stage.
     * @param width      the width of your stage.
     * @param height     the height of your stage.
     */
    public SwitchabScene(final SceneController controller, URL fxml, String title,
                         double width, double height) {
        //TODO the title is not used
        FXMLLoader loader = new FXMLLoader(fxml);
        try {
            loader.setControllerFactory(new Callback<Class<?>, Object>() {
                @Override
                public Object call(Class<?> aclass) {
                    return controller;
                }
            });
            Parent parent = (Parent) loader.load();
            scene = new Scene(parent, width, height);
            controller.setScene(scene);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Another constructor without the ability of customizing the width and the height of the scene.
     *
     * @param controller your stage controller.
     * @param fxml       the URI of the fxml file.
     * @param title      the title of your stage.
     */
    public SwitchabScene(final SceneController controller, URL fxml, String title) {
        FXMLLoader loader = new FXMLLoader(fxml);
        try {
            loader.setControllerFactory(new Callback<Class<?>, Object>() {
                @Override
                public Object call(Class<?> aclass) {
                    return controller;
                }
            });
            Parent parent = (Parent) loader.load();
            scene = new Scene(parent);
            controller.setScene(scene);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Scene getScene() {
        return scene;
    }
}
