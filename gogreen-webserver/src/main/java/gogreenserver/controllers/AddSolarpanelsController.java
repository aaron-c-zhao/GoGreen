package gogreenserver.controllers;

import gogreenserver.entity.AddSolarpanels;
import gogreenserver.services.AddSolarpanelsService;
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
public class AddSolarpanelsController {

    private final Logger logger;
    private AddSolarpanelsService addSolarpanelsService;

    @Autowired
    public AddSolarpanelsController(AddSolarpanelsService addSolarpanelsService, Logger logger) {
        this.addSolarpanelsService = addSolarpanelsService;
        this.logger = logger;
    }

    @GetMapping(value = "/addSolarpanels")
    public ResponseEntity<List<AddSolarpanels>> findAll() {
        return new ResponseEntity<List<AddSolarpanels>>(this.addSolarpanelsService.findAll(),
            HttpStatus.OK);
    }
    
    /**
     * Retrieve a single solar panel entry.
     * @param username the owner.
     * @return The entry, or if not found, an error message.
     */
    @GetMapping(value = "/addSolarpanel/{username}")
    public ResponseEntity<Object> findbyUsername(@PathVariable("username") String username) {
        AddSolarpanels res = addSolarpanelsService.findById(username).orElse(null);
        if (res != null) {
            return new ResponseEntity<Object>(res, HttpStatus.OK);
        } else {
            return new ResponseEntity<Object>(username + " has no solar panels",
                    HttpStatus.NOT_FOUND);
        }
    }

    /**
     * This end-point is used to create a new entry in the "addSolarpanel" table in
     * DB.
     *
     * @param userName       This is the users UserName.
     * @param addSolarpanels This is the addSolarpanels Object.
     * @return responseEntity of type string and status code OK if successful.
     */
    @PostMapping(value = "/addSolarpanel")
    public ResponseEntity<String> createSolarpanel(
        @RequestHeader(value = "userName") String userName,
        @RequestBody AddSolarpanels addSolarpanels, Authentication auth) {
        logger.debug("POST /addSolarpanel/ with userName header \"" + userName + "\" accessed by: "
            + auth.getName());
        addSolarpanels.setUserName(userName);
        this.addSolarpanelsService.createAddSolarpanels(addSolarpanels);
        return new ResponseEntity<String>(
            "Successfully saved solarpanel entry for user :" + userName, HttpStatus.OK);
    }
}
