package ua.challenge.hibernate.examples.inheritance.joined.subclass.dao;

import ua.challenge.hibernate.examples.inheritance.joined.subclass.entity.JoinOwner;

/**
 * Created by Денис on 25.08.2016.
 */
public interface JoinOwnerDAO {
    void save(JoinOwner owner);
}