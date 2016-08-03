package ua.challenge.hibernate.examples.relationship.one.to.many.unidirectional.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.challenge.hibernate.examples.relationship.one.to.many.unidirectional.dao.OrderDAO;
import ua.challenge.hibernate.examples.relationship.one.to.many.unidirectional.entity.Order;

/**
 * Created by d.bakal on 8/1/2016.
 */
@Repository
public class OrderDAOImpl implements OrderDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(Order order) {
        sessionFactory.getCurrentSession().save(order);
    }
}
