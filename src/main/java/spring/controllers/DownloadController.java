package main.java.spring.controllers;

import main.java.spring.common.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DownloadController {

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    private String getFile(@RequestBody User user) {

        System.out.println(user);
//        ObjectMapper objectMapper = new ObjectMapper();
//
//        try {
//            String val = objectMapper.writeValueAsString(user);
//
//            System.out.println(val);
//
//            User user1 = objectMapper.readValue(val, User.class);
//            System.out.println(user1.getName());
//
//        } catch (Exception e) {
//            System.out.println("whoops");
//        }

        return "param";
    }
}
