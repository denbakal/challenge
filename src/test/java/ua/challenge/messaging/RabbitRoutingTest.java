package ua.challenge.messaging;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.challenge.config.rabbit.RabbitRoutingConfiguration;

/**
 * Created by d.bakal on 05.02.2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(RabbitRoutingConfiguration.class)
public class RabbitRoutingTest {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void routingTest() {
        rabbitTemplate.convertAndSend("error", "Error");
        rabbitTemplate.convertAndSend("info", "Info");
        rabbitTemplate.convertAndSend("warning", "Warning");
    }

    @Test
    public void routingWithErrorTest() {
        rabbitTemplate.convertAndSend("error", "Error");
    }

    @Test
    public void routingWithInfoTest() {
        rabbitTemplate.convertAndSend("info", "Info");
    }

    @Test
    public void routingWithWarningTest() {
        rabbitTemplate.convertAndSend("warning", "Warning");
    }
}
