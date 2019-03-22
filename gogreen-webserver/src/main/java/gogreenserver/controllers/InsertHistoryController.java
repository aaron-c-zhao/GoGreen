package gogreenserver.controllers;

import gogreenserver.entity.InsertHistory;
import gogreenserver.services.InsertHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class InsertHistoryController {

    private InsertHistoryService insertHistoryService;

    @Autowired
    public InsertHistoryController(InsertHistoryService insertHistoryService){
        this.insertHistoryService = insertHistoryService;
    }

    @GetMapping(value = "/insertHistorys")
    public List<InsertHistory> findAllInsertHistorys(){
        return insertHistoryService.findAll();
    }

    @GetMapping(value = "/insertHistory/{userName}")
    public List<InsertHistory> findAllById(@PathVariable("userName") String userName){
        return this.insertHistoryService.findAllById(userName);
    }

    @PostMapping(value = "/insertHistory")
    public ResponseEntity<String> createInsertHistory(@RequestHeader(value="userName") String userName, @RequestBody InsertHistory insertHistory){
        insertHistory.setUserName(userName);
        this.insertHistoryService.createInsertHistory(insertHistory);
        return new ResponseEntity<String>("Successfully returned insertHistory for user : "+userName, HttpStatus.OK);
    }

}
