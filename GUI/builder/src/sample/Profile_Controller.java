package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;

import java.awt.*;

/**
 * this is test class don't look at it
 * really
 */
public class Profile_Controller {
    @FXML
    public TextField textField;

    final PasswordField pb = new PasswordField();

   /* textField.textProperty().addListener(new ChangeListener<String>() {
        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            if (!newValue.matches("\\d{0,7}([\\.]\\d{0,4})?")) {
                vehiclePrice_TextField.setText(oldValue);
            }
        }
    });*/
   /* public void initialize(){
        textField.addTextListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,7}([\\.]\\d{0,4})?")) {
                    vehiclePrice_TextField.setText(oldValue);
                }
            }
        });
    }*/
}
