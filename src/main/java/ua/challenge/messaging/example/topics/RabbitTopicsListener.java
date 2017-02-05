package ua.challenge.messaging.example.topics;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by d.bakal on 05.02.2017.
 */
@Component
public class RabbitTopicsListener {
    @RabbitListener(queues = "topics-1")
    public void worker1(String message) {
        System.out.println("accepted on worker 1 : " + message);
    }

    @RabbitListener(queues = "topics-2")
    public void worker2(String message) {
        System.out.println("accepted on worker 2 : " + message);
    }
}
