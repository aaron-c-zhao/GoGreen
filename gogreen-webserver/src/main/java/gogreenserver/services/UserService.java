package gogreenserver.services;

import gogreenserver.entity.User;
import gogreenserver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

    public void save(MultipartFile file, String username) throws IOException {
        String fileLocation = "C:/Users/prund/Programare/OOP_Project/gogreen-webserver/src/main/User_photos/";
        byte[] bytes = file.getBytes();
        Path path = Paths.get(fileLocation + username);
        Files.write(path, bytes);
    }

}
