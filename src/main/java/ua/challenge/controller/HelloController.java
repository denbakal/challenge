package ua.challenge.controller;

import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @RequestMapping(value = "/ids", method = RequestMethod.GET)
    public @ResponseBody String getIds(@RequestBody List<Long> ids) {
        ids.forEach(System.out::println);
        return "Ok";
    }
}
