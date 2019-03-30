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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import javax.ws.rs.Consumes;

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

    /**.
     * Recieving a multipartfile object from client (client profile photo)
     * and eventually saving it as an
     * image on the server side in gogreen-webserver/src/main/User_photos
     * @param file the photo user picked in multipartfile format
     * @param userName name of the user
     * @return the response of the http exchange
     * @throws IOException error while saving the image
     */
    @PostMapping("/createUser/upload")
    @Consumes("multipart/form-data")
    public ResponseEntity<String> uploadPhoto(@RequestParam("profile_pic") MultipartFile file,
                                              @RequestParam("username") String userName) {
        String response = "";
        try {
            userService.save(file, userName);
            response = "success";
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("Error saving photo", e);
            response = "error";
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
