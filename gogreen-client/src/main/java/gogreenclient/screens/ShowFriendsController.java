package gogreenclient.screens;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import gogreenclient.screens.window.SceneController;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableCell;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.paint.Color;
import javafx.util.Callback;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.function.Predicate;

public class ShowFriendsController implements SceneController {

    @Autowired
    private ScreenConfiguration screens;

    @FXML
    private JFXTreeTableView<Friend> treeView;

    @FXML
    private JFXTextField input;

    public ShowFriendsController(ScreenConfiguration screens) {
        this.screens = screens;
    }


    public void switchStatistics() {
        screens.startScreen().getScene().setRoot(screens.statisticScene().getRoot());
    }

    /**
     * initialize the table view.
     */
    public void initialize() {
        JFXTreeTableColumn<Friend, String> friendName =
            new JFXTreeTableColumn<>("Friend");
        friendName.setPrefWidth(213);
        friendName.setStyle( "-fx-alignment: CENTER;");
        friendName.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Friend,
            String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Friend,
                String> param) {
                return param.getValue().getValue().name;
            }
        });

        JFXTreeTableColumn<Friend, String> total = new JFXTreeTableColumn<>("Total saved");
        total.setPrefWidth(100);
        total.setStyle( "-fx-alignment: CENTER;");
        total.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Friend,
            String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Friend,
                String> param) {
                return param.getValue().getValue().totalEmissions;
            }
        });

        JFXTreeTableColumn<Friend, String> settingsColumn =
            new JFXTreeTableColumn<>("Details");
        settingsColumn.setPrefWidth(120);
        Callback<TreeTableColumn<Friend, String>,
            TreeTableCell<Friend, String>> cellFactory
            = //
            new Callback<TreeTableColumn<Friend, String>,
                TreeTableCell<Friend, String>>() {
                @Override
                public TreeTableCell call(final TreeTableColumn<Friend,
                    String> param) {
                    final TreeTableCell<Friend, String> cell = new TreeTableCell<Friend, String>() {

                        final JFXButton btn = new JFXButton("Show details");

                        @Override
                        public void updateItem(String item, boolean empty) {
                            super.updateItem(item, empty);
                            if (empty) {
                                setGraphic(null);
                                setText(null);
                            } else {
                                btn.setButtonType(JFXButton.ButtonType.RAISED);
                                btn.setRipplerFill(Color.valueOf("#87ceeb"));
                                btn.setStyle("-fx-background-color:  #00AB66;"
                                    + " -fx-text-fill: white; ");
                                btn.setAlignment(Pos.BASELINE_CENTER);
                                btn.setPrefWidth(120);

                                btn.setOnAction(event -> {
                                    screens.friendDetailsDialog().show();
                                });
                                setGraphic(btn);
                                setText(null);
                            }
                        }
                    };
                    return cell;
                }
            };
        settingsColumn.setCellFactory(cellFactory);


        ObservableList<Friend> friends = FXCollections.observableArrayList();
        Friend treeHugger = new Friend("Tree Hugger",
            "90");
        Friend friend1 = new Friend("Tree Hugger frined",
            "800");
        Friend friend22 = new Friend("0 Hugger 0 resp",
            "over 90");
        friends.addAll(treeHugger,friend1,friend22);

        final TreeItem<Friend> root = new RecursiveTreeItem<Friend>(friends,
            RecursiveTreeObject::getChildren);
        treeView.getColumns().addAll(friendName, total, settingsColumn);
        treeView.setRoot(root);
        treeView.setShowRoot(false);

        input.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue,
                                String s1, String t1) {
                treeView.setPredicate(new Predicate<TreeItem<Friend>>() {
                    @Override
                    public boolean test(TreeItem<Friend> friend) {
                        Boolean flag = friend.getValue().name.getValue().contains(t1);
                        return flag;
                    }
                });
            }
        });
    }

    @FXML
    public void addActivity() {
        screens.activityScreen().show();
    }

    @FXML
    public void closeProgram() {
        screens.exitDialog().showAndWait();
    }

    @FXML
    public void switchAchievements() {
        screens.startScreen().getScene().setRoot(screens.achievementsScene().getRoot());
    }

    class Friend extends RecursiveTreeObject<Friend> {
        StringProperty name;
        StringProperty totalEmissions;

        public Friend(String name, String totalEmissions) {
            this.name = new SimpleStringProperty(name);
            this.totalEmissions = new SimpleStringProperty(totalEmissions);
        }

    }

}