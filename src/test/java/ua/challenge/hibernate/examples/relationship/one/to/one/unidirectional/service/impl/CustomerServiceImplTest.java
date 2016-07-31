package ua.challenge.hibernate.examples.relationship.one.to.one.unidirectional.service.impl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.challenge.DemoAppApplication;
import ua.challenge.config.HibernateConfig;
import ua.challenge.hibernate.examples.relationship.one.to.one.unidirectional.entity.Address;
import ua.challenge.hibernate.examples.relationship.one.to.one.unidirectional.entity.Customer;
import ua.challenge.hibernate.examples.relationship.one.to.one.unidirectional.service.CustomerService;

import static org.junit.Assert.*;

/**
 * Created by d.bakal on 8/1/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {DemoAppApplication.class, HibernateConfig.class})
public class CustomerServiceImplTest {

    @Autowired
    private CustomerService customerService;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
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

        customerService.save(customer);
    }
}