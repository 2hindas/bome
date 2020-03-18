package org.oopp.server.database;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "UserBadges")
public class UserBadges {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "userbadgeId")
    private int userbadgeId;

    @Column(name = "userId")
    private String userId;

    @Column(name = "badgeId")
    private int badgeId;


    public UserBadges() {
    }


    /**
     * Constructor for user ID and UserBadges ID.
     *
     * @param userId  Name of User ID.
     * @param badgeId Name of UserBadges ID.
     */

    public UserBadges(String userId, int badgeId) {

        this.userId = userId;
        this.badgeId = badgeId;
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getBadgeId() {
        return badgeId;
    }

    public void setBadgeId(int badgeId) {
        this.badgeId = badgeId;
    }

    public int getUserbadgeId() {
        return userbadgeId;
    }

    public void setUserbadgeId(int userbadgeId) {
        this.userbadgeId = userbadgeId;
    }
}


