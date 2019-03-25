package gogreenserver.controllers;

import gogreenserver.entity.Achievements;
import gogreenserver.services.AchievementsService;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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
    private final Logger logger;

    @Autowired
    public AchievementsController(AchievementsService achievementsService, Logger logger) {
        this.achievementsService = achievementsService;
        this.logger = logger;
    }

    @GetMapping(value = "/achievements")
    public ResponseEntity<List<Achievements>> findAll(Authentication auth) {
        logger.debug("GET /achievements/ accessed by: " + auth.getName());
        return new ResponseEntity<>(this.achievementsService.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/achievement/{user_name}")
    public ResponseEntity<Optional<Achievements>> findById(
            @PathVariable("user_name") String userName, Authentication auth) {
        logger.debug("GET /achievement/" + userName + " accessed by: " + auth.getName());
        return new ResponseEntity<>(this.achievementsService.findById(userName), HttpStatus.OK);
    }
}
