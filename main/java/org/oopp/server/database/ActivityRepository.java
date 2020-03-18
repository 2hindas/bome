package org.oopp.server.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

public interface ActivityRepository extends JpaRepository<Activity, Long> {

    /**
     * Retrieves all the  activities of a specific User.
     * @param id id of the User.
     * @return Array List of activities of the User.
     */
    @Query("SELECT a FROM Activity a WHERE userId = :id")
    ArrayList<Activity> findUserActivities(@Param("id") String id);

    /**
     * Selects all the activities with appropriate id.
     * @param id of the activity.
     * @return Array List of the activities.
     */
    @Query("SELECT a FROM Activity a WHERE activityId = :id")
    ArrayList<Activity> findActivityById(@Param("id") int id);

    /**
     * Selects all activities from the database.
     * @return All the activities.
     */
    @Query("SELECT a FROM Activity a")
    ArrayList<Activity> findActivities();

    /**
     * Deletes an activity with certain id.
     * @param id of the activity.
     */
    @Transactional
    @Modifying
    @Query("DELETE FROM Activity a WHERE activityId = :id")
    void deleteActivity(@Param("id") int id);


}
