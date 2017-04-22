package ua.challenge.hibernate.examples.jpa;

import org.hibernate.*;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.jpa.boot.internal.EntityManagerFactoryBuilderImpl;
import org.hibernate.jpa.boot.internal.PersistenceUnitInfoDescriptor;
import org.hibernate.testing.transaction.TransactionUtil;
import org.junit.Before;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ua.challenge.core.datasource.latency.LatencyDatasource;
import ua.challenge.core.datasource.latency.ThreadSleepLatencySimulator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.spi.PersistenceUnitInfo;
import javax.sql.DataSource;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * Created by d.bakal on 08.04.2017.
 */
public abstract class JPAUnitTestCase {
    @Before
    public void init() {
        buildEntityManagerFactory();
    }

    private EntityManagerFactory entityManagerFactory;

    protected void buildEntityManagerFactory() {
        PersistenceUnitInfo persistenceUnitInfo = persistenceUnitInfo(getClass().getSimpleName());
        Map<String, Object> configuration = new HashMap<>();
        configuration.put(AvailableSettings.INTERCEPTOR, interceptor());

        entityManagerFactory = new EntityManagerFactoryBuilderImpl(
                    new PersistenceUnitInfoDescriptor(persistenceUnitInfo), configuration).build();
    }

    public SessionFactory sessionFactory() {
        return entityManagerFactory.unwrap(SessionFactory.class);
    }

    private PersistenceUnitInfo persistenceUnitInfo(String name) {
        return new PersistenceUnitInfoImpl(name, entityClassNames(), properties());
    }

    private List<String> entityClassNames() {
        return Arrays.stream(entities())
                .map(Class::getName)
                .collect(Collectors.toList());
    }

    protected abstract Class<?>[] entities();

    private Properties properties() {
        Properties properties = new Properties();
        //data source settings
        DataSource dataSource = newDataSource();
        if (dataSource != null) {
            properties.put("hibernate.connection.datasource", dataSource);
        }

        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQL82Dialect");
//        properties.put("hibernate.hbm2ddl.auto", "validate");
        properties.put("hibernate.hbm2ddl.auto", "create-drop");
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.format_sql", "true");
        properties.put("hibernate.ejb.metamodel.population", "disabled");
        properties.put("hibernate.generate_statistics", Boolean.TRUE.toString());

        properties.put("hibernate.jdbc.fetch_size", 20);

        // datasource
//        properties.put("hibernate.connection.driver_class", "org.postgresql.Driver");
//        properties.put("hibernate.connection.url", "jdbc:postgresql://localhost:5432/challenge_test");
//        properties.put("hibernate.connection.username", "postgres");
//        properties.put("hibernate.connection.password", "root");

        return properties;
    }

    private DataSource newDataSource() {
        DriverManagerDataSource datasource = new DriverManagerDataSource();
        datasource.setDriverClassName("org.postgresql.Driver");
        datasource.setUrl("jdbc:postgresql://localhost:5432/challenge_test");
        datasource.setUsername("postgres");
        datasource.setPassword("root");

        ThreadSleepLatencySimulator simulator = new ThreadSleepLatencySimulator(10L);

        return new LatencyDatasource(datasource, simulator);
    }

    private Interceptor interceptor() {
        return null;
    }

    protected void doInJPA(TransactionUtil.JPATransactionVoidFunction function) {
        EntityManager entityManager = null;
        EntityTransaction txn = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            function.beforeTransactionCompletion();
            txn = entityManager.getTransaction();
            txn.begin();
            function.accept(entityManager);
            txn.commit();
        } catch (Throwable e) {
            if ( txn != null && txn.isActive()) txn.rollback();
            throw e;
        } finally {
            function.afterTransactionCompletion();
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    protected void doInHibernateStateless(HibernateTransactionConsumer callable) {
        StatelessSession session = null;
        Transaction txn = null;
        try {
            session = sessionFactory().openStatelessSession();
            callable.beforeTransactionCompletion();
            txn = session.beginTransaction();

            callable.accept(session);
            txn.commit();
        } catch (Throwable e) {
            if ( txn != null ) txn.rollback();
            throw e;
        } finally {
            callable.afterTransactionCompletion();
            if (session != null) {
                session.close();
            }
        }
    }

    @FunctionalInterface
    protected interface HibernateTransactionConsumer extends Consumer<StatelessSession> {
        default void beforeTransactionCompletion() {

        }

        default void afterTransactionCompletion() {

        }
    }
}
