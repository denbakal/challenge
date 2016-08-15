package ua.challenge.hibernate.examples.relationship.many.to.many.unidirectional.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.challenge.hibernate.examples.relationship.many.to.many.unidirectional.dao.EmployeeDAO;
import ua.challenge.hibernate.examples.relationship.many.to.many.unidirectional.entity.Employee;

/**
 * Created by d.bakal on 8/15/2016.
 */
@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(Employee employee) {
        sessionFactory.getCurrentSession().save(employee);
    }
}
