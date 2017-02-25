package ua.challenge.query.dsl;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.mysema.query.jpa.impl.JPAQuery;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import ua.challenge.DemoAppApplication;
import ua.challenge.config.HibernateConfig;
import ua.challenge.config.HibernateConfigTest;
import ua.challenge.hibernate.examples.transactions.entity.Person;
import ua.challenge.hibernate.examples.transactions.entity.QPerson;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by d.bakal on 25.02.2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {DemoAppApplication.class, HibernateConfigTest.class})
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class, DbUnitTestExecutionListener.class })
public class QueryDslTest {
    @PersistenceContext
    private EntityManager entityManager;

    @Test
    @Transactional
    @DatabaseSetup("/data/persons.xml")
    public void example() {
        /* HQL */
        /*HQLQuery query = new HibernateQuery(sessionFactory.getCurrentSession());
        long count = query.from(person).where(person.firstName.eq("Test")).count();
        System.out.println("Count of persons: " + count);
        assertThat(count).isEqualTo(3);*/

        QPerson person = QPerson.person;
        JPAQuery query = new JPAQuery(entityManager);
        long count = query.from(person).count();
        System.out.println("Count of persons: " + count);
        assertThat(count).isEqualTo(2L);
    }
}
