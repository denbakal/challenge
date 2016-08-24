package ua.challenge.hibernate.examples.inheritance.single.table.dao;

import ua.challenge.hibernate.examples.inheritance.single.table.entity.CommonPerson;

/**
 * Created by Денис on 24.08.2016.
 */
public interface PersonDAO {
    void save(CommonPerson person);
}
