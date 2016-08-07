package ua.challenge.hibernate.examples.relationship.one.to.many.bidirectional.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.challenge.hibernate.examples.relationship.one.to.many.bidirectional.dao.TeamDAO;
import ua.challenge.hibernate.examples.relationship.one.to.many.bidirectional.entity.Team;

/**
 * Created by d.bakal on 8/7/2016.
 */
@Repository
public class TeamDAOImpl implements TeamDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(Team team) {
        sessionFactory.getCurrentSession().save(team);
    }
}
