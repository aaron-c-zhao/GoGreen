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

    /**
     * Get the achievements of a single user.
     */
    @GetMapping(value = "/achievement/{user_name}")
    public ResponseEntity<Object> findById(@PathVariable("user_name") String userName,
            Authentication auth) {
        logger.debug("GET /achievement/" + userName + " accessed by: " + auth.getName());
        List<Achievements> res = this.achievementsService.findAllByUsername(userName);
        if (res != null) {
            return new ResponseEntity<Object>(res, HttpStatus.OK);
        } else {
            return new ResponseEntity<Object>(userName + " has achieved nothing",
                    HttpStatus.NOT_FOUND);
        }
    }
}