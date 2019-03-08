package sample;

import sample.Main;

//constroller for food_menu
public class Controller {
    /**
     * function for the button to go the the menu view
     */
    public void switchToVegetarianMeal() {
        Main.window.setScene(Main.vegetarian_meal);
    }

    /**
     * function for the other button to go the the menu view
     */
    public void switchToMenu() {
        Main.window.setScene(Main.menu);
    }
}
