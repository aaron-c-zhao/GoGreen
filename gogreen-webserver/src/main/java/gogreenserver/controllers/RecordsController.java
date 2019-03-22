package gogreenserver.controllers;

import gogreenserver.entity.Records;
import gogreenserver.services.RecordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class RecordsController {

    private RecordsService recordsService;

    @Autowired
    public RecordsController(RecordsService recordsService){
        this.recordsService = recordsService;
    }

    @GetMapping(value = "/records")
    public List<Records> findAll(){
        return this.recordsService.findAll();
    }

    @GetMapping(value = "/record/{user_Name}")
    public Optional<Records> findById(@PathVariable("user_Name") String userName){
       return this.recordsService.findById(userName);
    }
}
