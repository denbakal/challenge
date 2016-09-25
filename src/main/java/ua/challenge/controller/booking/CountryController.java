package ua.challenge.controller.booking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.challenge.db.entity.Country;
import ua.challenge.service.CountryService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.Optional;

/**
 * Created by d.bakal on 6/11/2016.
 */
@RestController
@RequestMapping("/country")
public class CountryController {

    @Autowired
    private CountryService countryService;

    @RequestMapping(method = RequestMethod.POST)
    public void save(@RequestParam String country, HttpServletRequest request, HttpServletResponse response) {
        HttpSession httpSession = request.getSession();

        String token = (String) httpSession.getAttribute("VALID-TOKEN");
        Optional<Cookie> cookie = Arrays.stream(request.getCookies())
                .filter(c -> "VALID-TOKEN".equals(c.getName())).findFirst();

        System.out.println("token = " + token);
        System.out.println("tokenFromClient = " + cookie.get().getValue());

        if (!token.isEmpty() && cookie.isPresent()) {
            String tokenFromClient = cookie.get().getValue();

            if (token.equals(tokenFromClient)) {
                Country newCountry = new Country();
                newCountry.setName(country);
                countryService.save(newCountry);
            }

            httpSession.setAttribute("VALID-TOKEN", "");
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/get")
    public void get() {
        System.out.println("countryService GET ");
    }
}
