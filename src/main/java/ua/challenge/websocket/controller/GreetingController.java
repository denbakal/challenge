package ua.challenge.websocket.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import ua.challenge.websocket.ActionMessage;
import ua.challenge.websocket.Greeting;
import ua.challenge.websocket.HelloMessage;

@Log4j2
@Controller
public class GreetingController {
    @Autowired
    private SimpMessagingTemplate template;

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) throws InterruptedException {
        Thread.sleep(3000L);
        return new Greeting("Hello, " + message.getName() + "!");
    }

    @MessageMapping("/actions/{id}")
    public void actions(@DestinationVariable("id") Long id, ActionMessage message) throws InterruptedException {
        log.info("Handling action with id: " + id);
        Thread.sleep(3000L);
        this.template.convertAndSend("/topic/actions/history/" + id, "Action: " + message.getValue());
    }
}
