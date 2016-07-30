package ua.challenge.hibernate.examples.transactions.service.impl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.IllegalTransactionStateException;
import ua.challenge.DemoAppApplication;
import ua.challenge.config.HibernateConfig;
import ua.challenge.hibernate.examples.transactions.entity.Person;
import ua.challenge.hibernate.examples.transactions.service.PersonService;

/**
 * Created by d.bakal on 7/24/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {DemoAppApplication.class, HibernateConfig.class})
public class PersonServiceImplTest {

    @Autowired
    private PersonService personService;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testSave() throws Exception {
        Person dummyPerson = new Person();
        dummyPerson.setId(1L);
        dummyPerson.setSolutations("Mr.");
        dummyPerson.setFirstName("Jim");
        dummyPerson.setLastName("Bin");

        personService.save(dummyPerson);
    }

    @Test
    public void testSetCustomPersonWithRequired() throws Exception {
        Person dummyPerson = new Person();
        dummyPerson.setId(1L);
        dummyPerson.setSolutations("Mr.");
        dummyPerson.setFirstName("Jim2");
        dummyPerson.setLastName("Bin");

        personService.setCustomPersonWithRequired(dummyPerson);
    }

    @Test(expected = IllegalTransactionStateException.class)
    public void testSetCustomPersonWithMandatory() throws Exception {
        Person dummyPerson = new Person();
        dummyPerson.setId(1L);
        dummyPerson.setSolutations("Mr.");
        dummyPerson.setFirstName("Jim2");
        dummyPerson.setLastName("Bin");

        personService.setCustomPersonWithMandatory(dummyPerson);
    }

    @Test
    public void testSetCustomPersonWithSupports() throws Exception {
        Person dummyPerson = new Person();
        dummyPerson.setId(1L);
        dummyPerson.setSolutations("Mr.");
        dummyPerson.setFirstName("Jim2");
        dummyPerson.setLastName("Bin");

        personService.setCustomPersonWithSupports(dummyPerson); // The transaction has a local status NOT_ACTIVE
    }

    @Test
    public void testSetCustomPersonWithNotSupported() throws Exception {
        Person dummyPerson = new Person();
        dummyPerson.setId(1L);
        dummyPerson.setSolutations("Mr.");
        dummyPerson.setFirstName("Jim");
        dummyPerson.setLastName("Bin");

        personService.setCustomPersonWithNotSupported(dummyPerson); // The transaction has a local status NOT_ACTIVE
    }

    @Test
    public void testSetCustomPersonWithRequiresNew() throws Exception {
        Person dummyPerson = new Person();
        dummyPerson.setId(1L);
        dummyPerson.setSolutations("Mr.");
        dummyPerson.setFirstName("Jim");
        dummyPerson.setLastName("Bin");

        personService.setCustomPersonWithRequiresNew(dummyPerson);
    }

    @Test
    public void testSetCustomPersonWithNested() throws Exception {
        Person dummyPerson = new Person();
        dummyPerson.setId(1L);
        dummyPerson.setSolutations("Mr.");
        dummyPerson.setFirstName("Jim");
        dummyPerson.setLastName("Bin");

        personService.setCustomPersonWithNested(dummyPerson);
    }

    @Test
    public void testSetCustomPersonWithNever() throws Exception {
        Person dummyPerson = new Person();
        dummyPerson.setId(1L);
        dummyPerson.setSolutations("Mr.");
        dummyPerson.setFirstName("Jim");
        dummyPerson.setLastName("Bin");

        personService.setCustomPersonWithNever(dummyPerson);
    }
}