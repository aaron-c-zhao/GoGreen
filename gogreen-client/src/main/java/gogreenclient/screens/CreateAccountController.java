package gogreenclient.screens;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import gogreenclient.datamodel.ExceptionHandler;
import gogreenclient.datamodel.User;
import gogreenclient.datamodel.UserAccountValidator;
import gogreenclient.datamodel.UserModel;
import gogreenclient.screens.window.ConfirmDialog;
import gogreenclient.screens.window.WindowController;
import gogreenclient.screens.window.Windows;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.URISyntaxException;

public class CreateAccountController implements WindowController {

    @FXML
    JFXPasswordField password;
    @FXML
    JFXPasswordField repeatPassword;
    @FXML
    JFXTextField username;
    @FXML
    JFXTextField email;
    @FXML
    JFXDatePicker bday;
    @FXML
    Label incorrect;

    private ScreenConfiguration screens;

    @Autowired
    private UserModel userModel;

    @Autowired
    private UserAccountValidator validator;

    @Autowired
    private ExceptionHandler exceptionHandler;

    private Windows dialog;


    public CreateAccountController(ScreenConfiguration screens) {
        this.screens = screens;
    }

    public Windows getWindow() {
        return dialog;
    }

    @Override
    public void setWindow(Windows dialog) {
        this.dialog = dialog;
    }

    /**
     * initialize the create account scene.
     */
    public void initialize() {
        dialog.setOnCloseRequest(e -> {
            e.consume();
            dialog.close();
            screens.loginDialog().show();
        });
    }

    /**
     * the create button function.
     */
    @FXML
    public void createAccount() {
        try {
            validator.accountValidate(username.getText(), password.getText(),
                repeatPassword.getText(), bday.getValue(), email.getText());
        }catch (IllegalArgumentException e){
            exceptionHandler.illegalArgumentExceptionhandler(e);
            return;
        }
        ResponseEntity<User> response = null;
        try {
            response = userModel.addUser(username.getText(), password.getText(),
                bday.getValue(), email.getText());
        } catch (URISyntaxException e) {
            System.out.println("Wrong URI");
            return;
        }
        //TODO when creating account success, popup window shows
        if (response != null && response.getStatusCode() == HttpStatus.OK) {
            dialog.close();
            screens.loginDialog().show();
        } else {
            return;
        }


    }

    /**
     * function for the other button to go the the login view.
     */
    @FXML
    public void switchToLogin() {
        dialog.close();
        screens.loginDialog().show();
    }
}