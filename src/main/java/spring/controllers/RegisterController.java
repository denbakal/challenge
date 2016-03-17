package main.java.spring.controllers;


import main.java.spring.common.Login;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    private String register(@RequestBody Login login) {
        System.out.println(login.getBirthDate());

        return login.getName().equalsIgnoreCase("Vasya") ? "1" : "0";
    }
}
