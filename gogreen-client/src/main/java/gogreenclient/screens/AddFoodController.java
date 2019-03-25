package gogreenclient.screens;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import gogreenclient.datamodel.FoodEmissionModel;
import gogreenclient.datamodel.Records;
import gogreenclient.datamodel.UserCareerService;
import gogreenclient.datamodel.UserInputValidator;
import gogreenclient.screens.window.SceneController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import org.springframework.beans.factory.annotation.Autowired;


public class AddFoodController implements SceneController {


    //list for the tree view
    ObservableList<String> mealList = FXCollections
        .observableArrayList("beans", "vegetables", "cheese",
            "chocolate", "fruit", "lentils", "milk", "nuts", "pannekoeken",
            "poffertjes", "potatoes", "rice", "stroopwafel", "tofu", "beef",
            "bitterballen", "chicken", "eggs", "kroket", "lamb",
            "pork", "tuna", "turkey");
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

    private ScreenConfiguration screens;

    @Autowired
    private FoodEmissionModel foodEmissionModel;

    @Autowired
    private UserCareerService userCareerService;

    @Autowired
    private UserInputValidator validator;

    private Records records;


    public AddFoodController(ScreenConfiguration screens) {
        this.screens = screens;
    }


    /**
     * sets the combo box elements.
     */
    public void initialize() {
        validator.validateFraction(costTaken);
        validator.validateFraction(costInstead);
        takenMealBox.setItems(mealList);
        insteadOfMealBox.setItems(mealList);
        fillAll.setVisible(false);
    }


    /**
     * method for submit button, which will send the data to the server.
     */
    @FXML
    public void submit() {
        
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

    @FXML
    public void switchPlantTree() {
        screens.activityController()
            .getWindow().getScene().setRoot(screens.plantTreeScene().getRoot());
    }


}
