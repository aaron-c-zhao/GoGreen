package gogreenserver.controllers;

import gogreenserver.entity.User;
import gogreenserver.services.UserService;

import org.apache.logging.log4j.Logger;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.mockito.internal.util.io.IOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.ws.rs.Consumes;

@RestController
@RequestMapping("/api")
public class UserController {
    private final Logger logger;
    // set of user(name)s that have been created, but have not their profile
    // pictures set yet.
    // use this to allow unauthenticated accounts to edit pictures.
    private final Set<String> expectedaccounts;
    private UserService userService;

    /**
     * Autowired constructor, do not touch.
     */
    @Autowired
    public UserController(UserService userService, Logger logger) {
        this.userService = userService;
        this.logger = logger;
        expectedaccounts = new HashSet<String>();
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

    /**.
     * Endpoint controller for returning user profile picture from the server
     * @param username User name
     * @return User's profile picture
     * @throws IOException exception
     */
    @RequestMapping(value = "/user/photo/{username}", method = RequestMethod.GET,
            produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte []> showPhoto (@PathVariable("username") String username) throws IOException {
        logger.debug("GET/user/photo/" + username + "/ accessed");
        String pathname = "gogreen-webserver/src/main/profile_pictures/"
                + username + ".png";
        File file = new File(pathname);
        boolean exists = file.exists();
        if (exists) {
            BufferedImage bufimag = ImageIO.read(file);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ImageIO.write(bufimag, "png", byteArrayOutputStream);
            byte [] img = byteArrayOutputStream.toByteArray();
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
    public ResponseEntity<Object> addUser(@RequestBody User theUser, Authentication auth) {
        logger.debug("POST /createUser/ accessed");

        if ((auth == null || !auth.getName().equals(theUser.getUsername()))
                && userService.findById(theUser.getUsername()).isPresent()) {
            logger.warn("POST /createUser/ unauthorized update of " + theUser.getUsername());
            if (auth != null) {
                logger.warn("Attacker was logged in as: " + auth.getName());
            } else {
                logger.warn("Attacker was not logged in");
            }

            return new ResponseEntity<>("User already exists", HttpStatus.BAD_REQUEST);
        }

        userService.createUser(theUser);
        expectedaccounts.add(theUser.getUsername());
        return new ResponseEntity<>(theUser, HttpStatus.OK);
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