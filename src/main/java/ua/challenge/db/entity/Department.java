package ua.challenge.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by d.bakal on 30.10.2016.
 */
@Entity
@Table(name = "departments")
@Getter
@Setter
public class Department {
    @Id
    private Long id;
    private String name;
}
