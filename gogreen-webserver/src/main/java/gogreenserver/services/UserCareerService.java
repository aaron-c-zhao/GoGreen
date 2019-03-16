package gogreenserver.services;

import gogreenserver.entity.UserCareer;
import gogreenserver.repositories.UserCareerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserCareerService {

    private UserCareerRepository userCareerRepo;

    @Autowired
    public UserCareerService(UserCareerRepository userCareerRepo) {
        this.userCareerRepo = userCareerRepo;
    }

    public List<UserCareer> findAll() {
        return userCareerRepo.findAll();
    }

    public void createUserCareer(UserCareer career) {
        userCareerRepo.save(career);
    }

    public Optional<UserCareer> findById(String theUserName) {
        return userCareerRepo.findById(theUserName);
    }

    public void deleteById(String theUserName) {
        userCareerRepo.deleteById(theUserName);
    }

    public boolean updataCareer(UserCareer userCareer){
        UserCareer career = userCareerRepo.findById(userCareer.getusername()).orElse(null);
        System.out.println(userCareer.getusername());
        if(career != null){
            career.setco2saved((int)userCareer.getco2saved());
            userCareerRepo.saveAndFlush(career);
            return true;
        }
        else return false;
    }

}
