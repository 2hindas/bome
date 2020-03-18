package org.oopp.client;

import org.oopp.server.database.User;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Group {

    private int groupId;
    private String groupName;
    private ArrayList<User> members;
    private Date dateCreated;
    private int daysRunnings;
    private ArrayList<Challenge> challenges;
    private User leader;

    /**
     * Empty constructor for deserialization purposes.
     */
    public Group() {
    }


    /**
     * Constructor for a new Group object.
     *
     * @param groupName   Name of the group.
     * @param members     List of members of the group.
     */
    public Group(String groupName, ArrayList<User> members) {
        this.groupId = 1; // should be UID
        this.groupName = groupName;
        this.members = members;
        this.daysRunnings = 0;
        this.challenges = new ArrayList<Challenge>();
        dateCreated = Calendar.getInstance().getTime();
        leader = null;

        // store in database
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public User getLeader() {
        return leader;
    }

    public void setLeader(User leader) {
        this.leader = leader;
    }

    public int getGroupId() {
        return groupId;
    }

    //    public Date getDateCreated() {
    //        return dateCreated;
    //    }

    //    public int getDaysRunnings() {
    //        return daysRunnings;
    //    }

    /**
     * Adds a new group challenge.
     *
     * @param challenge A new challenge for all group memebers.
     */
    public void addChallenge(Challenge challenge) {
        challenges.add(challenge);

        //store in database
    }


    /**
     * Getter for the all the challenges in a group.
     *
     * @return An ArrayList of all the challenges.
     */
    public ArrayList<Challenge> getChallenges() {
        return challenges;
    }

    /**
     * Removes a challenge from a group.
     *
     * @param challenge The challenge to be removed.
     */
    public void removeChallenge(Challenge challenge) {
        challenges.remove(challenge);
        //update database.
    }


    /**
     * Adds a new user to an already existing group.
     * @param user A new user.
     */
    public void addMember(User user) {
        members.add(user);
        // store in database
    }

    /**
     * Getter for the members.
     * @return ArrayList of all the members.
     */
    public ArrayList<User> getMembers() {
        return members;
    }

    /**
     * Removes a member from a group.
     *
     * @param user UserDb that should be removed from a group.
     */
    public void removeMember(User user) {
        members.remove(user);
        // store in database
    }

}
