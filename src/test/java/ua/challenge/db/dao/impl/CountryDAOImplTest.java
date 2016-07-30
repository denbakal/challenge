package ua.challenge.db.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.challenge.config.HibernateConfig;
import ua.challenge.db.entity.Country;

import static org.junit.Assert.*;

/**
 * Created by d.bakal on 7/17/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {HibernateConfig.class})
public class CountryDAOImplTest {

    @Autowired
    private SessionFactory sessionFactory;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testGetCountries() throws Exception {
    }

    @Test
    public void testSave() throws Exception {
        Country country = new Country();
        country.setName("Germany");

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(country);
        transaction.commit();
        Country country1 = (Country) session.load(Country.class, country.getId());
        System.out.println("country1 = " + country1);
        session.close();
    }
}