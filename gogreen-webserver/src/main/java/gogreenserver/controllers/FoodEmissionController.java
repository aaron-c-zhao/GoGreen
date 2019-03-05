package gogreenserver.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gogreenserver.entity.FoodEmission;
import gogreenserver.services.FoodEmissionService;

@RestController 
@RequestMapping("/api")
public class FoodEmissionController {
	
	private FoodEmissionService service;
	
	@Autowired
	public FoodEmissionController(FoodEmissionService service) {
		this.service = service;
	}
	
	@GetMapping("/foodEmission")
	public List<FoodEmission> getAllFoodEmission(){
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
