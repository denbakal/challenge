package ua.challenge.hibernate.examples.relationship.one.to.one.unidirectional.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.challenge.hibernate.examples.relationship.one.to.one.unidirectional.dao.CustomerDAO;
import ua.challenge.hibernate.examples.relationship.one.to.one.unidirectional.entity.Customer;
import ua.challenge.hibernate.examples.relationship.one.to.one.unidirectional.service.CustomerService;

/**
 * Created by d.bakal on 8/1/2016.
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDAO customerDAO;

    @Override
    @Transactional
    public void save(Customer customer) {
        customerDAO.save(customer);
    }
}
