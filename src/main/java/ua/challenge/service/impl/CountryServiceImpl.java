package ua.challenge.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.challenge.db.dao.CountryDAO;
import ua.challenge.db.entity.Country;
import ua.challenge.service.CountryService;

/**
 * Created by d.bakal on 6/11/2016.
 */
@Service
public class CountryServiceImpl implements CountryService {
    @Autowired
    private CountryDAO countryDAO;

    @Override
    public void save(Country country) {
        countryDAO.save(country);
    }
}
