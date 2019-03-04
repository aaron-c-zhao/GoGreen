package sample;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Sample_Controller {
    public void switchToFood(){
        Main.window.setScene(Main.food);
    }

    public void closeProgram(){
        Boolean answer = Exit_ConfirmBox.display("GoGreen close","Are you sure you want to close the window?");
        if (answer) Main.window.close();
    }

}
