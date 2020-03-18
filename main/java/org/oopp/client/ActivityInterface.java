package org.oopp.client;

import org.oopp.server.database.Activity;
import org.oopp.server.database.Data;
import org.oopp.server.database.User;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Controller class for the Activity page in the GUI.
 */
public class ActivityInterface {

    public static String url = Url.url;

    public ActivityInterface() {
    }

    /**
     * Creates a new Activity from the selected options by the user, and
     * posts the activity to the server.
     *
     * @param name      Name of the activity.
     * @param insteadOf Name of the activity didn't do.
     * @param user      The user.
     * @return An Activity object.
     */
    public static Activity addActivity(String name, String insteadOf,
                                       User user,
                                       Object... args) {
        Date date = Calendar.getInstance().getTime();
        Activity activity = new Activity(name, insteadOf, user, date, args);
        RestTemplate restTemplate = new RestTemplate();
        String resourceUrl = url + "/addactivity";
        ResponseEntity<Activity> response = restTemplate.postForEntity(
                resourceUrl, activity, Activity.class);
        return response.getBody();
    }

    /**
     * Retrieves all the activities from all users from the database.
     *
     * @return ArrayList of the queried activities.
     */

    public static ArrayList<Activity> getAllActivities() {
        RestTemplate restTemplate = new RestTemplate();
        String resourceUrl = url + "/allactivities";
        ResponseEntity<ArrayList<Activity>> response = restTemplate.exchange(
                resourceUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ArrayList<Activity>>() {
                });

        return response.getBody();
    }

    /** This method gets the activities a user has done in the past.
     *
     * @param userId the ID of the user
     * @return an array of the user's activities
     */
    public static ArrayList<Activity> getActivitiesUser(String userId) {
        RestTemplate restTemplate = new RestTemplate();
        String resourceUrl =
                url + "/getuseractivities?id=" + userId;
        ResponseEntity<ArrayList<Activity>> response = restTemplate.exchange(
                resourceUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ArrayList<Activity>>() {
                });
        return response.getBody();
    }

    /**
     * Deletes a single activity in the database.
     *
     * @param activityId The activityId of the to be deleted activity.
     */
    public static void deleteActivity(int activityId) {
        RestTemplate restTemplate = new RestTemplate();
        String resourceUrl = url + "/activities/delete?id=" + activityId;
        ResponseEntity<ArrayList> response =
                restTemplate.getForEntity(resourceUrl, ArrayList.class);
    }

    /**
     * Returns a single activity from the database with a matching id.
     *
     * @param activityId The activityId of the activity.
     * @return An Activity object from the database.
     */
    public static Activity getActivity(int activityId) {
        RestTemplate restTemplate = new RestTemplate();
        String resourceUrl =
                url + "/getactivity?id=" + activityId;
        ResponseEntity<Activity> response =
                restTemplate.getForEntity(resourceUrl, Activity.class);
        return response.getBody();
    }

    /**
     * Retrieved the database statistics of certain activity types.
     *
     * @param activityType The name of the database type.
     * @return A Data object containing the climate data of a certain activity.
     */
    public static Data getActivityStats(String activityType) {
        RestTemplate restTemplate = new RestTemplate();
        String resourceUrl = url + "/activitystats"
                + "?activityType=" + activityType;
        return restTemplate.getForObject(
                resourceUrl, Data.class);
    }

}
