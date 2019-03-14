package gogreenclient.screens;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import gogreenclient.screens.window.WindowController;
import gogreenclient.screens.window.Windows;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;

public class LoginController implements WindowController {

    @FXML
    public JFXTextField username;
    @FXML
    public JFXPasswordField password;
    @FXML
    public Label combinationLabel;
    @FXML
    Hyperlink create;
//    @Autowired
//    private AuthenticationManager authenticationManager;
    private ScreenConfiguration screens;
    private Windows dialog;

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
    public void switchToMenu() {
        //TODO
        //  PasswordEncoder encoder = new BCryptPasswordEncoder();
        //  System.out.println(encoder.encode("zhao"));
        //  Authentication authToken = new UsernamePasswordAuthenticationToken(username.getText()
        //  , encoder.encode(password.getText()));
        //  try {
        //      authToken = authenticationManager.authenticate(authToken);
        //      SecurityContextHolder.getContext().setAuthentication(authToken);
        //  } catch (AuthenticationException e) {
        //      combinationLabel.setVisible(true);
        //      return;
        //  }

        dialog.close();
        screens.sampleDialog().show();
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
