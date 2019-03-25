package gogreenserver.repositories;

import gogreenserver.entity.Achievements;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AchievementsRepository extends JpaRepository<Achievements, String> {
    public List<Achievements> findAllByUserName(String userName);
}
