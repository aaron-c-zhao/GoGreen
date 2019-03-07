package gogreenclient.screens;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import gogreenclient.screens.Window.SceneController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;

public class FoodController implements SceneController {

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

    @FXML
    public JFXTextField cost;
    @FXML
    public DatePicker date;
    @FXML
    public JFXComboBox takenMeal_Box;
    @FXML
    public JFXComboBox insteadOfMeal_Box;
    //list for the tree view
    ObservableList<String> mealList = FXCollections.observableArrayList("Potato sandwich", "Beef sandwich");

    @FXML
    public Label fill_all;

    //sets the combo box elements
    @FXML
    public void initialize() {
        takenMeal_Box.setItems(mealList);
        insteadOfMeal_Box.setItems(mealList);
    }

    @FXML
    public void submit() {
        if (takenMeal_Box.getValue() == null || insteadOfMeal_Box.getValue() == null || date.getValue() == null || cost.getText().trim().isEmpty()) {
            fill_all.setVisible(true);
        } else {
            System.out.println(takenMeal_Box.getValue().toString());
            System.out.println(insteadOfMeal_Box.getValue().toString());
            System.out.println(cost.getText());
            System.out.println(date.getValue().toString());
            fill_all.setVisible(false);

        }
    }

    /**
     * goes back to food options
     */
    @FXML
    public void backToFood() {
        System.out.println("will have pop up saying don't you want switch ");

    }

}
