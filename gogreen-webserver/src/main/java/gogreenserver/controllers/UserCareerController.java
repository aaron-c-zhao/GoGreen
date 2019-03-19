package gogreenserver.controllers;

import gogreenserver.entity.UserCareer;
import gogreenserver.services.UserCareerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletResponse;

//import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserCareerController {

    private UserCareerService service;

    @Autowired
    public UserCareerController(UserCareerService service) {
        this.service = service;
    }

    @GetMapping("/career")
    public List<UserCareer> getAllUserCareers() {
        return service.findAll();
    }

    @GetMapping("/career/{user_name}")
    public Optional<UserCareer> getUserCareerById(@PathVariable("user_name") String userName) {
        return service.findById(userName);
    }

    @PostMapping("/career")
    public UserCareer addUserCareer(@RequestBody UserCareer career) {
        service.createUserCareer(career);
        return career;
    }

    @DeleteMapping("/career")
    public String deleteCareer(@RequestBody UserCareer career) {
        service.deleteById(career.getUsername());
        return "successfully deleted career for user with user name = " + career.getUsername();
    }

    @PostMapping("/careerupdate")
    public UserCareer updateCareer(@RequestBody UserCareer career, HttpServletResponse resp) {
        service.updateCareer(career);
        return career;
    }

}
