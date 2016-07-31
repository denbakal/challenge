package ua.challenge.hibernate.examples.relationship.one.to.one.unidirectional.dao;

import ua.challenge.hibernate.examples.relationship.one.to.one.unidirectional.entity.Customer;

/**
 * Created by d.bakal on 7/31/2016.
 */
public interface CustomerDAO {
    void save(Customer customer);
}
