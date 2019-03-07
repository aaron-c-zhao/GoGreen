package gogreenclient.screens;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import gogreenclient.config.AppConfig;
import gogreenclient.screens.DialogPrototype.Dialog;
import gogreenclient.screens.DialogPrototype.DialogController;
import gogreenclient.screens.DialogPrototype.FXMLDialog;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class LoginController implements DialogController {

    @Autowired
    private AuthenticationManager authenticationManager;
    private ScreenConfiguration screens;
    private Dialog dialog;


    @Override
    public void setDialog(Dialog dialog) {
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
//        PasswordEncoder encoder = new BCryptPasswordEncoder();
//        System.out.println(encoder.encode("zhao"));
//        Authentication authToken = new UsernamePasswordAuthenticationToken(username.getText(), encoder.encode(password.getText()));
//        try {
//            authToken = authenticationManager.authenticate(authToken);
//            SecurityContextHolder.getContext().setAuthentication(authToken);
//        } catch (AuthenticationException e) {
//            combination_label.setVisible(true);
//            return;
//        }

        dialog.close();
        screens.sampleDialog().show();
    }

    @FXML
    public void switchToCreate() {
        combination_label.setVisible(false);
//        gogreenclient.views.showSreeen(gogreenclient.views.menu());
    }

}
