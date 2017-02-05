package ua.challenge.messaging.example.remote.procedure.call;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by d.bakal on 05.02.2017.
 */
@Component
public class RabbitRpcListener {
    @RabbitListener(queues = "rpc-1")
    public String worker(String message) {
        System.out.println("Received on worker : " + message);
        return "Received on worker : " + message;
    }
}
