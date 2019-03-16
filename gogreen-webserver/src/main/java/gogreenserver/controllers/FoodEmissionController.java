package gogreenserver.controllers;

import gogreenserver.entity.FoodEmission;
import gogreenserver.services.FoodEmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class FoodEmissionController {

    private FoodEmissionService service;

    @Autowired
    public FoodEmissionController(FoodEmissionService foodService) {
        this.service = foodService;
    }

    // Returns the food emission object
    @PostMapping("/foodEmission")
    public FoodEmission addFoodEmission(
        @RequestBody String foodName
    ) {
        FoodEmission foodEmissionResponse = service.findById(foodName).orElse(null);

        return foodEmissionResponse;
    }


}
