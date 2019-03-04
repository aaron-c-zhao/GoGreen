package sample;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

public class Vegetarian_Meal_Controller {
    @FXML
    public JFXTextField cost;
    @FXML
    public DatePicker date;
    @FXML
    public JFXComboBox takenMeal_Box;
    @FXML
    public JFXComboBox insteadOfMeal_Box;
    //list for the tree view
    ObservableList<String> mealList= FXCollections.observableArrayList("Potato sandwich","Beef sandwich");

    @FXML
    public Label fill_all;

    //sets the combo box elements
    @FXML
    public void initialize(){
        takenMeal_Box.setItems(mealList);
        insteadOfMeal_Box.setItems(mealList);
    }
    /**
     * when button submit is clicked checks whether some of the fields are not field.
     * @false prints to the console their values and goes back
     * @true set to visible the label saying that the fields are not filled
     */
    public void submit(){
        if(takenMeal_Box.getValue()==null||insteadOfMeal_Box.getValue()==null||date.getValue()==null||cost.getText().trim().isEmpty()){fill_all.setVisible(true);}
        else{System.out.println(takenMeal_Box.getValue().toString());
        System.out.println(insteadOfMeal_Box.getValue().toString());
        System.out.println(cost.getText());
        System.out.println(date.getValue().toString());
        fill_all.setVisible(false);
        Main.window.setScene(Main.food);
    }}

    /**
     * goes back to food options
     */
    public void backToFood(){
        System.out.println("will have pop up saying don't you want switch ");
        Main.window.setScene(Main.food);
    }
}
