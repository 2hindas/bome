package org.oopp.server.database;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "defaultvalues")
public class Data {

    @Id
    @Column(name = "activityType")
    private String activityType;

    @Column(name = "co2")
    private double co2;

    @Column(name = "water")
    private double water;

    @Column(name = "land")
    private double land;

    /**
     * Empty constructor.
     */
    public Data() {
    }

    /**
     * Constructor for a data object, containing stats of a single activity
     * type. For testing purposes.
     * @param activityType The type of activity.
     * @param co2 The amount of co2 savings.
     * @param water Tha amount of water savings.
     * @param land The amount of land savings.
     */
    public Data(String activityType, double co2, double water, double land) {
        this.activityType = activityType;
        this.co2 = co2;
        this.water = water;
        this.land = land;
    }

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public double getCo2() {
        return co2;
    }

    public void setCo2(double co2) {
        this.co2 = co2;
    }

    public double getWater() {
        return water;
    }

    public void setWater(double water) {
        this.water = water;
    }

    public double getLand() {
        return land;
    }

    public void setLand(double land) {
        this.land = land;
    }
}




