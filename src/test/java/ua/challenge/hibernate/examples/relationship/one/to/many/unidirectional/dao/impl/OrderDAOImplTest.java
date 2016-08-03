package ua.challenge.hibernate.examples.relationship.one.to.many.unidirectional.dao.impl;

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
import ua.challenge.hibernate.examples.relationship.one.to.many.unidirectional.dao.OrderDAO;
import ua.challenge.hibernate.examples.relationship.one.to.many.unidirectional.entity.Order;
import ua.challenge.hibernate.examples.relationship.one.to.many.unidirectional.entity.OrderLine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by d.bakal on 8/2/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {DemoAppApplication.class, HibernateConfig.class})
@TransactionConfiguration(defaultRollback = false)
public class OrderDAOImplTest {

    @Autowired
    private OrderDAO orderDAO;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    @Transactional
    public void testSave() throws Exception {
        OrderLine orderLine1 = new OrderLine();
        orderLine1.setItem("Lemon");
        orderLine1.setQuantity(11);
        orderLine1.setUnitPrice(2.0);

        OrderLine orderLine2 = new OrderLine();
        orderLine2.setItem("Orange");
        orderLine2.setQuantity(4);
        orderLine2.setUnitPrice(9.2);

        List<OrderLine> orderLines = new ArrayList<>();
        orderLines.add(orderLine1);
        orderLines.add(orderLine2);

        Order order = new Order();
        order.setCreationDate(new Date(System.currentTimeMillis()));
        order.setOrderLines(orderLines);

        orderDAO.save(order);
    }
}