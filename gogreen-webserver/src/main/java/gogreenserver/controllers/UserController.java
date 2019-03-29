package gogreenserver.controllers;

import gogreenserver.entity.User;
import gogreenserver.services.UserService;

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

@RestController
@RequestMapping("/api")
public class UserController {
    private UserService userService;

    private final Logger logger;

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
     *         which body is "success", otherwise another entity will be returned
     *         with the body being "fail".
     */
    @GetMapping("/user/findUser/{user_name}")
    public ResponseEntity<String> findUser(@PathVariable("user_name") String userName) {
        logger.debug("GET /user/findusers/" + userName + "/ accessed");
        User user = userService.findById(userName).orElse(null);
        if (user != null) {
            return new ResponseEntity<String>("success", HttpStatus.OK);
        }
        return new ResponseEntity<String>("fail", HttpStatus.NOT_FOUND);
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

    /**
     * Remove the user from existence.
     * 
     * @param user The user username.
     */
    @DeleteMapping("/deleteUser/{username}")
    public ResponseEntity<String> deleteUser(@PathVariable("username") String user,
            Authentication auth) {
        if (!user.equals(auth.getName())) {
            return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
        }
        logger.debug("DELETE /user/" + user + "/ accessed by " + auth);
        userService.deleteUser(user);
        return new ResponseEntity<String>("success", HttpStatus.OK);
    }
}