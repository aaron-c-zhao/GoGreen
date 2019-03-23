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
        achName.setPrefWidth(100);
        achName.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Achievements,
            String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Achievements,
                String> param) {
                return param.getValue().getValue().name;
            }
        });

        JFXTreeTableColumn<Achievements, String> achieved = new JFXTreeTableColumn<>("Achieved");
        achieved.setPrefWidth(60);
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
        description.setPrefWidth(275);
        description.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Achievements,
            String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Achievements,
                String> param) {
                return param.getValue().getValue().description;
            }
        });
        ObservableList<Achievements> achievements = FXCollections.observableArrayList();
        Achievements achievements1 = new Achievements("Achievement1",
            "Yes", "Achieved as you ....");
        Achievements communist = new Achievements("Communist",
            "Yes", "Achieved as you have bought red shirt");
        Achievements achievements2 = new Achievements("Achievement2",
            "No", "Achieved as you ....");
        Achievements thewalking = new Achievements("The walking train",
            "Yes", "Achieved as you ....");
        Achievements achievements3 = new Achievements("Achievement3",
            "No", "Achieved as you ....");
        achievements.addAll(achievements1, communist,
            achievements2, thewalking, achievements3);

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