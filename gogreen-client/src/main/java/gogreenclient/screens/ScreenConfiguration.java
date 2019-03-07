package gogreenclient.screens;

import gogreenclient.screens.Window.ConfirmDialog;
import gogreenclient.screens.Window.FXMLWindow;
import gogreenclient.screens.Window.MainWindow;
import gogreenclient.screens.Window.SwitchabScene;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import java.util.HashMap;

@Configuration
/**
 * beans in a lazy manner, only instantiate when used.
 */
@Lazy
public class ScreenConfiguration {

    private Stage primaryStage;

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void showSreeen(Scene screen) {
        primaryStage.setScene(screen);
        primaryStage.show();
    }

    public Parent getParent() {
        return primaryStage.getScene().getRoot();
    }

    public Stage getPrimaryStage(){
        return primaryStage;
    }


    @Bean
    public FXMLWindow loginDialog() {
        return new FXMLWindow(loginController(), getClass().getResource("/views/Log_in.fxml"), primaryStage, StageStyle.DECORATED);
    }

    @Bean
    LoginController loginController() {
        return new LoginController(this);
    }

    @Bean
    @Scope("prototype")
    public FXMLWindow sampleDialog() {
        return new FXMLWindow(sampleController(), getClass().getResource("/views/sample.fxml"), primaryStage, StageStyle.DECORATED);
    }

    @Bean
    @Scope("prototype")
    SampleController sampleController() {
        return new SampleController(this);
    }

    @Bean
    @Scope("prototype")
    public ConfirmDialog exitDialog() {
        return new ConfirmDialog(exitController(), primaryStage, StageStyle.DECORATED, "GoGreen Close", "Are you sure you want to close the window?");
    }

    @Bean
    @Scope("prototype")
    ExitController exitController() {
        return new ExitController(this);
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
    public HashMap<String, Scene> sceneMap() {
        return new HashMap<>();
    }

    @Bean
    public SwitchabScene foodScene(){
        return new SwitchabScene(foodController(), getClass().getResource("/views/Vegetarian_Meal_Options.fxml"), "mainSecene");
    }

    @Bean
    FoodController foodController() {
        return new FoodController(this);
    }

    @Bean
    CreateAccountController createAccountController(){
        return new CreateAccountController(this);
    }

    @Bean
    public FXMLWindow createAccountDialog(){
        return new FXMLWindow(createAccountController(), getClass().getResource("/views/Create_account.fxml"), primaryStage, StageStyle.DECORATED);
    }


}
