package ua.challenge.hibernate.examples.inheritance.joined.subclass.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.challenge.hibernate.examples.inheritance.joined.subclass.dao.JoinEmployeeDAO;
import ua.challenge.hibernate.examples.inheritance.joined.subclass.entity.JoinEmployee;

/**
 * Created by Денис on 25.08.2016.
 */
@Repository
public class JoinEmployeeDAOImpl implements JoinEmployeeDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(JoinEmployee employee) {
        sessionFactory.getCurrentSession().save(employee);
    }
}
