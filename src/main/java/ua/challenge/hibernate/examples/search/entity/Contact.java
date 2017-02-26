package ua.challenge.hibernate.examples.search.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.search.annotations.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by d.bakal on 26.02.2017.
 */
@Entity
@Indexed
@Table(name = "contacts")
@Getter
@Setter
@ToString
public class Contact {
    @Id
    private Long id;
    @Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
    private String name;
    private String email;

    public Contact() {
    }
}
