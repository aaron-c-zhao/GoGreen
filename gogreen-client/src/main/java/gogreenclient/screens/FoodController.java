package gogreenclient.screens;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import gogreenclient.screens.window.SceneController;
import gogreenclient.screens.window.Windows;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;

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
    private HashMap<String, Scene> sceneMap;
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
        sceneMap.put("food", scene);
    }

    /**
     * sets the combo box elements.
     */
    public void initialize() {
        takenMealBox.setItems(mealList);
        insteadOfMealBox.setItems(mealList);
        // set the value for the text field displaying the total
        total.setText("total");
    }

    /**
     * method for submit button, which will send the data to the server.
     */
    @FXML
    public void submit() throws NoSuchFieldException {
        if (takenMealBox.getValue() == null || insteadOfMealBox.getValue() == null
            || date.getValue() == null || costTaken.getText().trim().isEmpty()
            || costInstead.getText().trim().isEmpty()) {
            fillAll.setVisible(true);
        } else {
            //TODO
            System.out.println(takenMealBox.getValue().toString());
            System.out.println(insteadOfMealBox.getValue().toString());
            System.out.println(costTaken.getText());
            System.out.println(costInstead.getText());
            System.out.println(date.getValue().toString());
            fillAll.setVisible(false);
            //  SubmitMealPopController.class.getDeclaredField("calc_use")
            //        .setText(takenMealBox.getValue().toString());
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
