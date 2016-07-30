package ua.challenge.hibernate.examples.transactions.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.challenge.db.dao.ActivityDAO;
import ua.challenge.db.entity.Activity;
import ua.challenge.hibernate.examples.transactions.dao.PersonDAO;
import ua.challenge.hibernate.examples.transactions.entity.Person;
import ua.challenge.hibernate.examples.transactions.service.PersonService;

/**
 * Created by d.bakal on 7/24/2016.
 */
@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonDAO personDAO;

    @Autowired
    private ActivityDAO activityDAO;

    @Override
    @Transactional
    public void save(Person person) {
        personDAO.save(person);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED) // by default
    public void setCustomPersonWithRequired(Person person) {
        personDAO.setCustomPerson(person);

        Activity activity = new Activity();
        activity.setId(2L);
        activity.setActionName("REQUIRED");
        activityDAO.save(activity);
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void setCustomPersonWithMandatory(Person person) {
        personDAO.setCustomPerson(person);

        Activity activity = new Activity();
        activity.setId(2L);
        activity.setActionName("MANDATORY");
        activityDAO.save(activity);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public void setCustomPersonWithSupports(Person person) {
        personDAO.setCustomPerson(person);

        Activity activity = new Activity();
        activity.setId(2L);
        activity.setActionName("SUPPORTS");
        activityDAO.save(activity);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void setCustomPersonWithNotSupported(Person person) {
        personDAO.setCustomPerson(person);

        Activity activity = new Activity();
        activity.setId(2L);
        activity.setActionName("NOT_SUPPORTED");
        activityDAO.save(activity);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void setCustomPersonWithRequiresNew(Person person) {
        personDAO.setCustomPerson(person);

        Activity activity = new Activity();
        activity.setId(2L);
        activity.setActionName("REQUIRES_NEW");
        activityDAO.save(activity);
    }

    @Override
    @Transactional(propagation = Propagation.NESTED)
    public void setCustomPersonWithNested(Person person) {
        personDAO.setCustomPerson(person);

        Activity activity = new Activity();
        activity.setId(2L);
        activity.setActionName("NESTED");
        activityDAO.save(activity);
    }

    @Override
    @Transactional(propagation = Propagation.NEVER)
    public void setCustomPersonWithNever(Person person) {
        personDAO.setCustomPerson(person);

        Activity activity = new Activity();
        activity.setId(2L);
        activity.setActionName("NEVER");
        activityDAO.save(activity);
    }
}
