package ua.challenge.hibernate.examples.querydsl.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by d.bakal on 11.03.2017.
 */
@Entity
@Table(name = "educations")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Education {
    @Id
    private Long id;
    private String name;
}
