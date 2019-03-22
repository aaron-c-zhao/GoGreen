package gogreenclient.screens;

import gogreenclient.screens.window.WindowController;
import gogreenclient.screens.window.Windows;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import org.springframework.beans.factory.annotation.Autowired;

public class StartController implements WindowController {

    public Windows dialog;
    @Autowired
    private ScreenConfiguration screens;
    @FXML
    private PieChart pieChart;

    public StartController(ScreenConfiguration screens) {
        this.screens = screens;
    }

    @Override
    public void setWindow(Windows win) {
        this.dialog = dialog;
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
        // dialog.close();
        screens.addFoodDialog().show();
    }

    public void switchAchievements() {
        //dialog.close();
        screens.achievementsDialog().show();
    }

    @FXML
    public void closeProgram() {
        screens.exitDialog().showAndWait();
    }
}
