package gogreenclient.screens;

import gogreenclient.screens.window.ConfirmDialogController;
import gogreenclient.screens.window.Windows;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import org.springframework.beans.factory.annotation.Autowired;

public class SubmitMealPopController implements ConfirmDialogController {

    @FXML
    Label calcUse;

    @Autowired
    private ScreenConfiguration screens;
    private Windows dialog;

    public SubmitMealPopController(ScreenConfiguration screens) {
        this.screens = screens;
    }
    /* TODO
    public  void initialize() throws NoSuchFieldException {
    calcUse.setText((FoodController.class.getDeclaredField("takenMealBox").toString())+
            " - " + FoodController.class.getDeclaredField("insteadOfMealBox").toString());
    }*/

    @Override
    public void setWindow(Windows dialog) {
        this.dialog = dialog;
    }

    @Override
    public void yes() {
        //TODO
    }

    @Override
    public void no() {
        dialog.close();
    }
}