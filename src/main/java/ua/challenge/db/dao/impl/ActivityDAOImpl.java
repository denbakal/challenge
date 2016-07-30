package ua.challenge.db.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.challenge.db.dao.ActivityDAO;
import ua.challenge.db.entity.Activity;

/**
 * Created by d.bakal on 7/3/2016.
 */
@Repository
public class ActivityDAOImpl implements ActivityDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(Activity activity) {
        sessionFactory.getCurrentSession().save(activity);
    }
}
