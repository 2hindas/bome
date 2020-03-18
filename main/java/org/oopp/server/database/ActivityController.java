package org.oopp.server.database;

import com.google.gson.Gson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;


@RestController
public class ActivityController {

    @Autowired
    private ActivityRepository activityRepository;

    /**
     * Returns all activities from the database.
     * @return A ResponseEntity with an array list of all activities.
     */
    @GetMapping(value = "/allactivities")
    public ResponseEntity<ArrayList<Activity>> getAllActivities() {
        System.out.println("Get request! (all activities)");
        ArrayList<Activity> activities = activityRepository.findActivities();
        return ResponseEntity.accepted().body(activities);
    }

    /**
     * Returns a single activity from the database with matching id.
     * @param id The Id of the searched Activity.
     * @return A ResponseEntity with an Activity object, matching the id.
     */
    @GetMapping("/getactivity")
    public ResponseEntity<Activity> getActivity(@RequestParam int id) {
        return ResponseEntity.accepted().body(activityRepository.findActivityById(id).get(0));
    }


    /**
     * Displays all the activities after deletion of an activity with certain
     * id.
     * @return Array list of activities.
     */
    @GetMapping("/activities/delete")
    public ArrayList<Activity> deleteActivities(@RequestParam int id) {
        activityRepository.deleteActivity(id);
        return activityRepository.findActivities();
    }


    /**
     * Adds an activity to the database.
     * @param activity A new activity object.
     * @return A ResponseEntity containing the new object.
     */
    @PostMapping(value = "/addactivity")
    public ResponseEntity<Activity> addActivity(@RequestBody Activity activity) {
        System.out.println("Post request! (new activity)");
        activityRepository.save(activity);
        return ResponseEntity.accepted().body(activity);
    }

    /**
     * Adds an activity to the database from an Android device.
     * @param activityJson A new Json string representing the activity.
     * @return The activity just created
     */
    @PostMapping(value = "/addactivityandroid")
    public ResponseEntity<Activity> addActivityAndroid(@RequestBody String activityJson) {
        System.out.println("Post request! (new activity)");
        Gson gson = new Gson();
        Activity activity = gson.fromJson(activityJson, Activity.class);
        activityRepository.save(activity);
        return ResponseEntity.accepted().body(activity);
    }

    /**
     * Returns all activities of a certain user from the database.
     * @param id The Id of the User.
     * @return A ResponseEntity with an Array List of activities of the User.
     */
    @GetMapping("/getuseractivities")
    public ResponseEntity<ArrayList<Activity>> getUserActivities(@RequestParam String id) {
        System.out.println("Get request! (get User activities)");
        return ResponseEntity.accepted().body(activityRepository.findUserActivities(id));
    }

}
