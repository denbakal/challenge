package ua.challenge.service.impl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.challenge.DemoAppApplication;
import ua.challenge.common.ActivityStatus;
import ua.challenge.config.HibernateConfig;
import ua.challenge.db.entity.Activity;
import ua.challenge.service.ActivityService;

import static org.junit.Assert.*;

/**
 * Created by d.bakal on 7/31/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {DemoAppApplication.class, HibernateConfig.class})
public class ActivityServiceImplTest {

    @Autowired
    private ActivityService activityService;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testSave() throws Exception {
        Activity activity = new Activity();
        activity.setActionName("Test Action");
        activity.setActivityStatus(ActivityStatus.IN_SERVICE);
        activityService.save(activity);
    }
}