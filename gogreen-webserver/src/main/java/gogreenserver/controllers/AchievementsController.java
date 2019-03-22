package gogreenserver.controllers;

import gogreenserver.entity.Achievements;
import gogreenserver.services.AchievementsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class AchievementsController {

    private AchievementsService achievementsService;

    @Autowired
    public AchievementsController(AchievementsService achievementsService) {
        this.achievementsService = achievementsService;
    }

    @GetMapping(value = "/achievements")
    public List<Achievements> findAll() {
        return this.achievementsService.findAll();
    }

    @GetMapping(value = "/achievement/{user_name}")
    public Optional<Achievements> findById(@PathVariable("user_name") String userName) {
        return this.achievementsService.findById(userName);
    }
}
