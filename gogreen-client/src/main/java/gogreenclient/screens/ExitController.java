package gogreenclient.screens;

import gogreenclient.screens.window.ConfirmDialogController;
import gogreenclient.screens.window.windows;
import javafx.application.Platform;
import org.springframework.beans.factory.annotation.Autowired;

public class ExitController implements ConfirmDialogController {

    @Autowired
    private ScreenConfiguration screens;
    private windows dialog;

    public ExitController(ScreenConfiguration screens) {
        this.screens = screens;
    }

    @Override
    public void setWindow(windows dialog) {
        this.dialog = dialog;
    }

    @Override
    public void yes() {
        dialog.close();
        Platform.exit();
    }

    @Override
    public void no() {
        dialog.close();
    }
}
