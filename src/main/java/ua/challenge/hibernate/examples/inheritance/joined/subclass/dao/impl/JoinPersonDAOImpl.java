package ua.challenge.hibernate.examples.inheritance.joined.subclass.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.challenge.hibernate.examples.inheritance.joined.subclass.dao.JoinPersonDAO;
import ua.challenge.hibernate.examples.inheritance.joined.subclass.entity.JoinPerson;

/**
 * Created by Денис on 25.08.2016.
 */
@Repository
public class JoinPersonDAOImpl implements JoinPersonDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(JoinPerson person) {
        sessionFactory.getCurrentSession().save(person);
    }
}
