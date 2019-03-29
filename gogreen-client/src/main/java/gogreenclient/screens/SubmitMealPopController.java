package gogreenclient.screens;

import gogreenclient.datamodel.FoodEmissionModel;
import gogreenclient.datamodel.UserCareerService;
import gogreenclient.screens.window.ConfirmDialogController;
import gogreenclient.screens.window.Windows;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.springframework.beans.factory.annotation.Autowired;

public class SubmitMealPopController implements ConfirmDialogController {

    @FXML
    Label calcUse;

    @FXML
    Label totalSaved;

    @Autowired
    private ScreenConfiguration screens;
    private Windows dialog;

    @Autowired
    private FoodEmissionModel foodEmissionModel;

    @Autowired
    private UserCareerService userCareerService;


    public SubmitMealPopController(ScreenConfiguration screens) {
        this.screens = screens;
    }

    /**
     * initialize this screen.
     */
    public void initialize() throws Exception {
        int co2Saved = foodEmissionModel.getChangedCO2();
        calcUse.setText(messager(co2Saved));
        totalSaved.setText(String
            .valueOf(Math.round(userCareerService.getCareer().getSavedCo2Total())));
    }

    private String messager(int co2Saved) {
        if (co2Saved > 0) {
            return "Good Job!" + co2Saved + " KG CO2 emission have been saved by you!";
        } else if (co2Saved == 0) {
            return "Hmmm, nothing happened.";
        } else {
            return "Oh, my friend! You just produced " + co2Saved * -1 + "more CO2.";
        }
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