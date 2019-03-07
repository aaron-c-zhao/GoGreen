package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    //initializing primary stage and scenes
    protected static Stage window;
    static Scene menu,food,vegetarian_meal,login,create;
    @Override
    //start class containing the layouts
    public void start(Stage primaryStage) throws Exception{
        window=primaryStage;
        window.setTitle("#GoGreen");

        //creating the layouts from the fxmls
        Parent root = FXMLLoader.load(getClass().getResource("../gogreen.gogreenclient.views/sample.fxml"));
        Parent food_lay = FXMLLoader.load(getClass().getResource("../gogreen.gogreenclient.views/food_menu.fxml"));
        Parent vegetarian = FXMLLoader.load(getClass().getResource("../gogreen.gogreenclient.views/Vegetarian_Meal_Options.fxml"));
        Parent login_layout = FXMLLoader.load(getClass().getResource("../gogreen.gogreenclient.views/Log_in.fxml"));
        Parent create_layout = FXMLLoader.load(getClass().getResource("../gogreen.gogreenclient.views/Create_account.fxml"));

        //creating the scenes from the layouts
        food=new Scene(food_lay,480,640);
        menu=new Scene(root,480,640);
        login=new Scene(login_layout,480,640);
        create=new Scene(create_layout,480,644);
        vegetarian_meal=new Scene(vegetarian);

        //starting scene = login
        window.setScene(login);
        window.show();

        // action when closing
        window.setOnCloseRequest(event -> {event.consume();closeProgram();});
    }

    /**
     * method for closing the program (calling the pop-up)
     */
    private void closeProgram(){
        Boolean answer = Exit_ConfirmBox.display("GoGreen close","Are you sure you want to close the window?");
        if (answer)window.close();
    }

    //main launches the app
    public static void main(String[] args) {
        launch(args);
    }
}
