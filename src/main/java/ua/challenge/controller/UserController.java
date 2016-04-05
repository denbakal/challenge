package ua.challenge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by d.bakal on 3/19/2016.
 */
@RestController
@RequestMapping("/user")
public class UserController {
//    @Autowired
//    @Qualifier("sessionRegistry")
//    private SessionRegistry sessionRegistry;
//
//    @RequestMapping(value = "/active")
//    public List<String> getActiveUsers() {
//        List<String> users = new ArrayList<>();
//        List<Object> principals = sessionRegistry.getAllPrincipals();
//
//        for (Object principal : principals) {
//            User user = (User) principal;
//
//            for (SessionInformation sessionInformation : sessionRegistry.getAllSessions(user, false)) {
//                System.out.println("sessionInformation = " + sessionInformation.getSessionId() + " - " + sessionInformation.getLastRequest());
//            }
//
//            users.add(user.getUsername());
//        }
//
//        return users;
//    }
}
