package pl.marcinbialecki.rest.app.mvc;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marcin Białecki on 2016-05-11.
 */
@RestController
public class SampleRestController {

    @RequestMapping(value = "/user/", method = RequestMethod.GET)
    public ResponseEntity<List<String>> listAllUsers() {
        List<String> users = new ArrayList<>();

        users.add("First user");

        return new ResponseEntity<List<String>>(users, HttpStatus.OK);
    }
}
