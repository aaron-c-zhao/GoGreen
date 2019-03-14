package gogreenserver.controllers;

import gogreenserver.entity.User;
import gogreenserver.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // This method returns a list of ALL the users that are present in the database
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    // This method returns a single user according to their UserName
    @GetMapping("/user/{user_name}")
    public Optional<User> getUserById(@PathVariable("user_name") String userName) {
        return userService.findById(userName);
    }

    // This method creates a new User entry in the "User" table
    @PostMapping("/user")
    public User addUser(@RequestBody User theUser) {
        userService.createUser(theUser);
        return theUser;
    }

    // this is a deleteUserById that does not use JSON
    // simply uses the given user_name from the URL to find the user and delete
    @DeleteMapping("/user/{user_name}")
    public String deleteUserById(@PathVariable("user_name") String userName) {
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
    public String deleteUser(@RequestBody User theUser) {
        String userName = theUser.getUsername();
        userService.deleteById(userName);
        return "successfully deleted user with user name = " + userName;
    }

}
