package gogreenclient.screens;

import gogreenclient.screens.window.SceneController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;

public class StatisticController implements SceneController {


    private ScreenConfiguration screens;

    @FXML
    private PieChart pieChart;

    public StatisticController(ScreenConfiguration screens) {
        this.screens = screens;
    }


    public StatisticController() {
    }

    /**
     * initialize pieChart values.
     */
    public void initialize() {
        ObservableList<PieChart.Data> pieChartData =
            FXCollections.observableArrayList(
                new PieChart.Data("Food", 18),
                new PieChart.Data("Transport", 34),
                new PieChart.Data("Solar panels", 30),
                new PieChart.Data("Lower temperature", 10),
                new PieChart.Data("Plant a tree", 8)
            );
        pieChart.setData(pieChartData);
    }

    public void addActivity() {
        screens.activityScreen().show();
    }

    public void switchAchievements() {
        screens.startScreen().getScene().setRoot(screens.achievementsScene().getRoot());
    }


    @FXML
    public void closeProgram() {
        screens.exitDialog().showAndWait();
    }
}
