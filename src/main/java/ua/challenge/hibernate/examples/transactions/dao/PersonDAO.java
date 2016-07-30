package ua.challenge.hibernate.examples.transactions.dao;

import ua.challenge.hibernate.examples.transactions.entity.Person;

import java.util.List;

/**
 * Created by d.bakal on 7/23/2016.
 */
public interface PersonDAO {
    List<Person> getPersons();

    void save(Person person);

    void setCustomPerson(Person person);
}
