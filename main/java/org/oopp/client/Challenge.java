package org.oopp.client;

import org.oopp.server.database.User;

import java.util.ArrayList;

public class Challenge {

    private boolean completed;
    private String name;
    private String description;
    private String startDate;
    private String endDate;


    private int points;
    private int groupSize;
    private ArrayList<User> achieved;

    /**
     * Constructor for a challenge object. Almost all the data is filled in by
     * the users.
     *
     * @param name        The name of the challenge.
     * @param description The description fo the challenge: what should the
     *                    users achieve
     * @param startDate   The date the challenge starts
     * @param endDate     The last day the challenge can still be achieved.
     * @param points      How many points the challenge is worth.
     */
    public Challenge(String name, String description, String startDate,
                     String endDate, int points, int groupSize) {
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.points = points;
        this.groupSize = groupSize;
        this.completed = false;
        achieved = new ArrayList<User>();
    }

    /**
     * Saves users who have achieved the challenge.
     *
     * @param user UserDb who achieved the challenge.
     */
    public void userAchieved(User user) {
        if (!achieved.contains(user)) {
            achieved.add(user);
        }
        if (achieved.size() == groupSize) {
            completed = true;
        }
    }

    /**
     * Increases the group size when a new user enters a group with an
     * already existing challenge. This only happens if the challenge was not
     * already completed.
     */
    public void newUser() {
        if (!completed) {
            this.groupSize++;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getGroupSize() {
        return groupSize;
    }

    public boolean isCompleted() {
        return completed;
    }

    public String getStartDate() {
        return startDate;
    }

    public ArrayList<User> getAchieved() {
        return achieved;
    }
}
