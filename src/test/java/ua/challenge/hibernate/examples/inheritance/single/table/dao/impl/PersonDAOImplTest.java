package ua.challenge.hibernate.examples.inheritance.single.table.dao.impl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import ua.challenge.DemoAppApplication;
import ua.challenge.config.HibernateConfig;
import ua.challenge.hibernate.examples.inheritance.single.table.dao.EmployeeDAO;
import ua.challenge.hibernate.examples.inheritance.single.table.dao.PersonDAO;
import ua.challenge.hibernate.examples.inheritance.single.table.entity.CommonEmployee;
import ua.challenge.hibernate.examples.inheritance.single.table.entity.CommonPerson;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by Денис on 24.08.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {DemoAppApplication.class, HibernateConfig.class})
@TransactionConfiguration(defaultRollback = false)
public class PersonDAOImplTest {

    @Autowired
    @Qualifier("iEmployeeDao")
    private EmployeeDAO employeeDAO;

    @Autowired
    @Qualifier("iPersonDao")
    private PersonDAO personDAO;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    @Transactional
    public void save() throws Exception {
        CommonPerson person = new CommonPerson();
        person.setFirstName("Steve");
        person.setLastName("Balmer");
        personDAO.save(person);

        CommonEmployee employee = new CommonEmployee("James", "Gosling", "Marketing", new Date());
        employeeDAO.save(employee);
    }

}