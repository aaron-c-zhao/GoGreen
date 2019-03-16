package gogreenclient.screens;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import gogreenclient.config.AppConfig;
import gogreenclient.datamodel.FoodEmissionModel;
import gogreenclient.datamodel.UserCareer;
import gogreenclient.screens.window.SceneController;
import gogreenclient.screens.window.Windows;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import org.springframework.beans.factory.annotation.Autowired;


public class FoodController implements SceneController {

    @FXML
    public JFXTextField costTaken;
    @FXML
    public JFXTextField costInstead;
    @FXML
    public DatePicker date;
    @FXML
    public JFXComboBox takenMealBox;
    @FXML
    public JFXComboBox insteadOfMealBox;
    @FXML
    public Label fillAll;
    @FXML
    public Label total;

    //list for the tree view
    ObservableList<String> mealList = FXCollections
        .observableArrayList("Potato sandwich", "Beef sandwich");
    @Autowired
    private ScreenConfiguration screens;

    @Autowired
    private FoodEmissionModel foodEmissionModel;

    @Autowired
    private AppConfig appConfig;

    private UserCareer career;

    private Scene scene;

    private Windows dialog;

    public FoodController(ScreenConfiguration screens) {
        this.screens = screens;
    }

    public void setWindow(Windows dialog) {
        this.dialog = dialog;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
        //        sceneMap.put("food", scene);
    }

    /**
     * sets the combo box elements.
     */
    public void initialize() {
        takenMealBox.setItems(mealList);
        insteadOfMealBox.setItems(mealList);
        // set the value for the text field displaying the total
        total.setText(String.valueOf(foodEmissionModel
            .getCareer("zhao").getCo2saved()));
    }


    /**
     * method for submit button, which will send the data to the server.
     */
    @FXML
    public void submit() {
        if (takenMealBox.getValue() == null || insteadOfMealBox.getValue() == null
            || date.getValue() == null || costTaken.getText().trim().isEmpty()
            || costInstead.getText().trim().isEmpty()) {
            fillAll.setVisible(true);
        } else {
            String eatenFood = takenMealBox.getValue().toString();
            String usualFood = insteadOfMealBox.getValue().toString();
            int eatenCost = Integer.parseInt(costTaken.getText());
            int usualCost = Integer.parseInt(costInstead.getText());
            String co2Saved = foodEmissionModel.compareFood(eatenFood,
                usualFood, eatenCost, usualCost);
            career = foodEmissionModel.updateUserCareer("zhao");
            String totalSaved = String.valueOf(career.getCo2saved());
            appConfig.setMailMan(new Co2SavedMailMan(co2Saved, career));
            fillAll.setVisible(false);
            total.setText(totalSaved);
            screens.submitMealDialog().showAndWait();

        }
    }

    /**
     * goes back to food options.
     */
    @FXML
    public void backToFood() {
        // dialog.close();
        // screens.activityScreen().show();
    }

}
