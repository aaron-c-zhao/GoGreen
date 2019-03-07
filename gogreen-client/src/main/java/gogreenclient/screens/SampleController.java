package gogreenclient.screens;

import gogreenclient.screens.DialogPrototype.DialogController;
import gogreenclient.screens.DialogPrototype.FXMLDialog;

public class SampleController implements DialogController {

    private ScreenConfiguration screens;
    private FXMLDialog dialog;

    @Override
    public void setDialog(FXMLDialog dialog) {
        this.dialog = dialog;
    }
//
//    @Override
//    public void setScreen(FXMLDialog screen) {
//        this.screen = screen;
//    }
//
//    public SampleController(ScreenConfiguration gogreenclient.views){
//        this.gogreenclient.views = gogreenclient.views;
//    }
//    @FXML
//    public void switchToFood(){
//        Main.window.setScene(Main.food);
//    }
//
//
//    //close the program + call the pop-up
//    @FXML
//    public void closeProgram(){
//        Boolean answer = Exit_ConfirmBox.display("GoGreen close","Are you sure you want to close the window?");
//        if (answer) Main.window.close();
//    }
}
