package gogreenclient.screens;

import gogreenclient.screens.DialogPrototype.DialogController;
import gogreenclient.screens.DialogPrototype.FXMLDialog;
import javafx.fxml.FXML;
import org.springframework.beans.factory.annotation.Autowired;

public class SampleController implements DialogController {

    @Autowired
    private ScreenConfiguration screens;
    private FXMLDialog dialog;

    public SampleController(ScreenConfiguration screens){
        this.screens = screens;
    }

    @Override
    public void setDialog(FXMLDialog dialog) {
        this.dialog = dialog;
    }


    @FXML
    public void switchToFood(){

    }

    //close the program + call the pop-up
    @FXML
    public void closeProgram(){
//        Boolean answer = Exit_ConfirmBox.display("GoGreen close","Are you sure you want to close the window?");
//        if (answer) Main.window.close();
    }
}
