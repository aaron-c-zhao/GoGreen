package sample;

/**
 * controller for the menu
 */
public class Sample_Controller {
    //switch to food
    public void switchToFood() {
        Main.window.setScene(Main.food);
    }

    //close the program + call the pop-up
    public void closeProgram() {
        Boolean answer = Exit_ConfirmBox.display("GoGreen close", "Are you sure you want to close the window?");
        if (answer) {
            Main.window.close();
        }
    }

}
