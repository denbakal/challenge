package ua.challenge.hibernate.examples.inheritance.concrete.classes.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.challenge.hibernate.examples.inheritance.concrete.classes.dao.ConcreteOwnerDAO;
import ua.challenge.hibernate.examples.inheritance.concrete.classes.entity.ConcreteOwner;

/**
 * Created by Денис on 26.08.2016.
 */
@Repository
public class ConcreteOwnerDAOImpl implements ConcreteOwnerDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(ConcreteOwner owner) {
        sessionFactory.getCurrentSession().save(owner);
    }
}
