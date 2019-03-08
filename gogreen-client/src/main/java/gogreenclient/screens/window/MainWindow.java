package gogreenclient.screens.window;

import javafx.stage.Modality;
import javafx.stage.StageStyle;
import javafx.stage.Window;

public class MainWindow extends gogreenclient.screens.window.windows {
    /**
     * The main window of the application, which is able to switch between different scenes.
     *
     * @param controller the stage controller.
     * @param owner      the stage owner.
     * @param style      the stage style.
     */
    public MainWindow(final WindowController controller, Window owner, StageStyle style) {
        super(style);
        initOwner(owner);
        initModality(Modality.WINDOW_MODAL);
        controller.setWindow(this);
    }
}
