package gogreenclient.screens.Window;

import javafx.stage.Modality;
import javafx.stage.StageStyle;
import javafx.stage.Window;

public class MainWindow extends Windows{

    public MainWindow(final WindowController controller, Window owner, StageStyle style) {
        super(style);
        initOwner(owner);
        initModality(Modality.WINDOW_MODAL);
        controller.setWindow(this);
    }
}
