package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    static Stage window;
    static Scene menu,food,vegetarian_meal,login,create;
    @Override
    public void start(Stage primaryStage) throws Exception{

        window=primaryStage;
        window.setTitle("#GoGreen");
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Parent food_lay = FXMLLoader.load(getClass().getResource("food_menu.fxml"));
        Parent vegetarian = FXMLLoader.load(getClass().getResource("VegetarianMeal_Options.fxml"));
        Parent login_layout = FXMLLoader.load(getClass().getResource("Log_in.fxml"));
        Parent create_layout = FXMLLoader.load(getClass().getResource("Create_account.fxml"));
        food=new Scene(food_lay,480,640);
        menu=new Scene(root,480,640);
        login=new Scene(login_layout,480,640);
        create=new Scene(create_layout,480,644);
        vegetarian_meal=new Scene(vegetarian);
        window.setScene(login);
        window.show();


        // action when closing + close button
        window.setOnCloseRequest(event -> {event.consume();closeProgram();});
    }

    /**
     * method for closing the program
     */
    private void closeProgram(){
        Boolean answer = Exit_ConfirmBox.display("GoGreen close","Are you sure you want to close the window?");
        if (answer)window.close();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
