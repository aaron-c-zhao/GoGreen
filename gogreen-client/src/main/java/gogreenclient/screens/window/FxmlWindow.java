package gogreenclient.screens.window;

import gogreenclient.screens.ScreenConfiguration;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import javafx.util.Callback;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.net.URL;

;

/**
 * Every dialog is a individual stage. This class is the factory class of the dialogs.
 */
public class FxmlWindow extends gogreenclient.screens.window.Windows {

    @Autowired
    private ScreenConfiguration screens;

    /**
     * The model of all the stages that will load a fxml file and will not change it's
     * scene or root of the scene.
     *
     * @param controller your stage controller.
     * @param fxml       the URI of the fxml file.
     * @param owner      the owner of the stage.
     * @param style      the style of the stage.
     */
    public FxmlWindow(final WindowController controller, URL fxml, Window owner, StageStyle style) {
        super(style);
        initOwner(owner);
        initModality(Modality.WINDOW_MODAL);
        FXMLLoader loader = new FXMLLoader(fxml);
        try {
            loader.setControllerFactory(new Callback<Class<?>, Object>() {
                @Override
                public Object call(Class<?> aclass) {
                    return controller;
                }
            });
            controller.setWindow(this);
            Scene scene = new Scene((Parent) loader.load());
            scene.getStylesheets().add("/static/hover.css");
            setScene(scene);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        setOnCloseRequest(e -> {
            e.consume();
            screens.exitDialog().showAndWait();
        });
    }

}
