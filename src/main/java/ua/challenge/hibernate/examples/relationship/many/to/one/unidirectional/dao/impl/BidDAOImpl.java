package ua.challenge.hibernate.examples.relationship.many.to.one.unidirectional.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.challenge.hibernate.examples.relationship.many.to.one.unidirectional.dao.BidDAO;
import ua.challenge.hibernate.examples.relationship.many.to.one.unidirectional.entity.Bid;

/**
 * Created by d.bakal on 8/14/2016.
 */
@Repository
public class BidDAOImpl implements BidDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(Bid bid) {
        sessionFactory.getCurrentSession().save(bid);
    }
}
