package ua.challenge.messaging.example.publish.subscribe;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by d.bakal on 05.02.2017.
 */
@Component
public class RabbitPublishSubscribeListener {
    @RabbitListener(queues = "publish-subscribe-queue-1")
    public void worker1(String message) {
        System.out.println("worker 1 : " + message);
    }

    @RabbitListener(queues = "publish-subscribe-queue-2")
    public void worker2(String message) {
        System.out.println("worker 2 : " + message);
    }
}
