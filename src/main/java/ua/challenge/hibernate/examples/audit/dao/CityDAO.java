package ua.challenge.hibernate.examples.audit.dao;

import ua.challenge.hibernate.examples.audit.entity.City;

/**
 * Created by d.bakal on 7/24/2016.
 */
public interface CityDAO {
    void save(City city);
    void updateState(long id, String state);
    void delete(City city);
}
