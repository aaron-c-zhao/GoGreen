package gogreenclient.screens;

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
        return new FXMLDialog(loginController(), getClass().getResource("/views/Log_in.fxml"), primaryStage, StageStyle.UNDECORATED);
    }

    @Bean
    @Scope("prototype")
    LoginController loginController() {
        return new LoginController(this);
    }

}
