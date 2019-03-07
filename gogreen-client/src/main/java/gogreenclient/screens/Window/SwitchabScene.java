package gogreenclient.screens.Window;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;

public class SwitchabScene {

    private Scene scene;

    public SwitchabScene(final SceneController controller, URL fxml, String title, double width, double height) {
        FXMLLoader loader = new FXMLLoader(fxml);
        try {
            loader.setControllerFactory(new Callback<Class<?>, Object>() {
                @Override
                public Object call(Class<?> aClass) {
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

    public SwitchabScene(final SceneController controller, URL fxml, String title) {
        FXMLLoader loader = new FXMLLoader(fxml);
        try {
            loader.setControllerFactory(new Callback<Class<?>, Object>() {
                @Override
                public Object call(Class<?> aClass) {
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
