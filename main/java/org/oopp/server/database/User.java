package org.oopp.server.database;

import org.oopp.client.Badge;
import org.oopp.client.Group;

import java.util.ArrayList;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name = "Users")
public class User {

    // user data
    @Id
    @Column(name = "user_Id")
    private String userId;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "photo")
    private String photo;

    // game stats
    @Column(name = "eco_footprint")
    private double ecologicalFootprint;

    @Column(name = "total_points")
    private int totalPoints;

    @Column(name = "level")
    private String level;

    @Transient
    private ArrayList<Badge> badges;


    // badge stats
    @Column(name = "num_vegan")
    private int numVeganMeals;

    @Column(name = "num_veg")
    private int numVegMeals;

    @Column(name = "num_pesc")
    private int numPescMeals;

    @Column(name = "water_all")
    private int totalWaterSaved;

    @Column(name = "co2_all")
    private int totalEmissionSaved;

    @Column(name = "land_all")
    private int totalLandSaved;

    @Column(name = "num_trees_planted")
    private int numTreesPlanted;

    @Column(name = "num_solar_panels")
    private int numSolarPanels;

    @Column(name = "num_local_meals")
    private int numLocalMeals;

    @Column(name = "water_day")
    private int waterDay;

    @Column(name = "water_week")
    private int waterWeek;

    @Column(name = "water_month")
    private int waterMonth;


    @Column(name = "co2_day")
    private int co2Day;

    @Column(name = "co2_week")
    private int co2Week;

    @Column(name = "co2_month")
    private int co2Month;


    @Column(name = "land_day")
    private int landDay;

    @Column(name = "land_week")
    private int landWeek;

    @Column(name = "land_month")
    private int landMonth;


    @Column(name = "km_cycle")
    private int kmCycled;

    @Column(name = "km_walk")
    private int kmWalked;

    @Column(name = "km_public_transport")
    private int kmPublicTransport;


    @Transient
    private ArrayList<Activity> activities;
    @Transient
    private ArrayList<Group> groups;

    /**
     * Empty constructor for deserialization purposes.
     */
    public User() {

    }

    /**
     * Constructor for an user. Basic information about the user is passed as
     * an argument. Other values are either calculated depending on questions
     * answered by the user, or initialized as empty values.
     *
     * @param userId   The id of the user.
     * @param fullName The full name of the user.
     * @param userName The username of the user.
     * @param photo    The photo of the user.
     */
    public User(String userId, String fullName, String userName, String photo) {
        this.fullName = fullName;
        this.userName = userName;
        this.photo = photo;
        this.userId = userId; // generate userID


        ecologicalFootprint = 0; // should calculate the footprint
        totalPoints = 0;
        level = "climate change denier";

        activities = new ArrayList<Activity>();
        badges = new ArrayList<Badge>();
        groups = new ArrayList<Group>();

        numPescMeals = 0;
        numVeganMeals = 0;
        numVegMeals = 0;
        totalWaterSaved = 0;
        totalEmissionSaved = 0;
        totalLandSaved = 0;
        numLocalMeals = 0;
        numTreesPlanted = 0;
        numSolarPanels = 0;

        //store user in database
    }


    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public double getEcologicalFootprint() {
        return ecologicalFootprint;
    }

    public void setEcologicalFootprint(double ecologicalFootprint) {
        this.ecologicalFootprint = ecologicalFootprint;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public ArrayList<Badge> getBadges() {
        return badges;
    }

    public void setBadges(ArrayList<Badge> badges) {
        this.badges = badges;
    }

    public int getNumVeganMeals() {
        return numVeganMeals;
    }

    public void setNumVeganMeals(int numVeganMeals) {
        this.numVeganMeals = numVeganMeals;
    }

    public int getNumVegMeals() {
        return numVegMeals;
    }

    public void setNumVegMeals(int numVegMeals) {
        this.numVegMeals = numVegMeals;
    }

    public int getNumPescMeals() {
        return numPescMeals;
    }

    public void setNumPescMeals(int numPescMeals) {
        this.numPescMeals = numPescMeals;
    }

    public int getTotalWaterSaved() {
        return totalWaterSaved;
    }

    public void setTotalWaterSaved(int totalWaterSaved) {
        this.totalWaterSaved = totalWaterSaved;
    }

    public int getTotalEmissionSaved() {
        return totalEmissionSaved;
    }

    public void setTotalEmissionSaved(int totalEmissionSaved) {
        this.totalEmissionSaved = totalEmissionSaved;
    }

    public int getTotalLandSaved() {
        return totalLandSaved;
    }

    public void setTotalLandSaved(int totalLandSaved) {
        this.totalLandSaved = totalLandSaved;
    }

    public int getNumTreesPlanted() {
        return numTreesPlanted;
    }

    public void setNumTreesPlanted(int numTreesPlanted) {
        this.numTreesPlanted = numTreesPlanted;
    }

    public int getNumSolarPanels() {
        return numSolarPanels;
    }

    public void setNumSolarPanels(int numSolarPanels) {
        this.numSolarPanels = numSolarPanels;
    }

    public ArrayList<Activity> getActivities() {
        return activities;
    }

    public void setActivities(ArrayList<Activity> activities) {
        this.activities = activities;
    }

    public ArrayList<Group> getGroups() {
        return groups;
    }

    public void setGroups(ArrayList<Group> groups) {
        this.groups = groups;
    }

    public int getWaterDay() {
        return waterDay;
    }

    public void setWaterDay(int waterDay) {
        this.waterDay = waterDay;
    }

    public int getWaterWeek() {
        return waterWeek;
    }

    public void setWaterWeek(int waterWeek) {
        this.waterWeek = waterWeek;
    }

    public int getWaterMonth() {
        return waterMonth;
    }

    public void setWaterMonth(int waterMonth) {
        this.waterMonth = waterMonth;
    }

    public int getCo2Day() {
        return co2Day;
    }

    public void setCo2Day(int co2Day) {
        this.co2Day = co2Day;
    }

    public int getCo2Week() {
        return co2Week;
    }

    public void setCo2Week(int co2Week) {
        this.co2Week = co2Week;
    }

    public int getCo2Month() {
        return co2Month;
    }

    public void setCo2Month(int co2Month) {
        this.co2Month = co2Month;
    }

    public int getLandDay() {
        return landDay;
    }

    public void setLandDay(int landDay) {
        this.landDay = landDay;
    }

    public int getLandWeek() {
        return landWeek;
    }

    public void setLandWeek(int landWeek) {
        this.landWeek = landWeek;
    }

    public int getLandMonth() {
        return landMonth;
    }

    public int getNumLocalMeals() {
        return numLocalMeals;
    }

    public void setNumLocalMeals(int numLocalMeals) {
        this.numLocalMeals = numLocalMeals;
    }

    public void setLandMonth(int landMonth) {
        this.landMonth = landMonth;
    }

    public int getKmCycled() {
        return kmCycled;
    }

    public void setKmCycled(int kmCycled) {
        this.kmCycled = kmCycled;
    }

    public int getKmWalked() {
        return kmWalked;
    }

    public void setKmWalked(int kmWalked) {
        this.kmWalked = kmWalked;
    }

    public int getKmPublicTransport() {
        return kmPublicTransport;
    }

    public void setKmPublicTransport(int kmPublicTransport) {
        this.kmPublicTransport = kmPublicTransport;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        User user = (User) obj;
        return userId.equals(user.userId)
                && Objects.equals(fullName, user.fullName)
                && Objects.equals(userName, user.userName)
                && Objects.equals(photo, user.photo);
    }
}
