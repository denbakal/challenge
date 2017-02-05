package ua.challenge.messaging;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.challenge.config.rabbit.RabbitRpcConfiguration;

/**
 * Created by d.bakal on 05.02.2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(RabbitRpcConfiguration.class)
public class RabbitRpcTest {
    @Autowired
    private RabbitTemplate template;

    @Test
    public void rpcTest() {
        String result = (String) template.convertSendAndReceive("rpc-1", "Hello world!");
        System.out.println("RPC result : " + result);
    }
}
