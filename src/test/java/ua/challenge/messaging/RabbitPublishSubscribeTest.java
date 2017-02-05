package ua.challenge.messaging;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.challenge.config.rabbit.RabbitPublishSubscribeConfiguration;
import ua.challenge.config.rabbit.RabbitWorkQueuesConfiguration;

/**
 * Created by d.bakal on 21.01.2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(RabbitPublishSubscribeConfiguration.class)
public class RabbitPublishSubscribeTest {

    @Autowired
    private RabbitTemplate amqpTemplate;

    @Test
    public void publishSubscribeTest() {
        amqpTemplate.setExchange("fanout-exchange");
        amqpTemplate.convertAndSend("Fanout message");
    }
}
