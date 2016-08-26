package ua.challenge.hibernate.examples.inheritance.concrete.classes.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.challenge.hibernate.examples.inheritance.concrete.classes.dao.ConcreteEmployeeDAO;
import ua.challenge.hibernate.examples.inheritance.concrete.classes.entity.ConcreteEmployee;

/**
 * Created by Денис on 26.08.2016.
 */
@Repository
public class ConcreteEmployeeDAOImpl implements ConcreteEmployeeDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(ConcreteEmployee employee) {
        sessionFactory.getCurrentSession().save(employee);
    }
}
