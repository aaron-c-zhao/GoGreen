package gogreenclient.datamodel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

public class FoodEmissionModel {
    /* TODO Add every 'CO2 Saving Instance' to each user savings and return a pass or fail for response
    public static String CO2SavedTransfer(String CO2){
        final String uri = "http://localhost:8080/api/foodEmission";
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.postForObject(uri, CO2, String.class);
        return response;
    }
    */
    @Autowired
    private RestTemplate restTemplate;

    public void setRestTemplate(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    // Sending a String with the food name, then it crosschecks if the food name is found on the list.
    public  FoodEmission getFoodEmissions(String foodName) {
        final String uri = "https://localhost:8443/api/foodEmission";
        FoodEmission response = restTemplate.postForObject(uri, foodName, FoodEmission.class);
        return response;
    }

    // TODO (quantity of both)
    // Does a comparison between the usage of CO2 between foods
    public  String compareFood(String eatenFood, String usualFood,
                                     int eatenFoodQuantity, int usualFoodQuantity) {
        if ((eatenFood == usualFood) && (eatenFoodQuantity == usualFoodQuantity)) {
            return "There is no difference in CO2 between both meals.";
        }
        FoodEmission mealEaten = getFoodEmissions(eatenFood);
        FoodEmission mealUsual = getFoodEmissions(usualFood);

        int changedCO2 = mealUsual.getEmission() - mealEaten.getEmission();

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
