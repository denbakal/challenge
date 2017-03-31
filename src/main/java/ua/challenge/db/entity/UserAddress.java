package ua.challenge.db.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

/**
 * Created by d.bakal on 30.10.2016.
 */
@Entity
@Table(name = "user_addresses")
@Getter
@Setter
@ToString
public class UserAddress {
    @Id
    private Long id;

    @OneToOne
    private UserCity city;

    private String street;
}
