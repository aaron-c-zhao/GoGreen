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

    public void switchToCreate(){
        combination_label.setVisible(false);
        Main.window.setScene(Main.create);
    }

    public void switchToMenu(){
        if(password.getText().equals("123")){
        System.out.println(username.getText());
        System.out.println(password.getText());
        Main.window.setScene(Main.menu);}
        else combination_label.setVisible(true);
    }

}
