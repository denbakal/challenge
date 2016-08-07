package ua.challenge.hibernate.examples.relationship.one.to.many.bidirectional.dao.impl;

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
import ua.challenge.hibernate.examples.relationship.one.to.many.bidirectional.dao.TeamDAO;
import ua.challenge.hibernate.examples.relationship.one.to.many.bidirectional.entity.Player;
import ua.challenge.hibernate.examples.relationship.one.to.many.bidirectional.entity.Team;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by d.bakal on 8/7/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {DemoAppApplication.class, HibernateConfig.class})
@TransactionConfiguration(defaultRollback = false)
public class TeamDAOImplTest {

    @Autowired
    private TeamDAO teamDAO;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    @Transactional
    public void testSave() throws Exception {
        Team team = new Team();
        team.setName("Barcelona");

        Player player1 = new Player();
        player1.setLastName("Neymar");
        Player player2 = new Player();
        player2.setLastName("Messi");

        player1.setTeam(team);
        player2.setTeam(team);

        Set<Player> players = new HashSet<>();
        players.add(player1);
        players.add(player2);

        team.setPlayers(players);

        teamDAO.save(team);
    }
}