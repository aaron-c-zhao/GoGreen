package gogreenserver.services;

import gogreenserver.entity.UserCareer;
import gogreenserver.repositories.UserCareerRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service layer of user entity.
 */
@Service
public class UserCareerService {

    private static final Logger LOGGER = LogManager.getLogger("GoGreen");

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

    /**
     * update user's career.
     *
     * @param newcareer an usercareer instance which contains the information to be
     *                  updated.
     * @return If successfully update, will return true. otherwise, false.
     */
    public boolean updateCareer(UserCareer newcareer) {
        UserCareer oldcareer = userCareerRepo.findById(newcareer.getUsername()).orElse(null);
        LOGGER.debug("Career updated of user " + newcareer.getUsername());

        if (oldcareer != null) {

            LOGGER.debug("Old: " + oldcareer.getco2saved());
            LOGGER.debug("New:" + newcareer.getco2saved());

            oldcareer.setco2saved((int) newcareer.getco2saved());
            userCareerRepo.saveAndFlush(oldcareer);
            return true;
        } else {
            LOGGER.warn(newcareer.getUsername() + " has no career.");
            return false;
        }
    }

}
