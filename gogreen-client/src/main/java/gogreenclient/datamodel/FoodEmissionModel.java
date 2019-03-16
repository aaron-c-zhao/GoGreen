package gogreenclient.datamodel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

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
    @Autowired
    private HttpRequestService httpRequestService;

    private int changedCO2;


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

    /**
     *
     * @param username
     * @return
     */
    public UserCareer updateUserCareer(String username){
        //TODO for now username is hardcoded, it will be able to retrive
        //username from the login information in the future
        username = "zhao";
        final String uri = "https://localhost:8443/api/career";
        UserCareer userCareer = restTemplate.getForObject(uri+"/"+ username, UserCareer.class);
        UserCareer finalCareer = null;
        if(userCareer == null){
            UserCareer career = new UserCareer();
            career.setUsername(username);
            career.setCo2saved(changedCO2);
            try{
                 ResponseEntity<UserCareer> response = httpRequestService
                     .postRequest(career, new URI(uri), MediaType.APPLICATION_JSON);
                 if(response.getStatusCode() == HttpStatus.OK){
                     finalCareer = response.getBody();
                 };
            }catch (URISyntaxException e){
                System.out.println("wrong URI");
            }
        }else{
            userCareer.setCo2saved(userCareer.getCo2saved()+changedCO2);
            try {
                ResponseEntity<UserCareer> response = httpRequestService
                    .postRequest(userCareer, new URI(uri+"update"), MediaType.APPLICATION_JSON);
                if (response.getStatusCode() == HttpStatus.OK)
                    finalCareer = userCareer;
                else {
                    //TODO an exception should be threw here
                    System.out.println("something worng with the server");
                }
            }catch (URISyntaxException e){
                System.out.println("wrong URI");
            }
        }
        return finalCareer;
    }
    //TODO what if can not find the username in database, exception handler needed
    public UserCareer getCareer(String username){
        username = "zhao";
        final String uri = "https://localhost:8443/api/career";
        UserCareer userCareer = restTemplate.getForObject(uri+"/"+ username, UserCareer.class);
        return userCareer;
    }


}
