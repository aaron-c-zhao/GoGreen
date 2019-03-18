package gogreenclient.screens;

import gogreenclient.datamodel.FoodEmissionModel;
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

    @Autowired
    private FoodEmissionModel foodEmissionModel;


    public SubmitMealPopController(ScreenConfiguration screens) {
        this.screens = screens;
    }

    /**
     * initialize this screen.
     */
    public void initialize() {
        String co2Saved = String.valueOf(foodEmissionModel.getChangedCO2());
        calcUse.setText(co2Saved);

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