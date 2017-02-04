package ua.challenge.messaging;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by d.bakal on 04.02.2017.
 */
@Component
public class RabbitMqListener {
    @RabbitListener(queues = "report_queue")
    public void processQueue(String message) {
        System.out.println("Received from report_queue: " + message);
    }
}
