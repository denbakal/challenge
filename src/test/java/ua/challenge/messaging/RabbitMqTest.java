package ua.challenge.messaging;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.challenge.config.RabbitConfiguration;

/**
 * Created by d.bakal on 21.01.2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(RabbitConfiguration.class)
public class RabbitMqTest {

    @Autowired
    private RabbitTemplate amqpTemplate;

    @Test
    public void simpleExample() {
        amqpTemplate.convertAndSend("report_queue", "Report message.");
    }
}
