package ua.challenge.hibernate.examples.inheritance.joined.subclass.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by Денис on 25.08.2016.
 */
@Entity
@Table(name = "join_employees")
@PrimaryKeyJoinColumn(name = "emp_id", referencedColumnName = "person_id")
public class JoinEmployee extends JoinPerson {
    @Column(name="joining_date")
    private Date joiningDate;

    @Column(name="department_name")
    private String departmentName;

    public JoinEmployee() {
    }

    public JoinEmployee(String firstname, String lastName, Date joiningDate, String departmentName) {
        super(firstname, lastName);
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
        return "JoinEmployee{" +
                "joiningDate=" + joiningDate +
                ", departmentName='" + departmentName + '\'' +
                '}';
    }
}
