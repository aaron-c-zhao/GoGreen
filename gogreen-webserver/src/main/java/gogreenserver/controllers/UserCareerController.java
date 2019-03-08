package gogreenserver.controllers;

import gogreenserver.entity.UserCareer;
import gogreenserver.services.UserCareerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserCareerController {

    private UserCareerService service;

    @Autowired
    public UserCareerController(UserCareerService service) {
        this.service = service;
    }

    @GetMapping("/career")
    public List<UserCareer> getALLUserCareers() {
        return service.findAll();
    }

    @GetMapping("/career/{user_name}")
    public Optional<UserCareer> getUserCareerById(@PathVariable("user_name") String userName) {
        return service.findById(userName);
    }

    @PostMapping("/career")
    public String addUserCareer(@RequestBody UserCareer career) {
        service.createUserCareer(career);
        return "successfully added career for user with user name = " + career.getusername();
    }

    @DeleteMapping("/career")
    public String deleteCareer(@RequestBody UserCareer career) {
        service.deleteById(career.getusername());
        return "successfully deleted career for user with user name = " + career.getusername();
    }

}
