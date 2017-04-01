package ua.challenge.mbassador;

import net.engio.mbassy.listener.Handler;

/**
 * Created by d.bakal on 01.04.2017.
 */
public class FinishedListener {
    @Handler
    public void handle(FinishedEvent event) {
        System.out.println("event = " + event);
    }
}
