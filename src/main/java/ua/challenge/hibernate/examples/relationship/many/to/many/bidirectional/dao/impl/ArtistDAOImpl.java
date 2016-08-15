package ua.challenge.hibernate.examples.relationship.many.to.many.bidirectional.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.challenge.hibernate.examples.relationship.many.to.many.bidirectional.dao.ArtistDAO;
import ua.challenge.hibernate.examples.relationship.many.to.many.bidirectional.entity.Artist;

/**
 * Created by d.bakal on 8/15/2016.
 */
@Repository
public class ArtistDAOImpl implements ArtistDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(Artist artist) {
        sessionFactory.getCurrentSession().save(artist);
    }
}
