package ua.challenge.messaging.example.work.queues;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * Created by d.bakal on 05.02.2017.
 */
@Component
public class RabbitWorkQueuesListener {
    private Random random = new Random();

    @RabbitListener(queues = "report_work_queues")
    public void worker1(String message) throws InterruptedException {
        System.out.println("worker 1 : " + message);
        Thread.sleep(100 * random.nextInt(20));
    }

    @RabbitListener(queues = "report_work_queues")
    public void worker2(String message) throws InterruptedException {
        System.out.println("worker 2 : " + message);
        Thread.sleep(100 * random.nextInt(20));
    }
}
