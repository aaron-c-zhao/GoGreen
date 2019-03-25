package gogreenserver.controllers;

import gogreenserver.entity.UserCareer;
import gogreenserver.services.UserCareerService;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

//import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserCareerController {

    private UserCareerService service;
    private final Logger logger;

    @Autowired
    public UserCareerController(UserCareerService service, Logger logger) {
        this.service = service;
        this.logger = logger;
    }

    @GetMapping("/career")
    public ResponseEntity<List<UserCareer>> getAllUserCareers(Authentication auth) {
        logger.debug("GET /career/ accessed by: " + auth.getName());
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping("/career/{user_name}")
    public ResponseEntity<Optional<UserCareer>> getUserCareerById(
            @PathVariable("user_name") String userName, Authentication auth) {
        logger.debug("GET /career/" + userName + " accessed by: " + auth.getName());
        return new ResponseEntity<>(service.findById(userName), HttpStatus.OK);
    }

    /**
     * Creates (or overrides existing) a new career.
     */
    @PostMapping("/career")
    public UserCareer addUserCareer(@RequestBody UserCareer career, Authentication auth) {
        logger.debug("POST /career/ accessed by: " + auth.getName());
        service.createUserCareer(career);
        return career;
    }

    /**
     * Deletes the given career, if it exists.
     */
    @DeleteMapping("/career")
    public String deleteCareer(@RequestBody UserCareer career, Authentication auth) {
        logger.debug("DELETE /career/ accessed by: " + auth.getName());
        service.deleteById(career.getUsername());
        return "successfully deleted career for user with user name = " + career.getUsername();
    }

    /**
     * Changes the amount of CO2 saved for the given career.
     */
    // Say, isn't this redundant with POST /career already in place?
    @PostMapping("/careerupdate")
    public UserCareer updateCareer(@RequestBody UserCareer career, Authentication auth) {
        logger.debug("POST /careerupdate/ accessed by: " + auth.getName());
        service.updateCareer(career);
        return career;
    }

}
