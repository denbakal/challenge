package ua.challenge.db.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.challenge.db.dao.CountryDAO;
import ua.challenge.db.entity.Country;

import java.util.List;

/**
 * Created by d.bakal on 6/11/2016.
 */
@Repository
public class CountryDAOImpl implements CountryDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Country> getCountries() {
        Session session = sessionFactory.openSession();
        List<Country> countries = session.createQuery("from Country").list();
        session.close();
        return countries;
    }

    @Override
    public void save(Country country) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(country);
        transaction.commit();
        session.close();
    }
}
