package ua.challenge.controller.booking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.challenge.db.entity.Country;
import ua.challenge.service.CountryService;

/**
 * Created by d.bakal on 6/11/2016.
 */
@RestController
@RequestMapping("/country")
public class CountryController {

    @Autowired
    private CountryService countryService;

    @RequestMapping(method = RequestMethod.POST)
    public void save(@RequestParam String country) {
        Country newCountry = new Country();
        newCountry.setName(country);
        countryService.save(newCountry);
    }
}
