package org.oopp.client;

import javafx.concurrent.Task;
import org.oopp.server.database.Activity;
import org.springframework.web.client.ResourceAccessException;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * This task tries to get the activities from the database.
 * It tries 10 accesses with 5 seconds in between.
 */
public class ShowHistory extends Task<ArrayList<Activity>> {

    private String userId;

    /**
     * Empty Constructor.
     */
    public ShowHistory() {
    }

    @Override
    protected ArrayList<Activity> call() throws Exception {
        ArrayList<Activity> activities = null;
        int repeat = 1;

        while (repeat < 11) {
            try {
                activities = ActivityInterface.getActivitiesUser(this.userId);
                repeat = 11;
                System.out.println("History successfully fetched.");
                return activities;
            } catch (ResourceAccessException e) {
                System.out.println(e.toString());
                System.out.println("History could not be fetched. Trying again.");
                TimeUnit.SECONDS.sleep(5);
            }
        }

        System.out.println("History could not be fetched.");
        return null;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
