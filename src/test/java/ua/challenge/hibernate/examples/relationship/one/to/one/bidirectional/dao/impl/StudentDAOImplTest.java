package ua.challenge.hibernate.examples.relationship.one.to.one.bidirectional.dao.impl;

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
import ua.challenge.hibernate.examples.relationship.one.to.one.bidirectional.dao.StudentDAO;
import ua.challenge.hibernate.examples.relationship.one.to.one.bidirectional.entity.Passport;
import ua.challenge.hibernate.examples.relationship.one.to.one.bidirectional.entity.Student;

import static org.junit.Assert.*;

/**
 * Created by d.bakal on 8/1/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {DemoAppApplication.class, HibernateConfig.class})
@TransactionConfiguration(defaultRollback = false)
public class StudentDAOImplTest {

    @Autowired
    private StudentDAO studentDAO;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    @Transactional
    public void testSave() throws Exception {
        Passport passport = new Passport();

        Student student = new Student();
        student.setFirstName("Test");
        student.setLastName("Test");
        student.setPhoneNumber("067 321 45 77");
        student.setPassport(passport);

        studentDAO.save(student);
    }
}