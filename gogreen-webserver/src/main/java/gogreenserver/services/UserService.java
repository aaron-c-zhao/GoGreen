package gogreenserver.services;

import gogreenserver.entity.User;
import gogreenserver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder bcryptPasswordEncoder;

    private AddSolarpanelsService solar;

    /**
     * Autowired constructor. What else is there to say?
     */
    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder bcryptPasswordEncoder,
            AddSolarpanelsService solar) {
        this.userRepository = userRepository;
        this.bcryptPasswordEncoder = bcryptPasswordEncoder;
        this.solar = solar;
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

    public void deleteUser(String user) {
        userRepository.deleteById(user);
    }

}
