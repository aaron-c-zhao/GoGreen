package gogreenclient.screens;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import gogreenclient.screens.DialogPrototype.DialogController;
import gogreenclient.screens.DialogPrototype.FXMLDialog;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;

public class LoginController implements DialogController {

    @Autowired
    private AuthenticationManager authenticationManager;
    private ScreenConfiguration screens;
    private FXMLDialog dialog;


    @Override
    public void setDialog(FXMLDialog dialog) {
        this.dialog = dialog;
    }

    public LoginController(ScreenConfiguration screens) {
        this.screens = screens;
    }

    @FXML
    public JFXTextField username;
    @FXML
    public JFXPasswordField password;
    @FXML
    public Label combination_label;

    @FXML
    Hyperlink create;

    @FXML
    public void switchToMenu() {
        Authentication authToken = new UsernamePasswordAuthenticationToken(username.getText(), password.getText());
        try {
            authToken = authenticationManager.authenticate(authToken);
            SecurityContextHolder.getContext().setAuthentication(authToken);
        } catch (AuthenticationException e) {
            combination_label.setVisible(true);
            return;
        }

        dialog.close();
//      gogreenclient.views.showSreeen(gogreenclient.views.sampleScreen());
    }

    @FXML
    public void switchToCreate() {
        combination_label.setVisible(false);
//        gogreenclient.views.showSreeen(gogreenclient.views.menu());
    }
}
