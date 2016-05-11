package pl.marcinbialecki.rest.app.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marcin Białecki on 2016-05-11.
 */
@Controller
public class SampleController {

    @RequestMapping(value = "/user_list", method = RequestMethod.GET)
    public ModelAndView getListUsersView() {
        ModelMap model = new ModelMap();
        List<String> users = new ArrayList<>();
        users.add("First");
        users.add("Second");
        model.addAttribute("users", users);
        return new ModelAndView("user_list", model);
    }
}
