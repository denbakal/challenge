package ua.challenge.hibernate.examples.performance;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import ua.challenge.hibernate.examples.jpa.JPAUnitTestCase;

import javax.persistence.*;

import static org.hibernate.testing.transaction.TransactionUtil.doInJPA;


/**
 * Created by d.bakal on 08.04.2017.
 */
@Log4j2
public class HibernateTestingTest extends JPAUnitTestCase {
    @Test
    public void test() {
        log.debug("Hibernate testing");
        doInJPA(this::entityManagerFactory, entityManager -> {
            Phone phone = new Phone();
            phone.setNumber("123-456-7890");

            entityManager.persist(phone);
        });
    }

    @Override
    protected Class<?>[] entities() {
        return new Class<?>[] {
                Phone.class
        };
    }

    @Entity
    @Table(name = "phones")
    @Getter
    @Setter
    @NoArgsConstructor
    private static class Phone {
        @Id
        @GeneratedValue
        private Long id;
        private String number;
    }
}
