package gogreenclient.screens;

import gogreenclient.screens.DialogPrototype.ConfirmDialog;
import gogreenclient.screens.DialogPrototype.FXMLDialog;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

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

    public void showSreeen(Parent screen) {
        primaryStage.setScene(new Scene(screen, 480, 640));
        primaryStage.show();
    }


    @Bean
    @Scope("prototype")
    public FXMLDialog loginDialog() {
        return new FXMLDialog(loginController(), getClass().getResource("/views/Log_in.fxml"), primaryStage, StageStyle.DECORATED);
    }

    @Bean
    @Scope("prototype")
    LoginController loginController() {
        return new LoginController(this);
    }

    @Bean
    public FXMLDialog sampleDialog() {
        return new FXMLDialog(sampleController(), getClass().getResource("/views/sample.fxml"), primaryStage, StageStyle.DECORATED);
    }

    @Bean
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
}
