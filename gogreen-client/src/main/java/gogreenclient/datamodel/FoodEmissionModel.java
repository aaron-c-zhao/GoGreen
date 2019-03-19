package gogreenclient.datamodel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

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
    public int compareFood(String eatenFood, String usualFood,
                           int eatenFoodQuantity, int usualFoodQuantity) {
        if ((eatenFood == usualFood) && (eatenFoodQuantity == usualFoodQuantity)) {
            return 0;
        }
        FoodEmission mealEaten = getFoodEmissions(eatenFood);
        FoodEmission mealUsual = getFoodEmissions(usualFood);

        changedCO2 = mealUsual.getEmission() - mealEaten.getEmission();
        return changedCO2;
    }

}
