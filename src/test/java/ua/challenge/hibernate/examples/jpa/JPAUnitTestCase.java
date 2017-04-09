package ua.challenge.hibernate.examples.jpa;

import org.hibernate.Interceptor;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.jpa.boot.internal.EntityManagerFactoryBuilderImpl;
import org.hibernate.jpa.boot.internal.PersistenceUnitInfoDescriptor;
import org.junit.Before;

import javax.persistence.EntityManagerFactory;
import javax.persistence.spi.PersistenceUnitInfo;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by d.bakal on 08.04.2017.
 */
public abstract class JPAUnitTestCase {
    @Before
    public void init() {
    }

    protected EntityManagerFactory entityManagerFactory() {
        PersistenceUnitInfo persistenceUnitInfo = persistenceUnitInfo(getClass().getSimpleName());
        Map<String, Object> configuration = new HashMap<>();
        configuration.put(AvailableSettings.INTERCEPTOR, interceptor());

        return new EntityManagerFactoryBuilderImpl(
                    new PersistenceUnitInfoDescriptor(persistenceUnitInfo), configuration
            ).build();
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
        /*DataSource dataSource = newDataSource();
        if (dataSource != null) {
            properties.put("hibernate.connection.datasource", dataSource);
        }*/

        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQL82Dialect");
        properties.put("hibernate.hbm2ddl.auto", "create-drop");
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.format_sql", "true");
        properties.put("hibernate.format_sql", "true");
        properties.put("hibernate.ejb.metamodel.population", "disabled");
        properties.put("hibernate.generate_statistics", Boolean.TRUE.toString());

        // datasource
        properties.put("hibernate.connection.driver_class", "org.postgresql.Driver");
        properties.put("hibernate.connection.url", "jdbc:postgresql://localhost:5432/challenge_test");
        properties.put("hibernate.connection.username", "postgres");
        properties.put("hibernate.connection.password", "root");

        return properties;
    }

    private Interceptor interceptor() {
        return null;
    }
}
