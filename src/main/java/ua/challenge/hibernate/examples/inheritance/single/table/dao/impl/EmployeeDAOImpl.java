package ua.challenge.hibernate.examples.inheritance.single.table.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.challenge.hibernate.examples.inheritance.single.table.dao.EmployeeDAO;
import ua.challenge.hibernate.examples.inheritance.single.table.entity.CommonEmployee;

/**
 * Created by Денис on 24.08.2016.
 */
@Repository("iEmployeeDao")
public class EmployeeDAOImpl implements EmployeeDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(CommonEmployee employee) {
        sessionFactory.getCurrentSession().save(employee);
    }
}
