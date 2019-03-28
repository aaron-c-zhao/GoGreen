package gogreenserver.controllers;

import gogreenserver.entity.User;
import gogreenserver.services.UserService;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/api")
public class UserController {
    private final Logger logger;
    private UserService userService;

    @Autowired
    public UserController(UserService userService, Logger logger) {
        this.userService = userService;
        this.logger = logger;
    }

    @GetMapping("/login")
    public void login() {
        logger.debug("GET /login/ accessed");
    }


    /**
     * This method will go to database and try to find the user by the user name.
     *
     * @param userName user name.
     * @return if userName is found in the database, it will return a ResponseEntity
     *     which body is "success", otherwise another entity will be returned
     *     with the body being "fail".
     */
    @GetMapping("/user/findUser/{user_name}")
    public ResponseEntity<String> findUser(@PathVariable("user_name") String userName) {
        logger.debug("GET /user/findusers/" + userName + "/ accessed");
        User user = userService.findById(userName).orElse(null);
        if (user != null) {
            return new ResponseEntity<String>("success", HttpStatus.OK);
        }
        return new ResponseEntity<String>("fail", HttpStatus.OK);
    }

    /**
     * This method creates a new User entry in the "User" table.
     */
    @PostMapping("/createUser")
    public ResponseEntity<User> addUser(@RequestBody User theUser) {
        logger.debug("POST /createUser/ accessed");
        userService.createUser(theUser);
        return new ResponseEntity<>(theUser, HttpStatus.OK);
    }

    @PostMapping("/createUser/upload")
    public ResponseEntity<String> uploadPhoto(@RequestParam("profile_pic") MultipartFile file, @RequestParam("profile_name") String username) {
        String response = "";
        try {
            userService.save(file, username);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("Error saving photo", e);
            response = "error";
        }
        response = "success";
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
