package gogreenserver.controllers;

import gogreenserver.entity.Records;
import gogreenserver.services.RecordsService;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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
    private final Logger logger;

    @Autowired
    public RecordsController(RecordsService recordsService, Logger logger) {
        this.recordsService = recordsService;
        this.logger = logger;
    }

    @GetMapping(value = "/records")
    public ResponseEntity<List<Records>> findAll(Authentication auth) {
        logger.debug("GET /records/ accessed by: " + auth.getName());
        return new ResponseEntity<>(this.recordsService.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/record/{user_Name}")
    public ResponseEntity<Optional<Records>> findById(@PathVariable("user_Name") String userName,
            Authentication auth) {
        logger.debug("GET /record/" + userName + " accessed by: " + auth.getName());
        return new ResponseEntity<>(this.recordsService.findById(userName), HttpStatus.OK);
    }
}
