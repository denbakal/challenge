package ua.challenge.db.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Created by d.bakal on 30.10.2016.
 */
@Entity
@Table(name = "addresses")
@Getter
@Setter
@ToString
public class Address {
    @Id
    private Long id;
    private City city;
    private String street;
}
