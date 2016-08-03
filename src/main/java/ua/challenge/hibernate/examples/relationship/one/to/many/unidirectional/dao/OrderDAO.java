package ua.challenge.hibernate.examples.relationship.one.to.many.unidirectional.dao;

import ua.challenge.hibernate.examples.relationship.one.to.many.unidirectional.entity.Order;

/**
 * Created by d.bakal on 8/1/2016.
 */
public interface OrderDAO {
    void save(Order order);
}
