package ua.challenge.hibernate.examples.audit;

import org.hibernate.envers.RevisionListener;

/**
 * Created by d.bakal on 7/24/2016.
 */
public class UserRevisionListener implements RevisionListener {
    private final static String USERNAME = "TEST_USER";

    @Override
    public void newRevision(Object revisionEntity) {
        UserRevEntity exampleRevEntity = (UserRevEntity) revisionEntity;
        exampleRevEntity.setUserName(USERNAME);
    }
}
