package gogreenclient.screens;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import gogreenclient.config.AppConfig;
import gogreenclient.datamodel.UserModel;
import gogreenclient.screens.window.WindowController;
import gogreenclient.screens.window.Windows;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

public class LoginController implements WindowController {

    @FXML
    public JFXTextField username;
    @FXML
    public JFXPasswordField password;
    @FXML
    public Label combinationLabel;
    @FXML
    Hyperlink create;
    private ScreenConfiguration screens;
    private Windows dialog;

    @Autowired
    private AppConfig appConfig;

    public LoginController(ScreenConfiguration screens) {
        this.screens = screens;
    }

    @Override
    public void setWindow(Windows dialog) {
        this.dialog = dialog;
    }


    /**
     * method of the button login.
     */
    @FXML
    public void switchToMenu() throws Exception{
        combinationLabel.setVisible(false);
        appConfig.setUsername(username.getText());
        appConfig.setPassword(password.getText());
        ResponseEntity<String> response = null;
        RestTemplate restTemplate = appConfig.restTemplate();
        try {
            response = restTemplate
                .getForEntity(new URI("https://localhost:8443/api/login"),
                    String.class);
        } catch (URISyntaxException e) {
            System.out.println("wrong URI");
        }
        if (response != null && response.getStatusCode() == HttpStatus.OK) {
            dialog.close();
            screens.sampleDialog().show();
        }
        else{
            combinationLabel.setVisible(true);
            return;
        }


    }


    /**
     * method of create account Hyperlink, which will switch the stage to create account.
     */
    @FXML
    public void switchToCreate() {
        combinationLabel.setVisible(false);
        dialog.close();
        screens.createAccountDialog().show();
    }

}
