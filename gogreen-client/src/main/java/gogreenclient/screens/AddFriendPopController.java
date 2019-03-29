package gogreenclient.screens;

import com.jfoenix.controls.JFXTextField;
import gogreenclient.screens.window.ConfirmDialogController;
import gogreenclient.screens.window.Windows;
import javafx.fxml.FXML;

public class AddFriendPopController implements ConfirmDialogController {


    @FXML
    private JFXTextField friendUser;

    private ScreenConfiguration screens;

    private Windows dialog;

    public AddFriendPopController(ScreenConfiguration screens) {
        this.screens = screens;
    }

    @Override
    public void setWindow(Windows dialog) {
        this.dialog = dialog;
    }

    /**
     * the function for the submit button.
     */
    @Override
    public void yes() {
        friendUser.getText();
    }

    @Override
    public void no() {
        dialog.close();
    }
}
