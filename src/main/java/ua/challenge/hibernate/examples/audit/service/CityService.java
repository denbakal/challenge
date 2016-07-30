package ua.challenge.hibernate.examples.audit.service;

import ua.challenge.hibernate.examples.audit.entity.City;

/**
 * Created by d.bakal on 7/24/2016.
 */
public interface CityService {
    void save(City city);
    void updateState(long id, String state);
    void delete(City city);
}
