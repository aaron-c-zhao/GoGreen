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


    public void initialize() {
        String result = (screens.submitMealDialog().getInformation() == null) ? "null" :
            screens.submitMealDialog().getInformation();
        calcUse.setText(result + "KG CO2");
    }

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