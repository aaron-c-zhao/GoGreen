package gogreenserver.services;

import gogreenserver.entity.User;
import gogreenserver.repositories.UserRepository;
import org.apache.logging.log4j.core.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder bcryptPasswordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder bcryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bcryptPasswordEncoder = bcryptPasswordEncoder;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User createUser(User user) {
        user.setPassword(bcryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public Optional<User> findById(String theUserName) {
        return userRepository.findById(theUserName);
    }

    public void deleteById(String theUserName) {
        userRepository.deleteById(theUserName);
    }

    /**
     *
     * @param file
     * @throws IOException
     */
    public void save(MultipartFile file) throws IOException {
//        String fileSeparator = System.getProperty("file.separator");
//        String fileLocation = "gogreen-webserver" + fileSeparator + "src" + fileSeparator
//                + "main" + fileSeparator + "User_photos" + fileSeparator + "ididit.jpg";
        BufferedImage image = ImageIO.read(file.getInputStream());
        File toStore = new File("C:\\Users\\prund\\Programare\\OOP_Project\\gogreen-webserver\\src"
                + "\\main\\User_photos\\ididit.jpg");
        toStore.createNewFile();
        ImageIO.write(image, "jpg", toStore);
    }

}
