package ua.challenge.service;

import ua.challenge.db.entity.Activity;

/**
 * Created by d.bakal on 7/3/2016.
 */
public interface ActivityService {
    void save(Activity activity);
    void process(String actionName);
}
