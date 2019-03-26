package gogreenserver.controllers;

import gogreenserver.entity.InsertHistoryCo2;
import gogreenserver.services.InsertHistoryCo2Service;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class InsertHistoryCo2Controller {

    private InsertHistoryCo2Service insertHistoryCo2Service;
    private final Logger logger;

    @Autowired
    public InsertHistoryCo2Controller(InsertHistoryCo2Service insertHistoryCo2Service, Logger logger) {
        this.insertHistoryCo2Service = insertHistoryCo2Service;
        this.logger = logger;
    }

    @GetMapping(value = "/insertHistory/{user_Name}")
    public ResponseEntity<List<InsertHistoryCo2>> findRecentTwoByUserName(
        @PathVariable("user_Name") String userName) {
        logger.debug("GET /insertHistory/" + userName);
        return new ResponseEntity<List<InsertHistoryCo2>>(this.insertHistoryCo2Service
            .findRecentTwoByUserName(userName), HttpStatus.OK);
    }
}
