package ua.challenge.hibernate.examples.relationship.many.to.one.unidirectional.dao.impl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import ua.challenge.DemoAppApplication;
import ua.challenge.config.HibernateConfig;
import ua.challenge.hibernate.examples.relationship.many.to.one.unidirectional.dao.BidDAO;
import ua.challenge.hibernate.examples.relationship.many.to.one.unidirectional.entity.Bid;
import ua.challenge.hibernate.examples.relationship.many.to.one.unidirectional.entity.Item;

import static org.junit.Assert.*;

/**
 * Created by d.bakal on 8/14/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {DemoAppApplication.class, HibernateConfig.class})
@TransactionConfiguration(defaultRollback = false)
public class BidDAOImplTest {

    @Autowired
    private BidDAO bidDAO;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    @Transactional
    public void testSave() throws Exception {
        Bid bid = new Bid();
        bid.setName("Test bid");

        Item item = new Item();
        item.setName("Test item 1");

        bid.setItem(item);

        bidDAO.save(bid);
    }
}