package ua.challenge.hibernate.examples.relationship.one.to.one.bidirectional.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.challenge.hibernate.examples.relationship.one.to.one.bidirectional.dao.StudentDAO;
import ua.challenge.hibernate.examples.relationship.one.to.one.bidirectional.entity.Student;

/**
 * Created by d.bakal on 8/1/2016.
 */
@Repository
public class StudentDAOImpl implements StudentDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(Student student) {
        sessionFactory.getCurrentSession().save(student);
    }
}
