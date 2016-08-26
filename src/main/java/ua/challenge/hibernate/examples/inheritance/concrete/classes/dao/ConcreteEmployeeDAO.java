package ua.challenge.hibernate.examples.inheritance.concrete.classes.dao;

import ua.challenge.hibernate.examples.inheritance.concrete.classes.entity.ConcreteEmployee;

/**
 * Created by Денис on 26.08.2016.
 */
public interface ConcreteEmployeeDAO {
    void save(ConcreteEmployee employee);
}
