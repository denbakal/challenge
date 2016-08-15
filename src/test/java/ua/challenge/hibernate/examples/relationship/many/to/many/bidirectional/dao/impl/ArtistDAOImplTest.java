package ua.challenge.hibernate.examples.relationship.many.to.many.bidirectional.dao.impl;

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
import ua.challenge.hibernate.examples.relationship.many.to.many.bidirectional.dao.ArtistDAO;
import ua.challenge.hibernate.examples.relationship.many.to.many.bidirectional.entity.Artist;
import ua.challenge.hibernate.examples.relationship.many.to.many.bidirectional.entity.CD;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by d.bakal on 8/15/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {DemoAppApplication.class, HibernateConfig.class})
@TransactionConfiguration(defaultRollback = false)
public class ArtistDAOImplTest {

    @Autowired
    private ArtistDAO artistDAO;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    @Transactional
    public void testSave() throws Exception {
        CD cd = new CD();
        cd.setTitle("Brand New Day");

        List<CD> cds = new ArrayList<>();
        cds.add(cd);

        Artist artist = new Artist();
        artist.setFirstName("-");
        artist.setLastName("Sting");
        artist.setAppearsOnCDs(cds);

        artistDAO.save(artist);
    }
}