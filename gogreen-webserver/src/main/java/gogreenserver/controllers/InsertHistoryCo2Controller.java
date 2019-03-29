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

import java.time.Duration;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/api")
public class InsertHistoryCo2Controller {

    private final Logger logger;
    private InsertHistoryCo2Service insertHistoryCo2Service;

    @Autowired
    public InsertHistoryCo2Controller(InsertHistoryCo2Service insertHistoryCo2Service,
                                      Logger logger) {
        this.insertHistoryCo2Service = insertHistoryCo2Service;
        this.logger = logger;
    }

    /**
     * The endpoint for retrieving the most recent two activities of a user.
     * @param userName username.
     * @return a response entity.
     */
    @GetMapping(value = "/insertHistory/{user_Name}")
    public ResponseEntity<List<InsertHistoryCo2>> findRecentTwoByUserName(
        @PathVariable("user_Name") String userName) {
        logger.debug("GET /insertHistory/" + userName);
        return new ResponseEntity<List<InsertHistoryCo2>>(this.insertHistoryCo2Service
            .findRecentTwoByUserName(userName), HttpStatus.OK);
    }

    /**
     * The end point for retrieving the amount of activities of a user.
     * @param userName username.
     * @return a response entity.
     */
    @GetMapping(value = "/insertHistory/amount/{user_Name}")
    public ResponseEntity<String> findActivityAmountByUserName(
        @PathVariable("user_Name") String userName) {
        logger.debug("GET /insertHistory/" + userName);
        return new ResponseEntity<String>(String.valueOf(this.insertHistoryCo2Service
            .findAllByUserName(userName).size()), HttpStatus.OK);
    }

    /**
     * The endpoint for retrieving active days of a user, which is calculated by subtracting
     * the date of first insertion from the data of the last insertion.
     * @param userName username.
     * @return a response entity.
     */
    @GetMapping(value = "/insertHistory/days/{user_Name}")
    public ResponseEntity<String> findActiveDaysByUserName(
        @PathVariable("user_Name") String userName) {
        logger.debug("GET /insertHistory/" + userName);
        List<InsertHistoryCo2> list = this.insertHistoryCo2Service.findAllByUserName(userName);
        list.sort(Comparator.comparing(InsertHistoryCo2::getInsertDate).reversed());
        long days = Duration
            .between(list.get(0).getInsertDate(), list.get(list.size() - 1)
                .getInsertDate()).toDays();
        return new ResponseEntity<>(String.valueOf(days), HttpStatus.OK);

    }

}
