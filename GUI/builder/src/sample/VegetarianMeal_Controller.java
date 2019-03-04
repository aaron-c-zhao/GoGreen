package sample;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TreeItem;

import java.awt.event.MouseEvent;

public class VegetarianMeal_Controller {
    @FXML
    JFXTreeView<String> treeView;
    TreeItem<String> newActivities=new TreeItem<>("Add new activities");
    TreeItem<String> foodop=new TreeItem<>("Food options");
    TreeItem<String> vegetarian=new TreeItem<>("Vegetarian meal");
    TreeItem<String> option2=new TreeItem<>("Option2");
    TreeItem<String> transport=new TreeItem<>("Transport options:");
    TreeItem<String> takebike=new TreeItem<>("Take bike");
    TreeItem<String> option3=new TreeItem<>("Option3");
    TreeItem<String> home=new TreeItem<>("Home Options");
    TreeItem<String> solarPanel=new TreeItem<>("Use solar panels");
    TreeItem<String> option4=new TreeItem<>("Option4");
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
    public void initialize(){
      //  TreeItem<String> food=new TreeItem<>("Food options");
       // TreeItem<String> vegetarian=new TreeItem<>("Vegetarian meal");
        newActivities.getChildren().addAll(foodop,transport,home);
        foodop.getChildren().addAll(vegetarian,option2);
        transport.getChildren().addAll(takebike,option3);
        home.getChildren().addAll(solarPanel,option4);
        newActivities.setExpanded(true);
        foodop.setExpanded(true);
        transport.setExpanded(true);
        home.setExpanded(true);
        treeView.setRoot(newActivities);
        takenMeal_Box.setItems(mealList);
        insteadOfMeal_Box.setItems(mealList);
    }


    public void submit(){
        System.out.println(takenMeal_Box.getValue().toString());
        System.out.println(insteadOfMeal_Box.getValue().toString());
        System.out.println(cost.getText());
        System.out.println(date.getValue().toString());
        Main.window.setScene(Main.food);
    }
    public void backToFood(){
        System.out.println("will have pop up saying don't you want switch ");
        Main.window.setScene(Main.food);
    }
}

