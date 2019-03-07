package gogreenclient.screens.DialogPrototype;

import javafx.stage.Stage;
import javafx.stage.StageStyle;

public abstract class Dialog extends Stage {
    private StageStyle stageStyle;

    public Dialog(StageStyle style) {
        super(style);
    }
}
