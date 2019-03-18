package gogreenclient.datamodel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

public class FoodEmissionModel {

    @Autowired
    private RestTemplate restTemplate;


    private int changedCO2;

    public int getChangedCO2() {
        return changedCO2;
    }

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Sending a String with the food name, then it crosschecks if the food
     * name is found on the list.
     */
    public FoodEmission getFoodEmissions(String foodName) {
        final String uri = "https://localhost:8443/api/foodEmission";
        FoodEmission response = restTemplate.postForObject(uri, foodName, FoodEmission.class);
        return response;
    }

    // TODO (quantity of both)

    /**
     * Does a comparison between the usage of CO2 between foods
     *
     * @param eatenFood         the food you chose to eat.
     * @param usualFood         usually what food you will eat
     * @param eatenFoodQuantity quantity of the food.
     * @param usualFoodQuantity quantity of the food.
     * @return return a statement about how much CO2 you have saved.
     */
    public String compareFood(String eatenFood, String usualFood,
                              int eatenFoodQuantity, int usualFoodQuantity) {
        if ((eatenFood == usualFood) && (eatenFoodQuantity == usualFoodQuantity)) {
            return "There is no difference in CO2 between both meals.";
        }
        FoodEmission mealEaten = getFoodEmissions(eatenFood);
        FoodEmission mealUsual = getFoodEmissions(usualFood);

        changedCO2 = mealUsual.getEmission() - mealEaten.getEmission();

        if (changedCO2 > 0) {
            return "You have saved " + changedCO2 + " kg of CO2.";
        }
        if (changedCO2 < 0) {
            return "You have lost " + changedCO2 + " kg of possibly saved CO2.";
        } else {
            return "You haven't saved anything with this meal.";
        }
    }

}
