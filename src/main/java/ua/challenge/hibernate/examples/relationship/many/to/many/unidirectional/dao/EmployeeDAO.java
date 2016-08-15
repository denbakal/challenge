package ua.challenge.hibernate.examples.relationship.many.to.many.unidirectional.dao;

import ua.challenge.hibernate.examples.relationship.many.to.many.unidirectional.entity.Employee;

/**
 * Created by d.bakal on 8/15/2016.
 */
public interface EmployeeDAO {
    void save(Employee employee);
}
