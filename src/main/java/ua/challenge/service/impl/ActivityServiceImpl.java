package ua.challenge.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.challenge.common.ActivityStatus;
import ua.challenge.db.dao.ActivityDAO;
import ua.challenge.db.entity.Activity;
import ua.challenge.service.ActivityService;

/**
 * Created by d.bakal on 7/3/2016.
 */
@Service
public class ActivityServiceImpl implements ActivityService {
    @Autowired
    private ActivityDAO activityDAO;

    @Override
    @Transactional
    public void save(Activity activity) {
        activityDAO.save(activity);
    }

    @Override
    @Async
    public void process(String actionName) {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Activity activity = new Activity();
        activity.setActionName(actionName);
        activity.setActivityStatus(ActivityStatus.IN_SERVICE.name());
        save(activity);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        activity.setActivityStatus(ActivityStatus.SUCCESS.name());
        save(activity);
    }
}
