package ua.challenge.hibernate.examples.relationship.one.to.one.bidirectional.dao;

import ua.challenge.hibernate.examples.relationship.one.to.one.bidirectional.entity.Student;

/**
 * Created by d.bakal on 8/1/2016.
 */
public interface StudentDAO {
    void save(Student student);
}
