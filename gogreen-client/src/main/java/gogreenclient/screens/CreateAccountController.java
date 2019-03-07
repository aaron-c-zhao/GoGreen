package gogreenclient.screens;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import gogreenclient.screens.Window.WindowController;
import gogreenclient.screens.Window.Windows;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import org.springframework.beans.factory.annotation.Autowired;

public class CreateAccountController implements WindowController {

    @Autowired
    private ScreenConfiguration screens;
    private Windows dialog;

    @Override
    public void setWindow(Windows dialog) {
        this.dialog = dialog;
    }

    public CreateAccountController(ScreenConfiguration screens) {
        this.screens = screens;
    }

    @FXML
    JFXPasswordField password;
    @FXML
    JFXPasswordField repeat_password;
    @FXML
    JFXTextField username;
    @FXML
    JFXTextField nationality;
    @FXML
    DatePicker bday;
    @FXML
    Label incorrect;

    //the create button function
    @FXML
    public void createAccount() {
        // if some of the field is not filled (is equal to null), show the label and set its text to ...
        if (username.getText().trim().isEmpty() || password.getText().trim().isEmpty() || repeat_password.getText().trim().isEmpty() || nationality.getText().trim().isEmpty() || bday.getValue() == null) {
            incorrect.setText("Not all the fields are filled");
            incorrect.setVisible(true);
        }
        //else if the password and repeat_password don't match show the other message
        else {
            if (!password.getText().equals(repeat_password.getText())) {
                incorrect.setVisible(true);
                incorrect.setText("The passwords don't match");
            }
            // else(if everything is correct) print(for now) all the filled information
            else {
                System.out.println(username.getText());
                System.out.println(password.getText());
                System.out.println(bday.getValue());
                System.out.println(nationality.getText());
                dialog.close();
                screens.loginDialog().show();
            }
        }
    }

    /**
     * function for the other button to go the the login view
     */
    @FXML
    public void switchToLogin() {
        dialog.close();
        screens.loginDialog().show();
    }
}

