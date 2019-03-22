package gogreenclient.screens;

import gogreenclient.screens.window.ConfirmDialogController;
import gogreenclient.screens.window.Windows;
import org.springframework.beans.factory.annotation.Autowired;

public class AddFoodController implements ConfirmDialogController {

    @Autowired
    private ScreenConfiguration screens;
    private Windows dialog;

    public AddFoodController(ScreenConfiguration screens) {
        this.screens = screens;
    }

    @Override
    public void yes() {

    }

    @Override
    public void no() {

    }

    public void switchFood() {
        dialog.close();
        screens.addFoodDialog().show();
    }

    public void switchTransport() {
        dialog.close();
        screens.addTransportDialog().show();
    }

    public void switchRoom() {
        dialog.close();
        screens.addRoomDialog().show();
    }

    public void switchSolar() {
        dialog.close();
        screens.addSolarDialog().show();
    }

    @Override
    public void setWindow(Windows dialog) {
        this.dialog = dialog;
    }
}
