package ua.challenge.db.dao;

import ua.challenge.db.entity.Country;
import ua.challenge.hibernate.examples.transactions.entity.Person;

import java.util.List;

/**
 * Created by d.bakal on 6/11/2016.
 */
public interface CountryDAO {
    List<Country> getCountries();

    void save(Country country);
}
