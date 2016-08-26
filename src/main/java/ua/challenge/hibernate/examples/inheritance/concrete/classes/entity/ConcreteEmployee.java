package ua.challenge.hibernate.examples.inheritance.concrete.classes.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by Денис on 26.08.2016.
 */
@Entity
@Table(name = "concrete_employees")
public class ConcreteEmployee extends ConcretePerson {
    @Column(name="joining_date")
    private Date joiningDate;

    @Column(name="department_name")
    private String departmentName;

    public ConcreteEmployee() {
    }

    public ConcreteEmployee(String firstName, String lastName, Date joiningDate, String departmentName) {
        super(firstName, lastName);
        this.joiningDate = joiningDate;
        this.departmentName = departmentName;
    }

    public Date getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(Date joiningDate) {
        this.joiningDate = joiningDate;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    @Override
    public String toString() {
        return "ConcreteEmployee{" +
                "joiningDate=" + joiningDate +
                ", departmentName='" + departmentName + '\'' +
                '}';
    }
}
