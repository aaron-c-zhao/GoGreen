package gogreenserver.services;

import gogreenserver.entity.User;
import gogreenserver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }
    
    public Optional<User> findById(String theUserName) {
    	return userRepository.findById(theUserName);
    }
    
    public void deleteById(String theUserName) {
    	userRepository.deleteById(theUserName);
    }

}
