package ua.challenge.hibernate.examples.inheritance.single.table.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Date;

/**
 * Created by Денис on 24.08.2016.
 */
@Entity
@DiscriminatorValue("E")
public class CommonEmployee extends CommonPerson {
    @Column(name="joining_date")
    private Date joiningDate;

    @Column(name="department_name")
    private String departmentName;

    public CommonEmployee() {
    }

    public CommonEmployee(String firstName, String lastName, String departmentName, Date joiningDate) {
        super(firstName, lastName);
        this.departmentName = departmentName;
        this.joiningDate = joiningDate;
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
        return "CommonEmployee{" +
                "joiningDate=" + joiningDate +
                ", departmentName='" + departmentName + '\'' +
                '}';
    }
}
