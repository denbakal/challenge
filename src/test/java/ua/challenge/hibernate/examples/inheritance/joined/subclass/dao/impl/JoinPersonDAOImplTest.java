package ua.challenge.hibernate.examples.inheritance.joined.subclass.dao.impl;

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
import ua.challenge.hibernate.examples.inheritance.joined.subclass.dao.JoinEmployeeDAO;
import ua.challenge.hibernate.examples.inheritance.joined.subclass.dao.JoinOwnerDAO;
import ua.challenge.hibernate.examples.inheritance.joined.subclass.dao.JoinPersonDAO;
import ua.challenge.hibernate.examples.inheritance.joined.subclass.entity.JoinEmployee;
import ua.challenge.hibernate.examples.inheritance.joined.subclass.entity.JoinOwner;
import ua.challenge.hibernate.examples.inheritance.joined.subclass.entity.JoinPerson;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by Денис on 25.08.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {DemoAppApplication.class, HibernateConfig.class})
@TransactionConfiguration(defaultRollback = false)
public class JoinPersonDAOImplTest {

    @Autowired
    private JoinPersonDAO joinPersonDAO;

    @Autowired
    private JoinEmployeeDAO joinEmployeeDAO;

    @Autowired
    private JoinOwnerDAO joinOwnerDAO;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    @Transactional
    public void save() throws Exception {
        JoinPerson person = new JoinPerson("Steve", "Balmer");
        joinPersonDAO.save(person);

        JoinEmployee employee = new JoinEmployee("James", "Gosling", new Date(), "Marketing");
        joinEmployeeDAO.save(employee);

        JoinOwner owner = new JoinOwner("Bill", "Gates", 300L, 20L);
        joinOwnerDAO.save(owner);
    }

}