package ua.challenge.hibernate.examples.relationship.many.to.one.unidirectional.dao;

import ua.challenge.hibernate.examples.relationship.many.to.one.unidirectional.entity.Bid;

/**
 * Created by d.bakal on 8/14/2016.
 */
public interface BidDAO {
    void save(Bid bid);
}
