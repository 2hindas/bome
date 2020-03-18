package org.oopp.client;

public class Badge {

    private int badgeId;
    private String badgeName;
    private String imageUrl;
    private String achievement;

    /**
     * Constructor for a badge.
     * @param badgeId     The ID of the badge type.
     * @param badgeName   The short display name of the badge.
     * @param imageUrl    The URL of the image of the badge.
     * @param achievement The achievement of the user that enables this badge.
     */
    public Badge(int badgeId, String badgeName, String imageUrl,
                 String achievement) {
        this.badgeId = badgeId;
        this.badgeName = badgeName;
        this.imageUrl = imageUrl;
        this.achievement = achievement;
    }

    public int getBadgeId() {
        return badgeId;
    }

    public String getBadgeName() {
        return badgeName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getAchievement() {
        return achievement;
    }

}
