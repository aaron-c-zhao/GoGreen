package gogreenclient.screens;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import gogreenclient.screens.window.SceneController;
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
    public JFXTextField cost;
    @FXML
    public DatePicker date;
    @FXML
    public JFXComboBox takenMealBox;
    @FXML
    public JFXComboBox insteadOfMealBox;
    @FXML
    public Label fillAll;
    //list for the tree view
    ObservableList<String> mealList = FXCollections
        .observableArrayList("Potato sandwich", "Beef sandwich");
    @Autowired
    private ScreenConfiguration screens;
    @Autowired
    private HashMap<String, Scene> sceneMap;
    private Scene scene;

    public FoodController(ScreenConfiguration screens) {
        this.screens = screens;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
        sceneMap.put("food", scene);
    }

    //sets the combo box elements
    @FXML
    public void initialize() {
        takenMealBox.setItems(mealList);
        insteadOfMealBox.setItems(mealList);
    }

    /**
     * method for submit button, which will send the data to the server.
     */
    @FXML
    public void submit() {
        if (takenMealBox.getValue() == null || insteadOfMealBox.getValue() == null
            || date.getValue() == null || cost.getText().trim().isEmpty()) {
            fillAll.setVisible(true);
        } else {
            //TODO
            System.out.println(takenMealBox.getValue().toString());
            System.out.println(insteadOfMealBox.getValue().toString());
            System.out.println(cost.getText());
            System.out.println(date.getValue().toString());
            fillAll.setVisible(false);

        }
    }

    /**
     * goes back to food options.
     */
    @FXML
    public void backToFood() {
        System.out.println("will have pop up saying don't you want switch ");

    }

}
