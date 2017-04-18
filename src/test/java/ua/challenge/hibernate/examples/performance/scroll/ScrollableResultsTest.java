package ua.challenge.hibernate.examples.performance.scroll;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.SneakyThrows;
import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.engine.spi.SessionImplementor;
import org.junit.Test;
import ua.challenge.hibernate.examples.jpa.JPAUnitTestCase;

import javax.persistence.*;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Map;

import static org.hibernate.testing.transaction.TransactionUtil.doInHibernate;

/**
 * Created by d.bakal on 12.04.2017.
 */
public class ScrollableResultsTest extends JPAUnitTestCase {

    @Test
    @SneakyThrows
    public void insertPersons() {
        System.out.println("The maximum amount of memory = " + Runtime.getRuntime().maxMemory());

        Instant start = Instant.now();
        doInJPA(entityManager -> {
            for (int i = 0; i < 100000; i++) {
                Person person = new Person();
                person.setFirstName("First Name " + i);
                person.setLastName("Last Name " + i);
                entityManager.persist(person);
            }
        });

        Instant end = Instant.now();
        System.out.println(Duration.between(start, end).toString().substring(2));
    }

    /*1*/
    @Test
    @SneakyThrows
    public void getPersonsWithHQL() {
        System.out.println("The maximum amount of memory = " + Runtime.getRuntime().maxMemory());

        Instant start = Instant.now();
        doInJPA(entityManager -> {

            List<Person> persons = entityManager
                    .createQuery("from ua.challenge.hibernate.examples.performance.scroll.ScrollableResultsTest$Person")
                    .getResultList();

            persons.forEach(System.out::println);

            /* Get a list of managed Entity instances in a persistence context */
            SessionImplementor implementor = entityManager.unwrap(SessionImplementor.class);
            org.hibernate.engine.spi.PersistenceContext context = implementor.getPersistenceContext();
            Map.Entry<Object,org.hibernate.engine.spi.EntityEntry>[] entityEntries = context.reentrantSafeEntityEntries();
            System.out.println("Count of entity: " + entityEntries.length);
            System.out.println("Persons: " + persons.size());
        });

        Instant end = Instant.now();
        System.out.println(Duration.between(start, end).toString().substring(2));
    }

    /*2*/
    @Test
    @SneakyThrows
    public void getPersonsWithSQL() {
        System.out.println("The maximum amount of memory = " + Runtime.getRuntime().maxMemory());

        Instant start = Instant.now();
        doInJPA(entityManager -> {
            List<Person> persons = entityManager
                    .createNativeQuery("select * from persons", Person.class)
                    .getResultList();

            persons.forEach(System.out::println);

            /* Get a list of managed Entity instances in a persistence context */
            SessionImplementor implementor = entityManager.unwrap(SessionImplementor.class);
            org.hibernate.engine.spi.PersistenceContext context = implementor.getPersistenceContext();
            Map.Entry<Object,org.hibernate.engine.spi.EntityEntry>[] entityEntries = context.reentrantSafeEntityEntries();
            System.out.println("Count of entity: " + entityEntries.length);
            System.out.println("Persons: " + persons.size());
        });

        Instant end = Instant.now();
        System.out.println(Duration.between(start, end).toString().substring(2));
    }

    /*3*/
    @Test
    @SneakyThrows
    public void getPersonsWithSQLViaSessionFactory() {
        System.out.println("The maximum amount of memory = " + Runtime.getRuntime().maxMemory());

        Instant start = Instant.now();
        doInHibernate(this::sessionFactory, session -> {
            List<Person> persons = session.createNativeQuery("select * from persons", Person.class)
                    .getResultList();

            /* Get a list of managed Entity instances in a persistence context */
            SessionImplementor implementor = (SessionImplementor) session;
            org.hibernate.engine.spi.PersistenceContext context = implementor.getPersistenceContext();
            Map.Entry<Object,org.hibernate.engine.spi.EntityEntry>[] entityEntries = context.reentrantSafeEntityEntries();
            System.out.println("Count of entity: " + entityEntries.length);
            System.out.println("Persons: " + persons.size());
        });

        Instant end = Instant.now();
        System.out.println(Duration.between(start, end).toString().substring(2));
    }

    /*4*/
    @Test
    @SneakyThrows
    public void getPersonsWithSQLViaSessionFactoryWithScrollWithoutNext() {
        System.out.println("The maximum amount of memory = " + Runtime.getRuntime().maxMemory());

        Instant start = Instant.now();
        doInHibernate(this::sessionFactory, session -> {
            ScrollableResults results = session.createNativeQuery("select * from persons", Person.class)
                    .scroll(ScrollMode.FORWARD_ONLY);

            /* Get a list of managed Entity instances in a persistence context */
            SessionImplementor implementor = (SessionImplementor) session;
            org.hibernate.engine.spi.PersistenceContext context = implementor.getPersistenceContext();
            Map.Entry<Object,org.hibernate.engine.spi.EntityEntry>[] entityEntries = context.reentrantSafeEntityEntries();
            System.out.println("Count of entity: " + entityEntries.length);
        });

        Instant end = Instant.now();
        System.out.println(Duration.between(start, end).toString().substring(2));
    }

