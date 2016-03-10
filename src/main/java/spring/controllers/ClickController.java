package main.java.spring.controllers;

import main.java.spring.common.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClickController {

    @RequestMapping(value = "/click", method = RequestMethod.POST)
    private User click(@RequestBody User user) {
        user.setName("Petya");
        user.setAge(43);

        return user;
    }
}
