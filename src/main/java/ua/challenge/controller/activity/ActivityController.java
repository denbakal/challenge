package ua.challenge.controller.activity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.challenge.db.entity.Activity;
import ua.challenge.service.ActivityService;

/**
 * Created by d.bakal on 7/3/2016.
 */
@RestController
@RequestMapping("/activity")
public class ActivityController {
    @Autowired
    private ActivityService activityService;

    @RequestMapping(method = RequestMethod.POST)
    public void addActivity(@RequestParam String actionName) {
        activityService.process(actionName);
        System.out.println("actionName = " + actionName);
    }
}
