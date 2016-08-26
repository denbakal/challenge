package ua.challenge.hibernate.examples.inheritance.concrete.classes.dao;

import ua.challenge.hibernate.examples.inheritance.concrete.classes.entity.ConcretePerson;

/**
 * Created by Денис on 26.08.2016.
 */
public interface ConcretePersonDAO {
    void save(ConcretePerson person);
}
