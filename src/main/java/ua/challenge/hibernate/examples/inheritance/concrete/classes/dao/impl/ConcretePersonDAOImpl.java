package ua.challenge.hibernate.examples.inheritance.concrete.classes.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.challenge.hibernate.examples.inheritance.concrete.classes.dao.ConcretePersonDAO;
import ua.challenge.hibernate.examples.inheritance.concrete.classes.entity.ConcretePerson;

/**
 * Created by Денис on 26.08.2016.
 */
@Repository
public class ConcretePersonDAOImpl implements ConcretePersonDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(ConcretePerson person) {
        sessionFactory.getCurrentSession().save(person);
    }
}
