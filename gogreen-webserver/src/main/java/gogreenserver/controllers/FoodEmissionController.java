package gogreenserver.controllers;

import gogreenserver.entity.FoodEmission;
import gogreenserver.repositories.FoodEmissionRepository;
import gogreenserver.responses.*;
import gogreenserver.services.FoodEmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;
import java.util.Optional;

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
    public Optional<FoodEmission> addFoodEmission(
            @RequestBody String foodName
    ) {
        Optional<FoodEmission> foodEmissionResponse = service.findById(foodName);

        return foodEmissionResponse;
    }


}
