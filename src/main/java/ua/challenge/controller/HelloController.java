package ua.challenge.controller;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sun.plugin.liveconnect.SecurityContextHelper;

/**
 * Created by d.bakal on 3/12/2016.
 */
@RestController
@RequestMapping("/rest")
public class HelloController {
    @RequestMapping("/hello")
    private String home() {
        return "This is hidden hello page";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login() {
        System.out.println("true = " + true);
        return "hello";
    }

    @RequestMapping(value = "/secure", method = RequestMethod.GET)
    public String secure() {
        return "UserName: ";
    }
}
