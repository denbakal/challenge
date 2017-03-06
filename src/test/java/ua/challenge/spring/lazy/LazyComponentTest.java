package ua.challenge.spring.lazy;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.challenge.config.AppConfigTest;

/**
 * Created by d.bakal on 28.02.2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {AppConfigTest.class})
public class LazyComponentTest {
    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void initTest() throws InterruptedException {
        System.out.println("Application context loaded");
        System.out.println("Getting PersonNotLazy Bean");
        PersonNotLazy personNotLazy = (PersonNotLazy) applicationContext.getBean("personNotLazy");
        System.out.println("personNotLazy = " + personNotLazy);

        System.out.println("Waiting...");
        Thread.sleep(5000);

        System.out.println("Getting the address");
        AddressLazy address = personNotLazy.getAddress();
        System.out.println(address + ".toString()");
    }
}
