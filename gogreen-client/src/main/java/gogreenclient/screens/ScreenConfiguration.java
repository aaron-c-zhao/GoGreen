package gogreenclient.screens;

import gogreenclient.screens.window.ConfirmDialog;
import gogreenclient.screens.window.FxmlWindow;
import gogreenclient.screens.window.MainWindow;
import gogreenclient.screens.window.SceneController;
import gogreenclient.screens.window.SwitchabScene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

/**
 * Configurations of all the GUI relative elements, including stages, scenes, and controllers. All
 * of these elements will be a Bean and can be used by dependency injection.
 *
 * <p>If you want to add a stage or scene please add it here.
 */
@Configuration
@Lazy
public class ScreenConfiguration {

    private Stage primaryStage;

    /**
     * Change the scene that shown on the primary stage.
     *
     * @param scene the scene to be shown on the primary stage.
     */
    public void showScene(SwitchabScene scene) {
    }


    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    //-----------------------------------------stages----------------------------------------------

    /**
     * Login window which is a individual stage.
     *
     * @return a singleton instance of LoginDialog.
     */
    @Bean
    public FxmlWindow loginDialog() {
        return new FxmlWindow(loginController(), getClass().getResource("/views/Log_in.fxml"),
            primaryStage, StageStyle.DECORATED);
    }

    @Bean
    LoginController loginController() {
        return new LoginController(this);
    }

    @Bean
    CreateAccountController createAccountController() {
        return new CreateAccountController(this);
    }

    @Bean
    public FxmlWindow createAccountDialog() {
        return new FxmlWindow(createAccountController(), getClass()
            .getResource("/views/Create_account.fxml"), primaryStage, StageStyle.DECORATED);
    }

    /**
     * A pop up exit window which is a individual stage.
     *
     * @return a singleton instance of ExitDialog which is a confirmDialog.
     */
    @Bean
    public ConfirmDialog exitDialog() {
        return new ConfirmDialog(exitController(), getClass()
            .getResource("/views/ExitConfirmDialog.fxml"), primaryStage, StageStyle.DECORATED);
    }

    @Bean
    ExitController exitController() {
        return new ExitController(this);
    }

    /**
     * A pop up submit window on which will show how much CO2 has been saved by a particular
     * activity of the user and how much CO2 has been saved in total by that user.
     *
     * @return a singleton instance of submitDialog which is a confirmDialog.
     */
    @Bean
    public ConfirmDialog submitMealDialog() {
        return new ConfirmDialog(submitMealController(), getClass()
            .getResource("/views/SubmitMeal_Popup.fxml"), primaryStage, StageStyle.DECORATED);
    }

    @Bean
    SubmitMealPopController submitMealController() {
        return new SubmitMealPopController(this);
    }

    @Bean
    public MainWindow startScreen() {
        return new MainWindow(startController(), primaryStage, StageStyle.DECORATED, false);
    }

    @Bean
    StartController startController() {
        return new StartController(this);
    }


    @Bean
    AddActivityController activityController() {
        return new AddActivityController(this);
    }

    @Bean
    public MainWindow activityScreen() {
        return new MainWindow(activityController(), primaryStage, StageStyle.DECORATED, true);
    }


    //---------------------------------------------scenes------------------------------------------

    @Bean
    public SwitchabScene foodScene() {
        return new SwitchabScene(foodController(), getClass()
            .getResource("/views/Add_FoodActivity.fxml"));
    }

    @Bean
    SceneController foodController() {
        return new AddFoodController(this);
    }

    @Bean
    public SwitchabScene transportScene() {
        return new SwitchabScene(transportController(), getClass()
            .getResource("/views/Add_TransportActivity.fxml"));
    }

    @Bean
    SceneController transportController() {
        return new AddTransportController(this);
    }

    @Bean
    public SwitchabScene addSolarPanelScene() {
        return new SwitchabScene(addSolarPanelController(), getClass()
            .getResource("/views/Add_SolarPanel.fxml"));
    }

    @Bean
    SceneController addSolarPanelController() {
        return new AddSolarPanelController(this);
    }

    @Bean
    public SwitchabScene roomScene() {
        return new SwitchabScene(roomController(), getClass()
            .getResource("/views/Add_RoomHeating.fxml"));
    }

    @Bean
    SceneController roomController() {
        return new AddRoomController(this);
    }

    @Bean
    public SwitchabScene statisticScene() {
        return new SwitchabScene(statisticController(), getClass()
            .getResource("/views/StartView_Alt.fxml"));
    }

    @Bean
    StatisticController statisticController() {
        return new StatisticController(this);
    }

    @Bean
    public SwitchabScene achievementsScene() {
        return new SwitchabScene(achieveController(), getClass()
            .getResource("/views/Achievements.fxml"));
    }

    @Bean
    AchievementsController achieveController() {
        return new AchievementsController(this);
    }

    @Bean
    public SwitchabScene plantTreeScene() {
        return new SwitchabScene(plantTreeController(), getClass()
                .getResource("/views/Plant_Tree.fxml"));
    }

    @Bean
    SceneController plantTreeController(){ return new PlantTreeController(this);}

}
