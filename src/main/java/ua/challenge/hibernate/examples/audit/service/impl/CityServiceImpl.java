package ua.challenge.hibernate.examples.audit.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.challenge.hibernate.examples.audit.dao.CityDAO;
import ua.challenge.hibernate.examples.audit.entity.City;
import ua.challenge.hibernate.examples.audit.service.CityService;

/**
 * Created by d.bakal on 7/24/2016.
 */
@Service
public class CityServiceImpl implements CityService {
    @Autowired
    private CityDAO cityDAO;


    @Override
    @Transactional
    public void save(City city) {
        cityDAO.save(city);
    }

    @Override
    @Transactional
    public void updateState(long id, String state) {
        cityDAO.updateState(id, state);
    }

    @Override
    @Transactional
    public void delete(City city) {
        cityDAO.delete(city);
    }
}
