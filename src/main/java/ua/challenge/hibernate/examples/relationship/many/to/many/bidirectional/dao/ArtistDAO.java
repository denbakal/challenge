package ua.challenge.hibernate.examples.relationship.many.to.many.bidirectional.dao;

import ua.challenge.hibernate.examples.relationship.many.to.many.bidirectional.entity.Artist;

/**
 * Created by d.bakal on 8/15/2016.
 */
public interface ArtistDAO {
    void save(Artist artist);
}
