package ua.challenge.db.entity;

import ua.challenge.common.ActivityStatus;

import javax.persistence.*;

/**
 * Created by d.bakal on 7/3/2016.
 */
@Entity
@Table(name = "activities")
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String actionName;

    @Enumerated(EnumType.STRING)
    private ActivityStatus activityStatus;

    public Activity() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public ActivityStatus getActivityStatus() {
        return activityStatus;
    }

    public void setActivityStatus(ActivityStatus activityStatus) {
        this.activityStatus = activityStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Activity activity = (Activity) o;

        if (id != activity.id) return false;
        if (actionName != null ? !actionName.equals(activity.actionName) : activity.actionName != null) return false;
        return activityStatus == activity.activityStatus;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (actionName != null ? actionName.hashCode() : 0);
        result = 31 * result + (activityStatus != null ? activityStatus.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Activity{" +
                "id=" + id +
                ", actionName='" + actionName + '\'' +
                ", activityStatus=" + activityStatus +
                '}';
    }
}
