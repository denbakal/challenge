package ua.challenge.hibernate.examples.transactions.service;

import ua.challenge.hibernate.examples.transactions.entity.Person;

/**
 * Created by d.bakal on 7/24/2016.
 */
public interface PersonService {
    void save(Person person);
    void setCustomPersonWithRequired(Person person);
    void setCustomPersonWithMandatory(Person person);
    void setCustomPersonWithSupports(Person person);
    void setCustomPersonWithNotSupported(Person person);
    void setCustomPersonWithRequiresNew(Person person);
    void setCustomPersonWithNested(Person person);
    void setCustomPersonWithNever(Person person);
}
