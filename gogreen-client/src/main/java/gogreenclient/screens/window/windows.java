package gogreenclient.screens.window;

import javafx.stage.Stage;
import javafx.stage.StageStyle;

public abstract class windows extends Stage {
    private StageStyle stageStyle;

    public windows(StageStyle style) {
        super(style);
    }
}
