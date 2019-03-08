package sample;

/**
 * class for the pop-up button when you close the program (needs new layout)(layout is only created using javafx, without fxml)
 */
public class Exit_ConfirmBox {
    static boolean answer;

    public static boolean display(String title, String message) {
        //create new window
        Stage window = new Stage();

        //blocking the user to use another window before he/she finish with this one
        window.initModality(Modality.APPLICATION_MODAL);

        //set title and width to the window
        window.setTitle(title);
        window.setMinWidth(300);
        Label label = new Label();
        label.setText(message);

        //creating buttons for yes/no
        Button yesButton = new Button("Yes");
        Button noButton = new Button("No");

        //yes button function
        yesButton.setOnAction(e -> {
            answer = true;
            window.close();
        });

        //no button function
        noButton.setOnAction(e -> {
            answer = false;
            window.close();
        });

        //the layout (consisting of vbox, wraping the label and the hbox(contain the buttons))
        VBox layout = new VBox(15);
        HBox buttons = new HBox(25);
        buttons.getChildren().addAll(yesButton, noButton);
        buttons.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(label, buttons);
        layout.setAlignment(Pos.CENTER);

        //creating the scene and setting it to the window
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
        return answer;
    }
}