package ua.challenge.messaging;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.challenge.config.rabbit.RabbitTopicsConfiguration;

/**
 * Created by d.bakal on 05.02.2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(RabbitTopicsConfiguration.class)
public class RabbitTopicsTest {
    @Autowired
    private RabbitTemplate template;

    @Test
    public void topicsTest() {
        template.convertAndSend("quick.orange.rabbit", "Message 1");
        template.convertAndSend("lazy.orange.elephant", "Message 2");
        template.convertAndSend("quick.orange.fox", "Message 3");
        template.convertAndSend("lazy.brown.fox", "Message 4");
        template.convertAndSend("lazy.pink.rabbit", "Message 5");
        template.convertAndSend("quick.brown.fox", "Message 6");
    }
}
