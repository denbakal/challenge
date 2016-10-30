package ua.challenge.db.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by d.bakal on 30.10.2016.
 */
@Entity
@Table(name = "users")
@Getter
@Setter
@ToString
public class User {
    @Id
    private Long id;

    private String firstName;

    private String lastName;

    @Temporal(value = TemporalType.DATE)
    private Date birthDay;

    private Address address;

    private List<Department> departments;
}
