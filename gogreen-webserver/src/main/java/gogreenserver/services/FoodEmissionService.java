package gogreenserver.services;

import gogreenserver.entity.FoodEmission;
import gogreenserver.repositories.FoodEmissionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FoodEmissionService {

    private FoodEmissionRepository repo;

    public FoodEmissionService(FoodEmissionRepository repo) {
        this.repo = repo;
    }

    public List<FoodEmission> findAll() {
        return repo.findAll();
    }

    public Optional<FoodEmission> findById(String food) {
        return repo.findById(food);
    }

    public FoodEmission createFoodEmission(FoodEmission foodEmission) {
        return repo.save(foodEmission);
    }

    public void deleteFoodEmissionById(String food_name) {
        repo.deleteById(food_name);
    }


}
