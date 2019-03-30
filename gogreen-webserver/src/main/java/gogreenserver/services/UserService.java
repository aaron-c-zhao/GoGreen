package gogreenserver.services;

import gogreenserver.entity.User;
import gogreenserver.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Optional;

import javax.imageio.ImageIO;

@Service
public class UserService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder bcryptPasswordEncoder;

    /**
     * Autowired constructor. What else is there to say?
     */
    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder bcryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bcryptPasswordEncoder = bcryptPasswordEncoder;
    }

    public User createUser(User user) {
        user.setPassword(bcryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public Optional<User> findById(String theUserName) {
        return userRepository.findById(theUserName);
    }

    public void deleteUser(String user) {
        userRepository.deleteById(user);
    }
    
    /**.
     * converting the multipartfile recieved by http request into an image
     * and writing this image inside User_photos folder in server side
     * @param file the photo to save on disk
     * @throws IOException image could not be written
     */

    public void save(MultipartFile file, String userName) throws IOException {
        BufferedImage image = ImageIO.read(file.getInputStream());
        File toStore = new File("C:\\Users\\prund\\Programare\\OOP_Project\\gogreen-webserver\\src"
                + "\\main\\User_photos\\" + userName + ".jpg");
        toStore.createNewFile();
        ImageIO.write(image, "jpg", toStore);
    }


}