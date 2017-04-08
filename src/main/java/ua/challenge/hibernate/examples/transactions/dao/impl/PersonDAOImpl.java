package ua.challenge.hibernate.examples.transactions.dao.impl;

import lombok.extern.log4j.Log4j2;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.challenge.hibernate.examples.transactions.dao.PersonDAO;
import ua.challenge.hibernate.examples.transactions.entity.Person;

import java.util.List;

/**
 * Created by d.bakal on 7/23/2016.
 */
@Repository
@Log4j2
public class PersonDAOImpl implements PersonDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Person> getPersons() {
        return null;
    }

    @Override
    public void save(Person person) {
        Transaction transaction = sessionFactory.getCurrentSession().getTransaction();
        log.info("Transaction for method save(): " + transaction.getStatus());
        sessionFactory.getCurrentSession().save(person);
    }

    @Override
    public void setCustomPerson(Person person) {
        person.setFirstName(">> --- <<");
        Transaction transaction = sessionFactory.getCurrentSession().getTransaction();
        log.info("Transaction: " + transaction.getStatus());
        sessionFactory.getCurrentSession().save(person);
    }
}
