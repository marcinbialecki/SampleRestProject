package pl.marcinbialecki.rest.app.mvc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.marcinbialecki.rest.core.logging.LogExecution;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marcin Białecki on 2016-05-11.
 */
@RestController
public class SampleRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SampleRestController.class);

    @LogExecution
    @RequestMapping(value = "/user/", method = RequestMethod.GET)
    public ResponseEntity<List<String>> listAllUsers() {
        LOGGER.debug("Call method {}", "listAllUsers");
        List<String> users = new ArrayList<>();

        users.add("First user");

        return new ResponseEntity<List<String>>(users, HttpStatus.OK);
    }
}
