package sample;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;

import java.awt.*;

public class Log_in_Controller {
    @FXML
    public JFXTextField username;
    @FXML
    public JFXPasswordField password;
    @FXML
    public Label combination_label;
    @FXML
    Hyperlink create;

    /**
     * switching to create an account view + setting the label message to not visible
     */
    public void switchToCreate(){
        combination_label.setVisible(false);
        Main.window.setScene(Main.create);
    }

    /**
     * go to menu (for now only print to the console the username and password)
     */
    public void switchToMenu(){
        //if the password is 123 (made it just to illustrate) proceed
        if(password.getText().equals("123")){
        System.out.println(username.getText());
        System.out.println(password.getText());
        Main.window.setScene(Main.menu);}
        //else show the label for incorrect password
        else combination_label.setVisible(true);
    }

}
