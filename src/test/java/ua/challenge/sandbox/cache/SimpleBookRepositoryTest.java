package ua.challenge.sandbox.cache;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import ua.challenge.DemoAppApplication;
import ua.challenge.config.AppCashingConfig;

import static org.junit.Assert.*;

/**
 * Created by d.bakal on 16.11.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {DemoAppApplication.class})
@WebAppConfiguration
public class SimpleBookRepositoryTest {
    @Autowired
    private BookRepository bookRepository;

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void getByIsbn() throws Exception {
        Book book = bookRepository.getByIsbn("1000");
        book = bookRepository.getByIsbn("1000");
        book = bookRepository.getByIsbn("1000");
    }

}