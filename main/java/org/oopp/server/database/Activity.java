package org.oopp.server.database;

import org.oopp.client.ActivityInterface;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name = "Activities")
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "activity_id")
    private int activityId;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "activity_type")
    private String activityType;

    @Column(name = "activity_name")
    private String name;

    @Column(name = "instead_of_activity")
    private String insteadOf;

    @Column(name = "panel_surface")
    private int panelSurface;

    @Column(name = "trees_planted")
    private int treesPlanted;

    @Column(name = "local_meals")
    private int localMeals;

    @Column(name = "distance_traveled")
    private int travelDistance; // km

    @Column(name = "emission_saving")
    private double emissionSaving; // kg

    @Column(name = "water_saving")
    private double waterSaving;  // L

    @Column(name = "land_saving")
    private double landSaving;  // km^2

    @Column(name = "date")
    private Date date;

    @Transient
    private User user;


    /**
     * Empty constructor for deserialization purposes.
     */
    public Activity() {
    }


    /**
     * Constructor for making a new Activity, with data filled in by the user.
     *
     * @param name      Name of the activity.
     * @param insteadOf Name of the activity the user didn't do, and by doing
     *                  so saved emissions etc.
     * @param user      The user.
     * @param date      The date and time of entering the activity.
     */
    public Activity(String name, String insteadOf, User user, Date date,
                    Object... args) {

        this.name = name.substring(2);
        this.insteadOf = insteadOf.substring(2);
        this.user = user;
        this.date = date;
        userId = user.getUserId();

        /*DataController controller = new DataController();*/

        if (name.charAt(0) == 'f') {
            // new food activity

            Data dataBefore = ActivityInterface.getActivityStats(insteadOf);
            Data dataAfter = ActivityInterface.getActivityStats(name);

            double co2Before = dataBefore.getCo2();
            double co2After = dataAfter.getCo2();
            double co2Difference = co2Before - co2After;
            this.emissionSaving = co2Difference;

            double waterBefore = dataBefore.getWater();
            double waterAfter = dataAfter.getWater();
            double waterDifference = waterBefore - waterAfter;
            this.waterSaving = waterDifference;

            double landBefore = dataBefore.getLand();
            double landAfter = dataAfter.getLand();
            double landDifference = landBefore - landAfter;
            this.landSaving = landDifference;

            this.localMeals = 0;
            if ((boolean) args[0] == true) {
                this.localMeals = 1;
            }

            this.panelSurface = 0;
            this.travelDistance = 0;
            this.treesPlanted = 0;

        } else if (name.charAt(0) == 't') {
            // new transport activity

            int distance = (int) args[0];


            Data dataBefore = ActivityInterface.getActivityStats(insteadOf);
            Data dataAfter = ActivityInterface.getActivityStats(name);

            double co2Before = dataBefore.getCo2();
            double co2After = dataAfter.getCo2();
            double co2Difference = co2Before - co2After;
            co2Difference = co2Difference * distance;
            this.emissionSaving = co2Difference;

            this.waterSaving = 0;
            this.landSaving = 0;
            this.panelSurface = 0;
            this.treesPlanted = 0;
            this.localMeals = 0;

            this.travelDistance = distance;

        } else if (name.charAt(0) == 'p') {
            this.emissionSaving = 0;
            this.waterSaving = 0;
            this.landSaving = 0;
            this.panelSurface = 0;
            this.travelDistance = 0;
            this.localMeals = 0;

            this.treesPlanted = 1;
        } else if (name.charAt(0) == 'h') {

            double reduction = Double.parseDouble(insteadOf);
            int weeks = Integer.parseInt((String) args[0]);

            Data dataGas = ActivityInterface.getActivityStats("h_gas");
            Data dataElec = ActivityInterface.getActivityStats("h_electricity");

            double gasCo2 = dataGas.getCo2();
            double elecCo2 = dataElec.getCo2();

            if (this.name.equals("gas")) {
                emissionSaving = reduction * weeks * gasCo2;
            }

            if (this.name.equals("electricity")) {
                emissionSaving = reduction * weeks * elecCo2;
            }

            this.waterSaving = 0;
            this.landSaving = 0;
            this.panelSurface = 0;
            this.travelDistance = 0;
            this.treesPlanted = 0;
            this.localMeals = 0;

        } else if (name.charAt(0) == 's') {

            Data solar = ActivityInterface.getActivityStats("s_solar_energy");
            Data electricity = ActivityInterface.getActivityStats("s_solar");

            final double solarEnergy = solar.getCo2();
            final double elecCo2 = electricity.getCo2();

            this.panelSurface = Integer.parseInt((String) args[0]);
            this.treesPlanted = 0;
            this.travelDistance = 0;
            this.emissionSaving = panelSurface * solarEnergy * elecCo2;
            this.waterSaving = 0;
            this.landSaving = 0;
            this.localMeals = 0;

        } else {
            this.panelSurface = 0;
            this.treesPlanted = 0;
            this.travelDistance = 0;
            this.emissionSaving = 0;
            this.waterSaving = 0;
            this.landSaving = 0;
            this.localMeals = 0;
        }
    }


    public String getName() {
        return name;
    }

    public User getUser() {
        return user;
    }

    public int getPanelSurface() {
        return panelSurface;
    }

    public int getTreesPlanted() {
        return treesPlanted;
    }

    public int getTravelDistance() {
        return travelDistance;
    }

    public String getInsteadOf() {
        return insteadOf;
    }

    public double getEmissionSaving() {
        return emissionSaving;
    }

    public double getWaterSaving() {
        return waterSaving;
    }

    public double getLandSaving() {
        return landSaving;
    }

    public Date getDate() {
        return date;
    }

    public int getActivityId() {
        return activityId;
    }

    public String getUserId() {
        return userId;
    }

    public int getLocalMeals() {
        return localMeals;
    }
}

