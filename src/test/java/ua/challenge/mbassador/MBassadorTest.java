package ua.challenge.mbassador;

import net.engio.mbassy.bus.MBassador;
import org.junit.Test;

/**
 * Created by d.bakal on 01.04.2017.
 */
public class MBassadorTest {
    @Test
    public void sample() {
        MBassador eventBus = new MBassador();
        eventBus.subscribe(new FinishedListener());

        FinishedEvent event = new FinishedEvent();
        event.setDescription("Custom event");

        eventBus.publish(event);
    }
}
