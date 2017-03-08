package ua.challenge.hibernate.examples.audit;

import com.querydsl.core.annotations.QueryExclude;
import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionEntity;

import javax.persistence.Entity;

/**
 * Created by d.bakal on 7/24/2016.
 */
@Entity
@RevisionEntity(UserRevisionListener.class)
@QueryExclude
public class UserRevEntity extends DefaultRevisionEntity {
    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
