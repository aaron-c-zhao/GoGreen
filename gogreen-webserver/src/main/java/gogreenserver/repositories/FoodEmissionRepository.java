package gogreenserver.repositories;

import gogreenserver.entity.FoodEmission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodEmissionRepository extends JpaRepository<FoodEmission, String> {
}
