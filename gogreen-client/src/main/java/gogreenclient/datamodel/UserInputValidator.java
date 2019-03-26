package gogreenclient.datamodel;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;


@Service
@Scope("prototype")
public class UserInputValidator {

    /**
     * validate user's fraction input, any non-digit character after the decimal will be
     * removed.
     * @param input user's input.
     */
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

    /**
     * validate user's integer input, any non-digit character  will be removed.
     * @param input user's input.
     */
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

    /**
     * validate if a text field is empty.
     * @param input user's input.
     * @throws IllegalArgumentException an exception which will contain the message of the error.
     */
    public void isNull(JFXTextField input) throws IllegalArgumentException {
        if (input.getText().equals("")) {
            throw new IllegalArgumentException(input.getId() + "is empty.");
        }
    }

    /**
     * validate if a combo box is empty.
     * @param box user's input.
     * @throws IllegalArgumentException an exception which will contain the message of the error.
     */
    public void isNull(JFXComboBox<String> box) throws IllegalArgumentException {
        if (box.getValue().equals("")) {
            throw new IllegalArgumentException(box.getId() + "is empty.");
        }
    }
}
