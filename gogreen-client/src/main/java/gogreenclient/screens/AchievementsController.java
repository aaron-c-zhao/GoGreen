package gogreenclient.screens;

import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import gogreenclient.screens.window.SceneController;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.util.Callback;
import org.springframework.beans.factory.annotation.Autowired;

public class AchievementsController implements SceneController {

    @Autowired
    private ScreenConfiguration screens;

    @FXML
    private JFXTreeTableView<Achievements> treeView;


    public AchievementsController(ScreenConfiguration screens) {
        this.screens = screens;
    }


    public void switchStatistics() {
        screens.startScreen().getScene().setRoot(screens.statisticScene().getRoot());
    }

    /**
     * initialize the table view.
     */
    public void initialize() {
        JFXTreeTableColumn<Achievements, String> achName =
            new JFXTreeTableColumn<>("Achievements");
        achName.setPrefWidth(150);
        achName.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Achievements,
            String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Achievements,
                String> param) {
                return param.getValue().getValue().name;
            }
        });

        JFXTreeTableColumn<Achievements, String> achieved = new JFXTreeTableColumn<>("Achieved");
        achieved.setPrefWidth(90);
        achieved.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Achievements,
            String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Achievements,
                String> param) {
                return param.getValue().getValue().achieved;
            }
        });

        JFXTreeTableColumn<Achievements,
            String> description = new JFXTreeTableColumn<>("Description");
        description.setPrefWidth(800);
        description.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Achievements,
            String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Achievements,
                String> param) {
                return param.getValue().getValue().description;
            }
        });
        ObservableList<Achievements> achievements = FXCollections.observableArrayList();
        Achievements treeHugger = new Achievements("Tree Hugger",
            "Yes", "Saved more than 100 kg carbon dioxide");
        Achievements anarchoPrimitivist = new Achievements("Anarcho Primitivist",
            "Yes", "Saved more than 1 ton of carbon dioxide");
        Achievements celebi = new Achievements("Celebi",
            "No", "Saved more than 5 tons of carbon dioxide");
        Achievements stopCheating = new Achievements("Stop Cheating",
            "Yes",
            "Saved 20 tons of carbon dioxide. Or, more likely, you're just cheating.");
        Achievements sunAbsorber = new Achievements("Sun Absorber",
            "No", "Your solar panels have produced more than 10 kw");
        Achievements powerPlant = new Achievements("Power Plant",
            "No", "Your solar panels have produced more 100 kw");
        Achievements fiveBillionYears = new Achievements("Five Billion Years",
            "No", "Your solar panels have produced more than 1095 kw."
            +
            " You know the sun will die eventually, right?");
        Achievements thankUvMuch = new Achievements("Thank UV Much",
            "No", "Your solar panels have produced more than 5000 kw."
            +
            " Absorbed light is lost forever. Congratulations, darkness worshipper");
        Achievements vegetarian = new Achievements("Vegetarian",
            "No", "Your food choices have saved more 100 kg of carbon dioxide");
        Achievements vegan = new Achievements("Vegan",
            "No", "Your food choices have saved more 500 kg of carbon dioxide");
        Achievements photosynthesizer = new Achievements("Photosynthesizer",
            "No", "Your food choices have saved more 1 ton of carbon dioxide");
        Achievements pleaseEat = new Achievements("Please Eat",
            "No", "Your food choices have saved more 5 tons of carbon dioxide."
            +
            " Please make sure you're eating properly");
        Achievements dutch = new Achievements("Dutch",
            "No", "100 kg of carbon dioxide saved from transport choices,"
            +
            " isn't Holland great?");
        Achievements niceLegs = new Achievements("Nice Legs",
            "No", "500 kg of carbon dioxide saved from transport choices, good work out");
        Achievements teleporter = new Achievements("Teleporter",
            "No", "1 ton of carbon dioxide saved from transport choices");
        Achievements neverSkipLegDay = new Achievements("Never Skip Leg Day",
            "No", "5 tons of carbon dioxide saved from transport choices."
            +
            " Eliud Kipchoge wants to know your location");
        Achievements fatWallet = new Achievements("Fat Wallet",
            "No", "Saved 100 euros, get your wallet some hydroxycut");
        Achievements retirementFund = new Achievements("Retirement Fund",
            "No", "Saved 500 euros total. Open an account mate");
        Achievements justBuySomething = new Achievements("Just Buy Something",
            "No", "2000 euros saved, time for a trip to Amsterdam");
        Achievements nokwg29 = new Achievements("nokwg",
            "No", "10000 euros saved. Tinyurl");
        achievements.addAll(treeHugger, anarchoPrimitivist, celebi, stopCheating,
            sunAbsorber, powerPlant, fiveBillionYears, thankUvMuch, vegetarian,
            vegan, photosynthesizer, pleaseEat, dutch, niceLegs, teleporter,
            neverSkipLegDay, fatWallet, retirementFund, justBuySomething, nokwg29);

        final TreeItem<Achievements> root = new RecursiveTreeItem<Achievements>(achievements,
            RecursiveTreeObject::getChildren);
        treeView.getColumns().setAll(achName, achieved, description);
        treeView.setRoot(root);
        treeView.setShowRoot(false);
    }

    @FXML
    public void addActivity() {
        screens.activityScreen().show();
    }

    @FXML
    public void closeProgram() {
        screens.exitDialog().showAndWait();
    }


    class Achievements extends RecursiveTreeObject<Achievements> {
        StringProperty name;
        StringProperty achieved;
        StringProperty description;

        public Achievements(String name, String achieved, String description) {
            this.name = new SimpleStringProperty(name);
            this.achieved = new SimpleStringProperty(achieved);
            this.description = new SimpleStringProperty(description);
        }
    }
}