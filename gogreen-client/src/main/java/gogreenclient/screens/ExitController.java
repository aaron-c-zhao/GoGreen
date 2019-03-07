package gogreenclient.screens;

import gogreenclient.screens.DialogPrototype.ConfirmDialogController;
import gogreenclient.screens.DialogPrototype.Dialog;
import javafx.application.Platform;
import org.springframework.beans.factory.annotation.Autowired;

public class ExitController implements ConfirmDialogController {

    @Autowired
    private ScreenConfiguration screens;
    private Dialog dialog;

    public ExitController(ScreenConfiguration screens) {
        this.screens = screens;
    }

    @Override
    public void setDialog(Dialog dialog) {
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
