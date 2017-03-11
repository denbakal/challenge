package ua.challenge.query.dsl;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.JPAExpressions;
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
import ua.challenge.hibernate.examples.querydsl.entity.QEducation;
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
        assertThat(count).isEqualTo(3L);
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

    /* Using joins */
    @Test
    @Transactional
    @DatabaseSetup("/data/persons.xml")
    public void fetchInnerJoinTest() {
        QPerson person = QPerson.person;
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);

        List<Person> persons = queryFactory.selectFrom(person)
                .innerJoin(person.education)
                .fetch();

        persons.forEach(System.out::println);
        assertThat(persons.size()).isEqualTo(2);
    }

    @Test
    @Transactional
    @DatabaseSetup("/data/persons.xml")
    public void fetchLeftJoinTest() {
        QPerson person = QPerson.person;
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);

        List<Person> persons = queryFactory.selectFrom(person)
                .leftJoin(person.education)
                .fetch();

        persons.forEach(System.out::println);
        assertThat(persons.size()).isEqualTo(3);
    }

    @Test
    @Transactional
    @DatabaseSetup("/data/persons.xml")
    public void fetchLeftJoinWithAliasTest() {
        QPerson person = QPerson.person;
        QEducation education = QEducation.education;
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);

        List<Person> persons = queryFactory.selectFrom(person)
                .leftJoin(person.education, education)
                .on(education.name.eq("middle"))
                .fetch();

        persons.forEach(System.out::println);
        assertThat(persons.size()).isEqualTo(3);
    }

    @Test
    @Transactional
    @DatabaseSetup("/data/persons.xml")
    public void fetchJoinTest() {
        QPerson person = QPerson.person;
        QEducation education = QEducation.education;
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);

        List<Person> persons = queryFactory.selectFrom(person)
                .leftJoin(person.education, education)
                .fetchJoin()
                .fetch();

        persons.forEach(System.out::println);
        assertThat(persons.size()).isEqualTo(3);
    }

    /* Ordering */
    @Test
    @Transactional
    @DatabaseSetup("/data/persons.xml")
    public void fetchOrderBy() {
        QPerson person = QPerson.person;
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);

        List<Person> persons = queryFactory.selectFrom(person)
                .orderBy(person.firstName.asc(), person.lastName.desc())
                .fetch();

        persons.forEach(System.out::println);
        assertThat(persons.size()).isEqualTo(3);
    }

    /* Grouping */
    @Test
    @Transactional
    @DatabaseSetup("/data/persons.xml")
    public void fetchGroupByTest() {
        QPerson person = QPerson.person;
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);

        List<String> result = queryFactory.select(person.solutations)
                .from(person)
                .groupBy(person.solutations)
                .fetch();

        result.forEach(System.out::println);
        assertThat(result.size()).isEqualTo(1);
    }

    @Test
    @Transactional
    @DatabaseSetup("/data/persons.xml")
    public void fetchGroupByWithHavingTest() {
        QPerson person = QPerson.person;
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);

        List<Tuple> result = queryFactory.select(person.solutations, person.firstName)
                .from(person)
                .groupBy(person.solutations, person.firstName)
                .having(person.firstName.eq("Mike"))
                .fetch();

        result.forEach(System.out::println);
        assertThat(result.size()).isEqualTo(1);
    }

    /* Delete clauses */
    @Test
    @Transactional
    @DatabaseSetup("/data/persons.xml")
    public void deleteAllTest() {
        QPerson person = QPerson.person;
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);

        long result = queryFactory.delete(person).execute();
        assertThat(result).isEqualTo(3);
    }

    @Test
    @Transactional
    @DatabaseSetup("/data/persons.xml")
    public void deleteWithWhereTest() {
        QPerson person = QPerson.person;
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);

        long result = queryFactory.delete(person)
                .where(person.firstName.eq("Mike"))
                .execute();

        assertThat(result).isEqualTo(1);
    }

    /* Update clauses */
    @Test
    @Transactional
    @DatabaseSetup("/data/persons.xml")
    public void updateTest() {
        QPerson person = QPerson.person;
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);

        long result = queryFactory.update(person)
                .where(person.firstName.eq("Mike"))
                .set(person.firstName, "Bob")
                .execute();

        assertThat(result).isEqualTo(1);

        Person updatedPerson = queryFactory.selectFrom(person)
                .where(person.firstName.eq("Bob"))
                .fetchOne();

        System.out.println(updatedPerson);
        assertThat(updatedPerson).isNotNull();
        assertThat(updatedPerson.getFirstName()).isEqualTo("Bob");
    }

    /* Subqueries */
    @Test
    @Transactional
    @DatabaseSetup("/data/persons.xml")
    public void fetchWithSubqueriesTest() {
        QPerson person = QPerson.person;
        QEducation education = QEducation.education;
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);

        Person result = queryFactory.selectFrom(person)
                .where(person.education.id.eq(
                        JPAExpressions.select(education.id).from(education)
                            .where(education.name.eq("middle"))
                ))
                .fetchOne();

        System.out.println(result);
        assertThat(result).isNotNull();
        assertThat(result.getFirstName()).isEqualTo("Fred");
    }
}
