package gogreenclient.screens;

import gogreenclient.screens.window.ConfirmDialogController;
import gogreenclient.screens.window.Windows;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;

public class FriendDetailsController implements ConfirmDialogController {

    private Windows dialog;

    private ScreenConfiguration screens;

    @FXML
    private PieChart pieChart;

    @FXML
    private Label setFriendUser;

    @FXML
    private Label setTotalAct;

    @FXML
    private Label setTotalDays;

    @FXML
    private Label setTotalAchievements;

    public FriendDetailsController(ScreenConfiguration screens) {
        this.screens = screens;
    }

    @Override
    public void setWindow(Windows dialog) {
        this.dialog = dialog;
    }

    /**
     * initializes pieChart values.
     * the text fields are commented out.
     */
    public void initialize() {
        /*
        setFriendUser.setText("");
        setTotalAchievements.setText("");
        setTotalAct.setText("");
        setTotalDays.setText("");
        */
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

    @Override
    public void yes() {
        dialog.close();
    }

    @Override
    public void no() {
        dialog.close();
    }
}
