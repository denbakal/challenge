package ua.challenge.hibernate.examples.relationship.one.to.one.common.primary.keys.dao;

import ua.challenge.hibernate.examples.relationship.one.to.one.common.primary.keys.entity.Product;

/**
 * Created by d.bakal on 8/21/2016.
 */
public interface ProductDAO {
    void save(Product product);
}
