package ua.challenge.hibernate.examples.performance;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import ua.challenge.hibernate.examples.jpa.JPAUnitTestCase;

import javax.persistence.*;

import java.util.Arrays;
import java.util.List;

import static org.hibernate.testing.transaction.TransactionUtil.doInJPA;


/**
 * Created by d.bakal on 08.04.2017.
 */
@Log4j2
public class HibernateTestingTest extends JPAUnitTestCase {
    @Test(expected = ClassCastException.class)
    public void failedTest() {
        log.debug("Hibernate testing");
        doInJPA(this::entityManagerFactory, entityManager -> {
            Phone phone1 = new Phone( "123-456-7890" );
            Phone phone2 = new Phone( "321-654-0987" );

            Person person = new Person();
            person.setFirstName("User");
            person.setLastName("LastUser");
            person.setPhones(Arrays.asList(phone1, phone2));

            entityManager.persist(person);

            List<Person> persons = entityManager
                    .createNativeQuery("select id from persons").getResultList();

            persons.forEach(System.out::println);
        });
    }

    @Test
    public void test() {
        log.debug("Hibernate testing");
        doInJPA(this::entityManagerFactory, entityManager -> {
            Phone phone1 = new Phone( "123-456-7890" );
            Phone phone2 = new Phone( "321-654-0987" );

            Person person = new Person();
            person.setFirstName("User");
            person.setLastName("LastUser");
            person.setPhones(Arrays.asList(phone1, phone2));

            entityManager.persist(person);

            List<Person> persons = entityManager
                    .createNativeQuery("select id from persons", Person.class).getResultList();

            persons.forEach(System.out::println);
        });
    }

    @Override
    protected Class<?>[] entities() {
        return new Class<?>[] {
                Phone.class,
                Person.class
        };
    }

    @Entity
    @Table(name = "phones")
    @Getter
    @Setter
    @ToString
    private static class Phone {
        @Id
        @GeneratedValue
        private Long id;
        private String number;

        public Phone() {
        }

        public Phone(String number) {
            this.number = number;
        }
    }

    @Entity
    @Table(name = "persons")
    @Getter
    @Setter
    @NoArgsConstructor
    private static class Person {
        @Id
        @GeneratedValue
        private Long id;

        @OneToMany(cascade = CascadeType.ALL)
        private List<Phone> phones;

        private String firstName;
        private String LastName;

        @Override
        public String toString() {
            return "Person{" +
                    "id=" + id +
                    ", firstName='" + firstName + '\'' +
                    ", LastName='" + LastName + '\'' +
                    '}';
        }
    }
}
