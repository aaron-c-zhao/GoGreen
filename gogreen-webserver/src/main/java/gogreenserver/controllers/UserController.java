package gogreenserver.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gogreenserver.entity.User;
import gogreenserver.entity.UserCareer;
import gogreenserver.services.UserCareerService;
import gogreenserver.services.UserService;

@RestController
@RequestMapping("/api")
public class UserController {

    private UserService userService;
    private UserCareerService userCareerService;

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

//    @PostMapping("/users")
//    public User createNewUser(@RequestParam String username,
//                              @RequestParam String password,
//                              @RequestParam String email,
//                              @RequestParam String firstname,
//                              @RequestParam String lastname){
//
//        User user = new User();
//        user.setUsername(username);
//        user.setPassword(password);
//        user.setEmail(email);
//        user.setFirstName(firstname);
//        user.setLastName(lastname);
//        return userService.createUser(user);
//    }

    // This method creates a new User entry in the "User" table 
    @PostMapping("/user")
    public User addUser (@RequestBody User theUser) {
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
    
//     This is a deleteUserById endoing that uses JSON for communication 
    @DeleteMapping("/user")
    public String deleteUser(@RequestBody User theUser) {
    	String userName = theUser.getUsername();
    	userService.deleteById(userName);
    	return "successfully deleted user with user name = " + userName;
    }

}
