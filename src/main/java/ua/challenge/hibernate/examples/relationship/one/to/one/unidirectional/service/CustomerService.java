package ua.challenge.hibernate.examples.relationship.one.to.one.unidirectional.service;

import ua.challenge.hibernate.examples.relationship.one.to.one.unidirectional.entity.Customer;

/**
 * Created by d.bakal on 8/1/2016.
 */
public interface CustomerService {
    void save(Customer customer);
}
