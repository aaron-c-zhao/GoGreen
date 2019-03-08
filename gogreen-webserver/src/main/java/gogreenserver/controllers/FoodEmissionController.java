package gogreenserver.controllers;

import gogreenserver.entity.FoodEmission;
import gogreenserver.services.FoodEmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class FoodEmissionController {

    private FoodEmissionService service;

    @Autowired
    public FoodEmissionController(FoodEmissionService service) {
        this.service = service;
    }

    @GetMapping("/foodEmission")
    public List<FoodEmission> getAllFoodEmission() {
        return service.findAll();
    }

    @GetMapping("/foodEmission/{food_name}")
    public Optional<FoodEmission> getFoodEmissionById(@PathVariable("food_name") String foodName) {
        return service.findById(foodName);
    }

// 	@DeleteMapping("/foodEmission/{food_name}")
// 	public String deleteFoodEmission(@PathVariable("food_name") String foodName) {
// 		service.deleteFoodEmissionById(foodName);
// 		return "Food emission data has been deleted of food with name + " +foodName;
// 	}

    @PostMapping("/foodEmission")
    public FoodEmission addFoodEmission(@RequestBody FoodEmission foodEmission) {
        return service.createFoodEmission(foodEmission);
    }


}
