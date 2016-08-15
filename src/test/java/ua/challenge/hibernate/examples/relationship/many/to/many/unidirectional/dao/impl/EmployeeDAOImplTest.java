package ua.challenge.hibernate.examples.relationship.many.to.many.unidirectional.dao.impl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import ua.challenge.DemoAppApplication;
import ua.challenge.config.HibernateConfig;
import ua.challenge.hibernate.examples.relationship.many.to.many.unidirectional.dao.EmployeeDAO;
import ua.challenge.hibernate.examples.relationship.many.to.many.unidirectional.entity.Department;
import ua.challenge.hibernate.examples.relationship.many.to.many.unidirectional.entity.Employee;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by d.bakal on 8/15/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {DemoAppApplication.class, HibernateConfig.class})
@TransactionConfiguration(defaultRollback = false)
public class EmployeeDAOImplTest {

    @Autowired
    private EmployeeDAO employeeDAO;

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    @Transactional
    public void testSave() throws Exception {
        Department department1 = new Department();
        department1.setName("IT");

        Department department2 = new Department();
        department2.setName("QA");

        List<Department> departments = new ArrayList<>();
        departments.add(department1);
        departments.add(department2);

        Employee employee = new Employee();
        employee.setFirstName("Jim");
        employee.setLastName("Bin");
        employee.setDepartments(departments);

        employeeDAO.save(employee);
    }
}