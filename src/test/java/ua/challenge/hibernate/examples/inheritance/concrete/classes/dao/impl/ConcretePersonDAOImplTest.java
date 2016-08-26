package ua.challenge.hibernate.examples.inheritance.concrete.classes.dao.impl;

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
import ua.challenge.hibernate.examples.inheritance.concrete.classes.dao.ConcreteEmployeeDAO;
import ua.challenge.hibernate.examples.inheritance.concrete.classes.dao.ConcreteOwnerDAO;
import ua.challenge.hibernate.examples.inheritance.concrete.classes.dao.ConcretePersonDAO;
import ua.challenge.hibernate.examples.inheritance.concrete.classes.entity.ConcreteEmployee;
import ua.challenge.hibernate.examples.inheritance.concrete.classes.entity.ConcreteOwner;
import ua.challenge.hibernate.examples.inheritance.concrete.classes.entity.ConcretePerson;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by Денис on 26.08.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {DemoAppApplication.class, HibernateConfig.class})
@TransactionConfiguration(defaultRollback = false)
public class ConcretePersonDAOImplTest {

    @Autowired
    private ConcretePersonDAO concretePersonDAO;

    @Autowired
    private ConcreteEmployeeDAO concreteEmployeeDAO;

    @Autowired
    private ConcreteOwnerDAO concreteOwnerDAO;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    @Transactional
    public void save() throws Exception {
        ConcretePerson person = new ConcretePerson("Steve", "Balmer");
        concretePersonDAO.save(person);

        ConcreteEmployee employee = new ConcreteEmployee("James", "Gosling", new Date(), "Marketing");
        concreteEmployeeDAO.save(employee);

        ConcreteOwner owner = new ConcreteOwner("Bill", "Gates", 300L, 20L);
        concreteOwnerDAO.save(owner);
    }

}