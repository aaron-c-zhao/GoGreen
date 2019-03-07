package gogreenclient.screens;

import gogreenclient.screens.DialogPrototype.Dialog;
import gogreenclient.screens.DialogPrototype.DialogController;
import gogreenclient.screens.DialogPrototype.FXMLDialog;
import javafx.fxml.FXML;
import org.springframework.beans.factory.annotation.Autowired;

public class SampleController implements DialogController {

    @Autowired
    private ScreenConfiguration screens;
    private Dialog dialog;

    public SampleController(ScreenConfiguration screens){
        this.screens = screens;
    }

    @Override
    public void setDialog(Dialog dialog) {
        this.dialog = dialog;
    }


    @FXML
    public void switchToFood(){

    }

    //close the program + call the pop-up
    @FXML
    public void closeProgram(){
        screens.exitDialog().show();
    }
}
