package ua.challenge.hibernate.examples.inheritance.joined.subclass.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.challenge.hibernate.examples.inheritance.joined.subclass.dao.JoinOwnerDAO;
import ua.challenge.hibernate.examples.inheritance.joined.subclass.entity.JoinOwner;

/**
 * Created by Денис on 25.08.2016.
 */
@Repository
public class JoinOwnerDAOImpl implements JoinOwnerDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(JoinOwner owner) {
        sessionFactory.getCurrentSession().save(owner);
    }
}
