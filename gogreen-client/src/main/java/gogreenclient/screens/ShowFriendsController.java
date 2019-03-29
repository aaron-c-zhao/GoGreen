package gogreenclient.screens;

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
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
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


    /**
     * initialize the table view.
     */
    public void initialize() {
        JFXTreeTableColumn<Friend, String> friendName =
            new JFXTreeTableColumn<>("Friend");
        friendName.setPrefWidth(288);
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
        total.setPrefWidth(160);
        total.setStyle( "-fx-alignment: CENTER;");
        total.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Friend,
            String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Friend,
                String> param) {
                return param.getValue().getValue().totalEmissions;
            }
        });

        JFXTreeTableColumn<Friend, String> rankCol = new JFXTreeTableColumn<>("Rank");
        rankCol.setPrefWidth(150);
        rankCol.setStyle( "-fx-alignment: CENTER;");
        rankCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Friend,
            String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Friend,
                String> param) {
                return param.getValue().getValue().rank;
            }
        });
        /*
        JFXTreeTableColumn<Friend, String> settingsColumn =
            new JFXTreeTableColumn<>("Details");
        settingsColumn.setPrefWidth(150);
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
                                btn.setPrefWidth(150);

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
        settingsColumn.setCellFactory(cellFactory);*/


        ObservableList<Friend> friends = FXCollections.observableArrayList();
        Friend treeHugger = new Friend("Tree Hugger",
            "900","1");
        Friend friend1 = new Friend("Tree Hugger frined",
            "800", "2");
        Friend friend22 = new Friend("0 Hugger 0 resp",
            "90" , "3");
        Friend friend23 = new Friend("2 Hugger 1 resp",
            "80" , "4");
        friends.addAll(treeHugger,friend1,friend22,friend23);

        final TreeItem<Friend> root = new RecursiveTreeItem<Friend>(friends,
            RecursiveTreeObject::getChildren);
        treeView.getColumns().addAll(friendName, total, rankCol);
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
    public void switchAddFriend() {
        screens.addFriendDialog().show();
    }

    @FXML
    public void closeProgram() {
        screens.exitDialog().showAndWait();
    }

    public void switchAchievements() {
        screens.startScreen().getScene().setRoot(screens.achievementsScene().getRoot());
    }

    public void switchStatistics() {
        screens.startScreen().getScene().setRoot(screens.statisticScene().getRoot());
    }

    class Friend extends RecursiveTreeObject<Friend> {
        StringProperty name;
        StringProperty totalEmissions;
        StringProperty rank;

        public Friend(String name, String totalEmissions, String rank) {
            this.name = new SimpleStringProperty(name);
            this.totalEmissions = new SimpleStringProperty(totalEmissions);
            this.rank = new SimpleStringProperty(rank);
        }

    }

}