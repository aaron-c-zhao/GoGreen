package gogreenclient.screens.DialogPrototype;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;

/**
 * Every dialog is a individual stage. This class is the factory class of the dialogs.
 */
public class FXMLDialog extends Dialog {
    public FXMLDialog(final DialogController controller, URL fxml, Window owner, StageStyle style) {
        super(style);
        initOwner(owner);
        initModality(Modality.WINDOW_MODAL);
        FXMLLoader loader = new FXMLLoader(fxml);
        try {
            loader.setControllerFactory(new Callback<Class<?>, Object>() {
                @Override
                public Object call(Class<?> aClass) {
                    return controller;
                }
            });
            controller.setDialog(this);
            setScene(new Scene((Parent) loader.load()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
