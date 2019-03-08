package sample;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;

/**
 * this is test class don't look at it
 * really
 */
public class Profile_Controller {
    final PasswordField pb = new PasswordField();
    @FXML
    public TextField textField;

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
