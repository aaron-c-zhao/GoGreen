package gogreenserver.services;

import gogreenserver.entity.UserCareer;
import gogreenserver.repositories.UserCareerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserCareerService {

    private UserCareerRepository UserCareerRepo;

    @Autowired
    public UserCareerService(UserCareerRepository UserCareerRepo) {
        this.UserCareerRepo = UserCareerRepo;
    }

    public List<UserCareer> findAll() {
        return UserCareerRepo.findAll();
    }

    public void createUserCareer(UserCareer career) {
        UserCareerRepo.save(career);
    }

    public Optional<UserCareer> findById(String theUserName) {
        return UserCareerRepo.findById(theUserName);
    }

    public void deleteById(String theUserName) {
        UserCareerRepo.deleteById(theUserName);
    }

}
