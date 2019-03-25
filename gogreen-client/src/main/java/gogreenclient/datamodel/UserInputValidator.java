package gogreenclient.datamodel;

import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;


@Service
@Scope("prototype")
public class UserInputValidator {

    public void validateFraction(JFXTextField input) {
        input.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("[0-9]*\\.?[0-9]*")) {
                    input.setText(oldValue);
                }
            }
        });
    }

    public void validateInteger(JFXTextField input) {
        input.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d")) {
                    input.setText(oldValue);
                }
            }
        });
    }


    private void isNull(JFXTextField input){
        if(input.getText().equals(""))
            throw new IllegalArgumentException(input.getId());
    }
}