    /*5*/
    @Test
    @SneakyThrows
    public void getPersonsWithSQLViaSessionFactoryWithScrollWithNext() {
        System.out.println("The maximum amount of memory = " + Runtime.getRuntime().maxMemory());

        Instant start = Instant.now();
        doInHibernate(this::sessionFactory, session -> {
            ScrollableResults results = session.createNativeQuery("select * from persons", Person.class)
                    .scroll(ScrollMode.FORWARD_ONLY);

            while (results.next()) {
                Person person = (Person) results.get(0);
                System.out.println("person = " + person);
            }

            /* Get a list of managed Entity instances in a persistence context */
            SessionImplementor implementor = (SessionImplementor) session;
            org.hibernate.engine.spi.PersistenceContext context = implementor.getPersistenceContext();
            Map.Entry<Object,org.hibernate.engine.spi.EntityEntry>[] entityEntries = context.reentrantSafeEntityEntries();
            System.out.println("Count of entity: " + entityEntries.length);
        });

        Instant end = Instant.now();
        System.out.println(Duration.between(start, end).toString().substring(2));
    }

    /*6*/
    @Test
    @SneakyThrows
    public void getPersonsWithSQLViaSessionFactoryWithScrollWithNextOnly10() {
        System.out.println("The maximum amount of memory = " + Runtime.getRuntime().maxMemory());

        Instant start = Instant.now();
        doInHibernate(this::sessionFactory, session -> {
            ScrollableResults results = session.createNativeQuery("select * from persons", Person.class)
                    .scroll(ScrollMode.FORWARD_ONLY);

            int count = 0;
            while (results.next()) {
                if  (count == 10) {
                    break;
                }
                Person person = (Person) results.get(0);
                System.out.println("row = " + person);
                count++;
            }

            /* Get a list of managed Entity instances in a persistence context */
            SessionImplementor implementor = (SessionImplementor) session;
            org.hibernate.engine.spi.PersistenceContext context = implementor.getPersistenceContext();
            Map.Entry<Object,org.hibernate.engine.spi.EntityEntry>[] entityEntries = context.reentrantSafeEntityEntries();
            System.out.println("Count of entity: " + entityEntries.length);
        });

        Instant end = Instant.now();
        System.out.println(Duration.between(start, end).toString().substring(2));
    }

    /*7*/
    @Test
    @SneakyThrows
    public void getPersonsWithSQLViaSessionFactoryWithScrollWithNextWithSetReadOnly() {
        System.out.println("The maximum amount of memory = " + Runtime.getRuntime().maxMemory());

        Instant start = Instant.now();
        doInHibernate(this::sessionFactory, session -> {
            ScrollableResults results = session.createNativeQuery("select * from persons", Person.class)
                    .setReadOnly(true)
                    .scroll(ScrollMode.FORWARD_ONLY);

            int count = 0;
            while (results.next()) {
                if  (count == 10) {
                    break;
                }
                Person person = (Person) results.get(0);
                System.out.println("row = " + person);
                count++;
            }

            /* Get a list of managed Entity instances in a persistence context */
            SessionImplementor implementor = (SessionImplementor) session;
            org.hibernate.engine.spi.PersistenceContext context = implementor.getPersistenceContext();
            Map.Entry<Object,org.hibernate.engine.spi.EntityEntry>[] entityEntries = context.reentrantSafeEntityEntries();
            System.out.println("Count of entity: " + entityEntries.length);
        });

        Instant end = Instant.now();
        System.out.println(Duration.between(start, end).toString().substring(2));
    }

    /*8*/
    @Test
    @SneakyThrows
    public void getPersonsWithSQLViaSessionFactoryWithScrollWithNextWithSetReadOnlyClear() {
        System.out.println("The maximum amount of memory = " + Runtime.getRuntime().maxMemory());

        Instant start = Instant.now();
        doInHibernate(this::sessionFactory, session -> {
            ScrollableResults results = session.createNativeQuery("select * from persons", Person.class)
                    .setReadOnly(true)
                    .scroll(ScrollMode.FORWARD_ONLY);

            int count = 0;
            while (results.next()) {
                if ( count % 20 == 0 ) {
//                    session.flush();
                    session.clear();
                }
                Person person = (Person) results.get(0);
                System.out.println("row = " + person);
                count++;
            }

            /*Get a list of managed Entity instances in a persistence context*/
            SessionImplementor implementor = (SessionImplementor) session;
            org.hibernate.engine.spi.PersistenceContext context = implementor.getPersistenceContext();
            Map.Entry<Object,org.hibernate.engine.spi.EntityEntry>[] entityEntries = context.reentrantSafeEntityEntries();
            System.out.println("Count of entity: " + entityEntries.length);
        });

        Instant end = Instant.now();
        System.out.println(Duration.between(start, end).toString().substring(2));
    }

    /*9*/
    @Test
    @SneakyThrows
    public void getPersonsWithSQLViaSessionFactoryWithScrollWithNextWithStatelessSession() {
        System.out.println("The maximum amount of memory = " + Runtime.getRuntime().maxMemory());

        Instant start = Instant.now();
        doInHibernateStateless(statelessSession -> {

            ScrollableResults results = statelessSession.createNativeQuery("select * from persons", Person.class)
                    .scroll(ScrollMode.FORWARD_ONLY);

            while (results.next()) {
                Person person = (Person) results.get(0);
                System.out.println("row = " + person);
            }
        });

        Instant end = Instant.now();
        System.out.println(Duration.between(start, end).toString().substring(2));
    }

    @Override
    protected Class<?>[] entities() {
        return new Class<?>[] {
            Person.class
        };
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
