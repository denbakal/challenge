package ua.challenge.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by d.bakal on 3/13/2016.
 */
@RestController
public class LogoutController {
    @Value("${current.realm}")
    private String realm;

    /**
     * Logout for HTTP Basic & Digest authenticate
     * */
    @RequestMapping(value = "/logout")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        response.setHeader("WWW-Authenticate", "Basic realm=\"" + realm + "\"");
    }

    /*@RequestMapping(value = "/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
    }*/

    @RequestMapping(value = "/logoutSuccess")
    public String logoutSuccess() {
        return "logoutSuccess";
    }
}
