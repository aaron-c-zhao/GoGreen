package gogreenclient.screens;

import gogreenclient.datamodel.Achievements;
import gogreenclient.datamodel.Records;
import gogreenclient.datamodel.UserCareerService;
import gogreenclient.screens.window.SceneController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import org.springframework.beans.factory.annotation.Autowired;

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

    private List<Achievements> achievementsList;


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
            statisticInitialize();
            userNameInitialize();
            pieChyartInitialize();
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        achievement.setText(getLastAchievements());
        totalAchievements.setText(getAchievementsAmount());
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
        int tree = 10;
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
        if(achievementsList != null)
            amount = achievementsList.size();
        return String.valueOf(amount);
    }

    @FXML
    public void closeProgram() {
        screens.exitDialog().showAndWait();
    }
}
