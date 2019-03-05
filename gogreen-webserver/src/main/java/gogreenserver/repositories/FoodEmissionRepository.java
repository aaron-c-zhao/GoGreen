package gogreenserver.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import gogreenserver.entity.FoodEmission;

public interface FoodEmissionRepository extends JpaRepository<FoodEmission, String> {
	
}
