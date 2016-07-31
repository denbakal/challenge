package ua.challenge.hibernate.examples.relationship.one.to.one.unidirectional.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.challenge.hibernate.examples.relationship.one.to.one.unidirectional.dao.CustomerDAO;
import ua.challenge.hibernate.examples.relationship.one.to.one.unidirectional.entity.Customer;

/**
 * Created by d.bakal on 7/31/2016.
 */
@Repository
public class CustomerDAOImpl implements CustomerDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(Customer customer) {
        sessionFactory.getCurrentSession().save(customer);
    }
}
