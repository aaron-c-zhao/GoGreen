package gogreenserver.services;

import gogreenserver.entity.Achievements;
import gogreenserver.repositories.AchievementsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AchievementsService {

    private AchievementsRepository achievementsRepo;

    @Autowired
    public AchievementsService(AchievementsRepository achievementsRepository) {
        this.achievementsRepo = achievementsRepository;
    }

    public List<Achievements> findAll() {
        return achievementsRepo.findAll();
    }


    public List<Achievements> findAllByUserName(String userName){
        return achievementsRepo.findAllByUserName(userName);
    }

    // Yet have to add method to find Friends Achievements.


}
