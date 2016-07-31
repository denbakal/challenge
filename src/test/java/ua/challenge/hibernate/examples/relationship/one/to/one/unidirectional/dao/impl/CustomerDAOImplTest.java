package ua.challenge.hibernate.examples.relationship.one.to.one.unidirectional.dao.impl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import ua.challenge.DemoAppApplication;
import ua.challenge.config.HibernateConfig;
import ua.challenge.hibernate.examples.relationship.one.to.one.unidirectional.dao.CustomerDAO;
import ua.challenge.hibernate.examples.relationship.one.to.one.unidirectional.entity.Address;
import ua.challenge.hibernate.examples.relationship.one.to.one.unidirectional.entity.Customer;

import static org.junit.Assert.*;

/**
 * Created by d.bakal on 7/31/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {DemoAppApplication.class, HibernateConfig.class})
@TransactionConfiguration(defaultRollback = false)
public class CustomerDAOImplTest {

    @Autowired
    private CustomerDAO customerDAO;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    @Transactional
    public void testSave() throws Exception {
        Customer customer = new Customer();
        Address address = new Address();
        address.setStreet1("Lesnaya 7b");
        address.setCity("Kiev");
        address.setState("Kiev");
        address.setZipcode("08300");
        address.setCountry("Ukraine");

        customer.setFirstName("Test");
        customer.setLastName("Test");
        customer.setEmail("test@gmail.com");
        customer.setPhoneNumber("050 322 60 99");
        customer.setAddress(address);

        customerDAO.save(customer);
    }
}