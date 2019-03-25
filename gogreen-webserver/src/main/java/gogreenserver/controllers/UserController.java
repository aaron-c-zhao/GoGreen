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

import java.util.List;

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

    // This method returns a list of ALL the users that are present in the database
    @GetMapping("/users")
    public List<User> getAllUsers(Authentication auth) {
        logger.debug("GET /users/ accessed by: " + auth.getName());
        return userService.findAll();
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
        return new ResponseEntity<String>("fail", HttpStatus.OK);
    }

    /**
     * This method creates a new User entry in the "User" table.
     */
    @PostMapping("/createUser")
    public User addUser(@RequestBody User theUser) {
        logger.debug("POST /createUser/ accessed");
        userService.createUser(theUser);
        return theUser;
    }

    /**
     * this is a deleteUserById that does not use JSON but simply uses the given
     * user_name from the URL to find the user and delete them.
     */
    @DeleteMapping("/user/{user_name}")
    public String deleteUserById(@PathVariable("user_name") String userName, Authentication auth) {
        logger.debug("DELETE /user/{user_name}/ accessed by: " + auth.getName());
        userService.deleteById(userName);
        return "successfully deleted user with user name = " + userName;
    }

    /**
     * Endpoint that uses JSON for communication.
     *
     * @param theUser the JSON passed to this method must be in User Format
     * @return success string
     */
    @DeleteMapping("/user")
    public String deleteUser(@RequestBody User theUser, Authentication auth) {
        logger.debug("DELETE /user/ accessed by: " + auth.getName());
        String userName = theUser.getUsername();
        userService.deleteById(userName);
        return "successfully deleted user with user name = " + userName;
    }

}
