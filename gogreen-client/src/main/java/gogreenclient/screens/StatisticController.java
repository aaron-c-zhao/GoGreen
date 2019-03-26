package gogreenclient.screens;

import gogreenclient.datamodel.Achievements;
import gogreenclient.datamodel.InsertHistoryCo2;
import gogreenclient.datamodel.Records;
import gogreenclient.datamodel.UserCareerService;
import gogreenclient.screens.window.SceneController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;


public class StatisticController implements SceneController {


    private ScreenConfiguration screens;

    @Autowired
    private UserCareerService userCareerService;

    private Records records;

    @FXML
    private PieChart pieChart;

    @FXML
    private Label userName;

    @FXML
    private Label totalSaved;

    @FXML
    private Label achievement;

    @FXML
    private Label totalAchievements;

    @FXML
    private Label recentActivityOne;

    @FXML
    private Label recentActivityTwo;

    @FXML
    private Label firstActivityDate;

    @FXML
    private Label firstActivityAmount;

    @FXML
    private Label secondActivityDate;

    @FXML
    private Label secondActivityAmount;

    private List<Achievements> achievementsList;

    private List<InsertHistoryCo2> insertHistoryList;

    private ArrayList<Label> labelList;


    public StatisticController(ScreenConfiguration screens) {
        this.screens = screens;
    }


    public StatisticController() {
    }

    /**
     * initialize pieChart values.
     */
    public void initialize() {
        try {
            records = userCareerService.getCareer();
            achievementsList = userCareerService.getAchievements();
            insertHistoryList = userCareerService.getRecentTwoInsertHistory();
            statisticInitialize();
            userNameInitialize();
            pieChyartInitialize();
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        achievement.setText(getLastAchievements());
        totalAchievements.setText(getAchievementsAmount());
        recentActivityInit();
    }

    public void addActivity() {
        screens.activityScreen().show();
    }

    public void switchAchievements() {
        screens.startScreen().getScene().setRoot(screens.achievementsScene().getRoot());
    }

    //TODO achievement, insert history
    private void statisticInitialize() {
        totalSaved.setText(String.valueOf(Math.round(records.getSavedCo2Total())));
    }

    private void userNameInitialize() {
        String username = userCareerService.getUsername();
        userName.setText(username);
    }

    private void pieChyartInitialize() {
        int food = Math.round(records.getSavedCo2Food());
        int transport = Math.round(records.getSavedCo2Transport());
        int solarPaner = Math.round(records.getSavedCo2Solarpanels());
        int temperature = Math.round(records.getSavedCo2Energy());
        int tree = 0;
        if (food == 0 && transport == 0 && solarPaner == 0 && temperature == 0 && tree == 0) {
            food = transport = solarPaner = temperature = tree = 10;
        }
        ObservableList<PieChart.Data> pieChartData =
            FXCollections.observableArrayList(
                new PieChart.Data("Food", food),
                new PieChart.Data("Transport", transport),
                new PieChart.Data("Solar panels", solarPaner),
                new PieChart.Data("Lower temperature", temperature),
                new PieChart.Data("Plant a tree", tree)
            );
        pieChart.setData(pieChartData);
    }

    private String getLastAchievements() {
        String result = null;
        if (achievementsList == null) {
            result = "Blue";
        } else {
            result = achievementsList.get(0).getAchievement();
        }
        return result;
    }

    private String getAchievementsAmount() {
        int amount = 0;
        if (achievementsList != null) {
            amount = achievementsList.size();
        }
        return String.valueOf(amount);
    }

    private void recentActivityInit() {
        if (insertHistoryList.size() == 0) {
            recentActivityOne.setText("");
            recentActivityTwo.setText("");
            firstActivityDate.setText("");
            firstActivityAmount.setText("");
            secondActivityDate.setText("");
            secondActivityAmount.setText("");
        }

        if (insertHistoryList.size() >= 1) {
            recentActivityOne.setText(insertHistoryList.get(0).activityName());
            firstActivityDate.setText(insertHistoryList.get(0).getInsertDate().toString());
            firstActivityAmount.setText(String
                .valueOf(Math.round(insertHistoryList.get(0).getCo2Saved())));
        }
        if (insertHistoryList.size() == 2) {
            recentActivityTwo.setText(insertHistoryList.get(1).activityName());
            secondActivityDate.setText(insertHistoryList.get(1).getInsertDate().toString());
            secondActivityAmount.setText(String
                .valueOf(Math.round(insertHistoryList.get(1).getCo2Saved())));
        }

    }

    @FXML
    public void closeProgram() {
        screens.exitDialog().showAndWait();
    }
}
