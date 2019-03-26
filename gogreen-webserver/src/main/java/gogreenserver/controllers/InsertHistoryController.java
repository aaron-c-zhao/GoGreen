package gogreenserver.controllers;

import gogreenserver.entity.InsertHistory;
import gogreenserver.services.InsertHistoryService;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    /**
     * Returns everything everyone has ever done.
     */
    @GetMapping(value = "/insertHistories")
    public ResponseEntity<List<InsertHistory>> findAllInsertHistorys(Authentication auth) {
        logger.debug("GET /insertHistories/ accessed by: " + auth.getName());
        return new ResponseEntity<List<InsertHistory>>(insertHistoryService.findAll(),
            HttpStatus.OK);
    }


    /**
     * This endpoint is used to create a new entry in the InsertHistory table.
     *
     * @param insertHistory This is an object of type insertHistory.
     * @return responseEntity of type String with status code OK if successful.
     */
    @PostMapping(value = "/insertHistory")
    public ResponseEntity<String> createInsertHistory(@RequestBody InsertHistory insertHistory) {
        this.insertHistoryService.createInsertHistory(insertHistory);
        return new ResponseEntity<String>("Successfully insertHistory for user : " + insertHistory.getUserName(),
            HttpStatus.OK);
    }

}
