package gogreenclient.screens;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import gogreenclient.datamodel.User;
import gogreenclient.datamodel.UserModel;
import gogreenclient.screens.window.WindowController;
import gogreenclient.screens.window.Windows;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
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
    JFXTextField nationality;
    @FXML
    JFXDatePicker bday;
    @FXML
    Label incorrect;
    @Autowired
    private ScreenConfiguration screens;
    @Autowired
    private UserModel userModel;
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
     * the create button function.
     */
    @FXML
    public void createAccount() {
        // if some of the field is not filled (is equal to null), show the label and
        // set its text to ...
        if (username.getText().trim().isEmpty() || password.getText().trim().isEmpty()
            || repeatPassword.getText().trim().isEmpty() || nationality.getText().trim().isEmpty()
            || bday.getValue() == null) {
            incorrect.setText("Not all the fields are filled");
            incorrect.setVisible(true);
            //else if the password and repeatPasswordPassword don't match show the other message
        } else {
            if (!password.getText().equals(repeatPassword.getText())) {
                incorrect.setVisible(true);
                incorrect.setText("The passwords don't match");
                // else(if everything is correct) print(for now) all the filled information
            } else {
                ResponseEntity<User> response = null;
                try {
                    response = userModel.addUser(username.getText(), password.getText(),
                        bday.getValue(), nationality.getText());
                } catch (URISyntaxException e) {
                    System.out.println("Wrong URI");
                    return;
                }
                if (response != null && response.getStatusCode() == HttpStatus.OK) {
                    dialog.close();
                    screens.loginDialog().show();
                } else {
                    return;
                }
            }
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