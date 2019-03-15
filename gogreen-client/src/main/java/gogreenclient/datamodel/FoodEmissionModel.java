package gogreenclient.datamodel;

import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import gogreenclient.datamodel.FoodEmission;
import java.net.URI;
import java.net.URISyntaxException;

public class FoodEmissionModel {
    /* TODO Add every 'CO2 Saving Instance' to each user savings and return a pass or fail for response
    public static String CO2SavedTransfer(String CO2){
        final String uri = "http://localhost:8080/api/foodEmission";
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.postForObject(uri, CO2, String.class);
        return response;
    }
    */

    // Sending a String with the food name, then it crosschecks if the food name is found on the list.
    public static FoodEmission getFoodEmissions(String foodName){
        final String uri = "http://localhost:8080/api/foodEmission";
        RestTemplate restTemplate = new RestTemplate();
        FoodEmission response = restTemplate.postForObject(uri, foodName, FoodEmission.class);
        return response;
    }

    // TODO (quantity of both)
    // Does a comparison between the usage of CO2 between foods
    public static String compareFood(String eatenFood, String usualFood, int eatenFoodQuantity, int usualFoodQuantity){
        if((eatenFood == usualFood) && (eatenFoodQuantity == usualFoodQuantity)){
            return "There is no difference in CO2 between both meals.";
        }
        FoodEmission mealEaten = getFoodEmissions(eatenFood);
        FoodEmission mealUsual = getFoodEmissions(usualFood);

        int changedCO2 = mealUsual.getEmission() - mealEaten.getEmission();

        if(changedCO2 > 0){
            return "You have saved " + changedCO2 + " kg of CO2.";
        }
        if(changedCO2 < 0){
            return "You have lost " + changedCO2 + " kg of possibly saved CO2.";
        }
        else {
            return "You haven't saved anything with this meal.";
        }
    }


}
