package gogreenclient.screens.DialogPrototype;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.StageStyle;
import javafx.stage.Window;

public class ConfirmDialog extends Dialog {


    public ConfirmDialog(final ConfirmDialogController controller, Window owner, StageStyle style, String title, String message){
        super(style);
        initOwner(owner);
        initModality(Modality.WINDOW_MODAL);
        setTitle(title);
        setMinWidth(300);

        Label label = new Label();
        label.setText(message);

        //creating buttons for yes/no
        Button yesButton= new Button("Yes");
        Button noButton= new Button("No");

        //yes button function
        yesButton.setOnAction(e->{
            controller.yes();
        });

        //no button function
        noButton.setOnAction(e->{
            controller.no();
        });

        //the layout (consisting of vbox, wraping the label and the hbox(contain the buttons))
        VBox layout = new VBox(15);
        HBox buttons = new HBox(25);
        buttons.getChildren().addAll(yesButton,noButton);
        buttons.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(label,buttons);
        layout.setAlignment(Pos.CENTER);

        controller.setDialog(this);
        setScene(new Scene(layout));
    }
}
