package gogreenclient.screens;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import gogreenclient.datamodel.FoodEmissionModel;
import gogreenclient.datamodel.UserCareer;
import gogreenclient.datamodel.UserCareerService;
import gogreenclient.screens.window.SceneController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import org.springframework.beans.factory.annotation.Autowired;


public class AddFoodController implements SceneController {

    @FXML
    private JFXTextField costTaken;
    @FXML
    private JFXTextField costInstead;
    @FXML
    private DatePicker date;
    @FXML
    private JFXComboBox takenMealBox;
    @FXML
    private JFXComboBox insteadOfMealBox;
    @FXML
    private Label fillAll;
    @FXML
    private Label total;
    @FXML
    private JFXCheckBox localProduct;

    //list for the tree view
    ObservableList<String> mealList = FXCollections
        .observableArrayList("Potato sandwich", "Beef sandwich");


    private ScreenConfiguration screens;

    @Autowired
    private FoodEmissionModel foodEmissionModel;

    @Autowired
    private UserCareerService userCareerService;

    private UserCareer career;


    public AddFoodController(ScreenConfiguration screens) {
        this.screens = screens;
    }



    /**
     * sets the combo box elements.
     */
    public void initialize() throws Exception {
        takenMealBox.setItems(mealList);
        insteadOfMealBox.setItems(mealList);
        // set the value for the text field displaying the total
//        total.setText(String.valueOf(userCareerService
//            .getCareer().getCo2saved()));
    }


    /**
     * method for submit button, which will send the data to the server.
     */
    @FXML
    public void submit() throws Exception {
        if (takenMealBox.getValue() == null || insteadOfMealBox.getValue() == null
            || date.getValue() == null || costTaken.getText().trim().isEmpty()
            || costInstead.getText().trim().isEmpty()) {
            fillAll.setVisible(true);
        } else {
            String eatenFood = takenMealBox.getValue().toString();
            String usualFood = insteadOfMealBox.getValue().toString();
            int eatenCost = Integer.parseInt(costTaken.getText());
            int usualCost = Integer.parseInt(costInstead.getText());
            int co2Saved = foodEmissionModel.compareFood(eatenFood,
                usualFood, eatenCost, usualCost);
            career = userCareerService.updateUserCareer(co2Saved);
            String totalSaved = String.valueOf(career.getCo2saved());
            fillAll.setVisible(false);
            total.setText(totalSaved);
            screens.submitMealDialog().showAndWait();
        }
    }

    /**
     * goes to transportation activities.
     * need a transportation scene.
     */
    @FXML
    public void switchTransport() {
        screens.activityController()
            .getWindow().getScene().setRoot(screens.transportScene().getRoot());
    }

    @FXML
    public void switchSolar() {
        screens.activityController()
            .getWindow().getScene().setRoot(screens.solarPanelScene().getRoot());
    }

    @FXML
    public void switchRoom() {
        screens.activityController()
            .getWindow().getScene().setRoot(screens.roomScene().getRoot());
    }

}
