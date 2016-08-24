package ua.challenge.hibernate.examples.inheritance.single.table.dao;

import ua.challenge.hibernate.examples.inheritance.single.table.entity.CommonEmployee;

/**
 * Created by Денис on 24.08.2016.
 */
public interface EmployeeDAO {
    void save(CommonEmployee employee);
}
