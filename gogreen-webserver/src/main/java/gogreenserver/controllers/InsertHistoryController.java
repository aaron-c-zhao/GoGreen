package gogreenserver.controllers;

import gogreenserver.entity.InsertHistory;
import gogreenserver.services.InsertHistoryService;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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
    private final Logger logger;

    @Autowired
    public InsertHistoryController(InsertHistoryService insertHistoryService, Logger logger) {
        this.insertHistoryService = insertHistoryService;
        this.logger = logger;
    }

    // TODO: fix typo
    @GetMapping(value = "/insertHistorys")
    public List<InsertHistory> findAllInsertHistorys(Authentication auth) {
        logger.debug("GET /insertHistorys/ accessed by: " + auth.getName());
        return insertHistoryService.findAll();
    }

    @GetMapping(value = "/insertHistory/{user_Name}")
    public List<InsertHistory> findAllById(@PathVariable("user_Name") String userName,
            Authentication auth) {
        logger.debug("GET /insertHistory/" + userName + " accessed by: " + auth.getName());
        return this.insertHistoryService.findAllById(userName);
    }

    /**
     * This endpoint is used to create a new entry in the InsertHistory table.
     *
     * @param userName      This is the users UserName.
     * @param insertHistory This is an object of type insertHistory.
     * @param auth          This is the Spring authentication data.
     * @return responseEntity of type String with status code OK if successful.
     */
    @PostMapping(value = "/insertHistory")
    public ResponseEntity<String> createInsertHistory(
            @RequestHeader(value = "userName") String userName,
            @RequestBody InsertHistory insertHistory, Authentication auth) {
        logger.debug("POST /insertHistory/ with username header \"" + userName + "\" accessed by: "
                + auth.getName());
        insertHistory.setUserName(userName);
        this.insertHistoryService.createInsertHistory(insertHistory);
        return new ResponseEntity<String>("Successfully insertHistory for user : " + userName,
                HttpStatus.OK);
    }

}
