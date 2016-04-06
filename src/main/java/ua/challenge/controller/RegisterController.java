package ua.challenge.controller;


import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ua.challenge.common.Login;

@RestController
public class RegisterController {

    @RequestMapping(value = "#/register", method = RequestMethod.POST)
    private String register(@RequestBody Login login) {
        return login.getName().equalsIgnoreCase("Vasya") ? "1" : "0";
    }
}