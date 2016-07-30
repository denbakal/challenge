package ua.challenge.hibernate.examples.audit.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.challenge.hibernate.examples.audit.dao.CityDAO;
import ua.challenge.hibernate.examples.audit.entity.City;

/**
 * Created by d.bakal on 7/24/2016.
 */
@Repository
public class CityDAOImpl implements CityDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(City city) {
        sessionFactory.getCurrentSession().save(city);
    }

    @Override
    public void updateState(long id, String state) {
        City currentCity = (City) sessionFactory.getCurrentSession().load(City.class, id);
        currentCity.setState(state);
        sessionFactory.getCurrentSession().update(currentCity);
    }

    @Override
    public void delete(City city) {
        sessionFactory.getCurrentSession().delete(city);
    }
}
