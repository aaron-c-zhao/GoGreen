package gogreenserver.controllers;

import gogreenserver.entity.AddSolarpanels;
import gogreenserver.services.AddSolarpanelsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AddSolarpanelsController {

    private AddSolarpanelsService addSolarpanelsService;

    @Autowired
    public AddSolarpanelsController(AddSolarpanelsService addSolarpanelsService){
        this.addSolarpanelsService = addSolarpanelsService;
    }

    @GetMapping(value = "/addSolarpanels")
    public List<AddSolarpanels> findAll(){
        return this.addSolarpanelsService.findAll();
    }

    @PostMapping(value = "/addSolarpanel")
    public ResponseEntity<String> createSolarpanel(@RequestHeader(value = "userName") String userName, @RequestBody AddSolarpanels addSolarpanels){
        addSolarpanels.setUserName(userName);
        this.addSolarpanelsService.createAddSolarpanels(addSolarpanels);
        return new ResponseEntity<String>("Successfully saved solarpanel entry for user :"+userName, HttpStatus.OK);
    }
}
