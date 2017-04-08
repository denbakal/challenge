package ua.challenge.hibernate.examples.search.service.impl;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import ua.challenge.DemoAppApplication;
import ua.challenge.DemoAppApplicationTests;
import ua.challenge.config.HibernateConfigTest;
import ua.challenge.hibernate.examples.search.entity.Contact;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by d.bakal on 26.02.2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {HibernateConfigTest.class})
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class, DbUnitTestExecutionListener.class })
public class FullTextSearchServiceImplTest {
    @PersistenceContext
    private EntityManager entityManager;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    @Transactional
//    @DatabaseSetup("/data/contacts.xml")
//    @Rollback(value = false)
    public void createIndex() throws Exception {
        // Indexing
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
        fullTextEntityManager.createIndexer().startAndWait();

        // Searching
        // create native Lucene query unsing the query DSL
        // alternatively you can write the Lucene query using the Lucene query parser
        // or the Lucene programmatic API. The Hibernate Search DSL is recommended though
        QueryBuilder builder = fullTextEntityManager.getSearchFactory()
                .buildQueryBuilder().forEntity(Contact.class).get();

        // create the query
        Query luceneQuery = builder
                .keyword()
                .onFields("name")
                .matching("Li")
                .createQuery();

        // wrap Lucene query in a javax.persistence.Query
        javax.persistence.Query jpaQuery = fullTextEntityManager.createFullTextQuery(luceneQuery, Contact.class);

        // execute search
        List result = jpaQuery.getResultList();

        assertThat(result.size()).isEqualTo(2);
    }

}