package ua.challenge.spring.lazy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * Created by d.bakal on 28.02.2017.
 */
@Component
public class PersonNotLazy {
    @Autowired
    @Lazy
    private AddressLazy address;

    public PersonNotLazy() {
        System.out.println("Person initialized");
    }

    public AddressLazy getAddress() {
        return address;
    }
}
