package org.oopp.server.database;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "Badges")
public class Badges {


    @Id
    @Column(name = "badge_id")
    private int badgeId;

    @Column(name = "badge_name")
    private String badgeName;

    @Column(name = "badge_image")
    private String badgeImage;


    public Badges() {
    }

    /**
     * The constructor for the badge object.
     * @param badgeId the unique id for the badge.
     * @param badgeName name of the badge.
     * @param badgeImage image of the badge.
     */
    public Badges(int badgeId, String badgeName, String badgeImage) {

        this.badgeId = badgeId;
        this.badgeName = badgeName;
        this.badgeImage = badgeImage;
    }


    public int getBadgeId() {
        return badgeId;
    }

    public void setBadgeId(int badgeId) {
        this.badgeId = badgeId;
    }

    public String getBadgeName() {
        return badgeName;
    }

    public void setBadgeName(String badgeName) {
        this.badgeName = badgeName;
    }

    public String getBadgeImage() {
        return badgeImage;
    }

    public void setBadgeImage(String badgeImage) {
        this.badgeImage = badgeImage;
    }
}




