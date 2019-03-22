package gogreenclient.screens;

import gogreenclient.screens.window.ConfirmDialog;
import gogreenclient.screens.window.FxmlWindow;
import gogreenclient.screens.window.MainWindow;
import gogreenclient.screens.window.SwitchabScene;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

import java.util.HashMap;

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
    public void showScene(Scene scene) {
        primaryStage.setScene(scene);
        primaryStage.show();
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

    @Bean
    public ConfirmDialog addFoodDialog() {
        return new ConfirmDialog(addFoodController(), getClass()
            .getResource("/views/Add_FoodActivity.fxml"), primaryStage, StageStyle.DECORATED);
    }

    @Bean
    public ConfirmDialog addTransportDialog() {
        return new ConfirmDialog(addFoodController(), getClass()
            .getResource("/views/Add_TransportActivity.fxml"), primaryStage, StageStyle.DECORATED);
    }

    @Bean
    public ConfirmDialog addRoomDialog() {
        return new ConfirmDialog(addFoodController(), getClass()
            .getResource("/views/Add_RoomHeating.fxml"), primaryStage, StageStyle.DECORATED);
    }

    @Bean
    public ConfirmDialog addSolarDialog() {
        return new ConfirmDialog(addFoodController(), getClass()
            .getResource("/views/Add_SolarPanel.fxml"), primaryStage, StageStyle.DECORATED);
    }

    @Bean
    AddFoodController addFoodController() {
        return new AddFoodController(this);
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

    //---------------------------------------------scenes------------------------------------------


    @Bean
    public HashMap<String, Scene> sceneMap() {
        return new HashMap<>();
    }

    @Bean
    @Scope("prototype")
    public FxmlWindow sampleDialog() {
        return new FxmlWindow(sampleController(), getClass()
            .getResource("/views/sample.fxml"), primaryStage, StageStyle.DECORATED);
    }

    @Bean
    @Scope("prototype")
    SampleController sampleController() {
        return new SampleController(this);
    }

    @Scope("prototype")
    public FxmlWindow startDialog() {
        return new FxmlWindow(startController(), getClass()
            .getResource("/views/StartView_Alt.fxml"), primaryStage, StageStyle.DECORATED);
    }

    @Bean
    @Scope("prototype")
    StartController startController() {
        return new StartController(this);
    }

    @Scope("prototype")
    public FxmlWindow achievementsDialog() {
        return new FxmlWindow(achieveController(), getClass()
            .getResource("/views/Achievements.fxml"), primaryStage, StageStyle.DECORATED);
    }

    @Bean
    @Scope("prototype")
    AchievementsController achieveController() {
        return new AchievementsController(this);
    }

    @Bean
    ActivityController activityController() {
        return new ActivityController(this);
    }

    @Bean
    public MainWindow activityScreen() {
        return new MainWindow(activityController(), primaryStage, StageStyle.DECORATED);
    }

    @Bean
    public SwitchabScene foodScene() {
        return new SwitchabScene(foodController(), getClass()
            .getResource("/views/Vegetarian_Meal_Options.fxml"), "mainSecene");
    }

    @Bean
    FoodController foodController() {
        return new FoodController(this);
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


}
