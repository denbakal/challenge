package ua.challenge.hibernate.examples.relationship.one.to.one.common.primary.keys.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.challenge.hibernate.examples.relationship.one.to.one.common.primary.keys.dao.ProductDAO;
import ua.challenge.hibernate.examples.relationship.one.to.one.common.primary.keys.entity.Product;

/**
 * Created by d.bakal on 8/21/2016.
 */
@Repository
public class ProductDAOImpl implements ProductDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(Product product) {
        sessionFactory.getCurrentSession().save(product);
    }
}
