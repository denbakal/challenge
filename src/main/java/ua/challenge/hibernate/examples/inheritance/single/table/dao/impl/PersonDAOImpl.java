package ua.challenge.hibernate.examples.inheritance.single.table.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.challenge.hibernate.examples.inheritance.single.table.dao.PersonDAO;
import ua.challenge.hibernate.examples.inheritance.single.table.entity.CommonPerson;

/**
 * Created by Денис on 24.08.2016.
 */
@Repository("iPersonDao")
public class PersonDAOImpl implements PersonDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(CommonPerson person) {
        sessionFactory.getCurrentSession().save(person);
    }
}
