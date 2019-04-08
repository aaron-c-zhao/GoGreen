package gogreenserver.controllers;

import gogreenserver.entity.User;
import gogreenserver.services.UserService;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Optional;

import javax.imageio.ImageIO;
import javax.ws.rs.Consumes;

@RestController
@RequestMapping("/api")
public class UserController {
    private final Logger logger;
    private UserService userService;

    /**
     * Autowired constructor, do not touch.
     */
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
     * Returns the url to be used a profile picture.
     * 
     * @param username the user to use.
     */
    @GetMapping("/user/photourl/{user}")
    public ResponseEntity<String> getPicUrl(@PathVariable("user") String username,
            Authentication auth) {

        logger.debug("GET /user/photourl/" + username + " accessed by :" + auth.getName());

        Optional<User> user = userService.findById(username);
        return new ResponseEntity<String>(user.isPresent() ? user.get().getPfpUrl() : "",
                user.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    /**
     * Sets the user picture.
     * 
     * @param username the user in question
     * @param body     A string containing the url.
     */
    @PostMapping("/user/photourl/{user}")
    public ResponseEntity<String> setPicUrl(@PathVariable("user") String username,
            @RequestBody String body, Authentication auth) {

        logger.debug("GET /user/photourl/" + username + " accessed by :" + auth.getName());

        if (!auth.getName().equals(username)) {
            return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
        }

        User user = userService.findById(username).orElse(null);
        if (user == null) {
            return new ResponseEntity<String>("User not found", HttpStatus.NOT_FOUND);
        }
        user.setPfpUrl(body);
        userService.updateUser(user);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }

    /**
     * . Endpoint controller for returning user profile picture from the server
     * 
     * @param username User name
     * @return User's profile picture
     * @throws IOException exception
     */
    @GetMapping(value = "/user/photo/{username}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> showPhoto(@PathVariable("username") String username)
            throws IOException {
        logger.debug("GET/user/photo/" + username + "/ accessed");
        String pathname = "gogreen-webserver/src/main/profile_pictures/" + username + ".png";
        File file = new File(pathname);
        boolean exists = file.exists();
        if (exists) {
            BufferedImage bufimag = ImageIO.read(file);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ImageIO.write(bufimag, "png", byteArrayOutputStream);
            byte[] img = byteArrayOutputStream.toByteArray();
            return new ResponseEntity<>(img, HttpStatus.OK);
        } else {
            byte[] resp = new byte[2];
            return new ResponseEntity<>(resp, HttpStatus.OK);
        }
    }

    /**
     * This method creates a new User entry in the "User" table.
     */
    @PostMapping("/createUser")
    public ResponseEntity<Object> addUser(@RequestBody User theUser) {
        logger.debug("POST /createUser/ accessed");

        if (userService.findById(theUser.getUsername()).isPresent()) {
            return new ResponseEntity<>("User already exists", HttpStatus.BAD_REQUEST);
        }

        userService.createUser(theUser);
        return new ResponseEntity<>(theUser, HttpStatus.OK);
    }

    /**
     * This method updates the existing users.
     */
    @PostMapping("/updateUser")
    public ResponseEntity<String> updateUser(@RequestBody User theUser, Authentication auth) {
        logger.debug("POST /updateUser/ accessed by: " + auth.getName());

        if (!auth.getName().equals(theUser.getUsername())) {
            return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);
        }

        if (userService.findById(theUser.getUsername()).isEmpty()) {
            // This should be impossible, right?
            return new ResponseEntity<>("User does not exist", HttpStatus.NOT_FOUND);
        }

        userService.updateUser(theUser);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    /**
     * Receives a multipart file object from client (client profile photo) and
     * eventually saves it as an image on the server side in
     * gogreen-webserver/src/main/User_photos .
     * 
     * @param file     the photo user picked in multipart file format
     * @param userName name of the user
     * @return the response of the http exchange
     * @throws IOException error while saving the image
     */
    @PostMapping("/createUser/upload")
    @Consumes("multipart/form-data")
    public ResponseEntity<String> uploadPhoto(@RequestParam("profile_pic") MultipartFile file,
            @RequestParam("username") String userName) {

        logger.debug("POST /createUser/upload/" + userName);
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