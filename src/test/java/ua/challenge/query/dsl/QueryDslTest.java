package ua.challenge.query.dsl;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import ua.challenge.config.HibernateConfigTest;
import ua.challenge.hibernate.examples.transactions.entity.Person;
import ua.challenge.hibernate.examples.transactions.entity.QPerson;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by d.bakal on 25.02.2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {HibernateConfigTest.class})
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class, DbUnitTestExecutionListener.class })
public class QueryDslTest {
    @PersistenceContext
    private EntityManager entityManager;

    /* Querying */
    @Test
    @Transactional
    @DatabaseSetup("/data/persons.xml")
    public void countTest() {
        /* HQL */
        /*HQLQuery query = new HibernateQuery(sessionFactory.getCurrentSession());
        long count = query.from(person).where(person.firstName.eq("Test")).count();
        System.out.println("Count of persons: " + count);
        assertThat(count).isEqualTo(3);*/

        QPerson person = QPerson.person;
        JPAQuery query = new JPAQuery(entityManager);
        long count = query.from(person).fetchCount();
        System.out.println("Count of persons: " + count);
        assertThat(count).isEqualTo(2L);
    }

    @Test
    @Transactional
    @DatabaseSetup("/data/persons.xml")
    public void fetchOneUsingQueryFactory() {
        QPerson person = QPerson.person;
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);

        Person result = queryFactory.selectFrom(person)
                .where(person.firstName.eq("Fred"))
                .fetchOne();

        System.out.println("Result: " + result);
        assertThat(result).isNotNull();
        assertThat(result.getFirstName()).isEqualTo("Fred");
    }

    @Test
    @Transactional
    @DatabaseSetup("/data/persons.xml")
    public void fetchWithWhereTest() {
        QPerson person = QPerson.person;
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);

        List<Person> persons = queryFactory.selectFrom(person)
                .where(person.firstName.eq("Phillip"))
                .fetch();

        persons.forEach(System.out::println);
        assertThat(persons.size()).isEqualTo(1);
    }

    @Test
    @Transactional
    @DatabaseSetup("/data/persons.xml")
    public void fetchWithWhereAndTest() {
        QPerson person = QPerson.person;
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);

        List<Person> persons = queryFactory.selectFrom(person)
                .where(person.firstName.eq("Phillip").and(person.lastName.eq("Webb")))
                .fetch();
        /*List<Person> persons = queryFactory.selectFrom(person)
                .where(person.firstName.eq("Phillip"), person.lastName.eq("Webb"))
                .fetch();*/

        persons.forEach(System.out::println);
        assertThat(persons.size()).isEqualTo(1);
    }

    @Test
    @Transactional
    @DatabaseSetup("/data/persons.xml")
    public void findWithWhereOrTest() {
        QPerson person = QPerson.person;
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);

        List<Person> persons = queryFactory.selectFrom(person)
                .where(person.firstName.eq("Phillip").or(person.lastName.eq("Bloggs")))
                .fetch();

        persons.forEach(System.out::println);
        assertThat(persons.size()).isEqualTo(2);
    }


}
