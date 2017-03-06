package ua.challenge.spring.lazy;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * Created by d.bakal on 28.02.2017.
 */
@Lazy
@Component
public class AddressLazy {
    public AddressLazy() {
        System.out.println("Address initialized");
    }

    @Override
    public String toString() {
        return "AddressLazy{}";
    }
}
