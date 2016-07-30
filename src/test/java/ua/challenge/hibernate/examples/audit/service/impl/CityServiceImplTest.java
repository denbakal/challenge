package ua.challenge.hibernate.examples.audit.service.impl;

import org.hibernate.SessionFactory;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.query.AuditQuery;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.challenge.DemoAppApplication;
import ua.challenge.config.HibernateConfig;
import ua.challenge.hibernate.examples.audit.UserRevEntity;
import ua.challenge.hibernate.examples.audit.entity.City;
import ua.challenge.hibernate.examples.audit.service.CityService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by d.bakal on 7/24/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {DemoAppApplication.class, HibernateConfig.class})
public class CityServiceImplTest {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private CityService cityService;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testAllMethods() {
        City city = new City();
        city.setName("Kiev");
        city.setState("Kiev 2");
        city.setCountry("Ukraine");
        cityService.save(city);

        cityService.updateState(1L, "Kiev 3");

        City city2 = new City();
        city2.setName("London");
        city2.setState("Forest");
        city2.setCountry("England");
        cityService.save(city2);

        cityService.delete(city2);
    }

    @Test
    public void testSave() throws Exception {
        City city = new City();
        city.setName("Kiev");
        city.setState("Kiev 2");
        city.setCountry("Ukraine");

        cityService.save(city);

        AuditReader auditReader = AuditReaderFactory.get(sessionFactory.openSession());
        assertThat(auditReader.isEntityClassAudited(City.class)).isTrue();

        List<Number> revisions = auditReader.getRevisions(City.class, city.getId());
        assertThat(revisions).hasSize(1).contains(2);

        AuditQuery auditQuery = auditReader.createQuery().forRevisionsOfEntity(City.class, false, false);
        Object[] result = (Object[]) auditQuery.getSingleResult();
        assertThat(result).hasSize(3);

        UserRevEntity userRevEntity = (UserRevEntity) result[1];
        assertThat(userRevEntity.getUserName()).isEqualTo("TEST_USER");
    }

    @Test
    public void testUpdateState() throws Exception {
        City city = new City();
        city.setName("Kiev");
        city.setState("Kiev 2");
        city.setCountry("Ukraine");

        cityService.save(city);
        cityService.updateState(1L, "Kiev 3");
    }

    @Test
    public void testDelete() throws Exception {
        City city = new City();
        city.setName("Kiev");
        city.setState("Kiev 2");
        city.setCountry("Ukraine");

        cityService.save(city);
        cityService.delete(city);
    }
}