package org.oopp.client;

import org.oopp.server.database.User;
import org.oopp.server.database.UserBadges;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

public class BadgeInterface {

    public static String url = Url.url;

    /**
     * Empty constructor.
     */
    public BadgeInterface() {

    }

    /**
     * Adds a new badge to a user.
     * @param user The user who achieved a new badge.
     * @param badgeId The id of the type of badge the user achieved.
     * @return A new UserBadges object with the user and the badge.
     */
    public static UserBadges addBadge(User user, int badgeId) {
        String userId = user.getUserId();
        RestTemplate restTemplate = new RestTemplate();
        String resourceUrl =
                url + "/newbadge?userId=" + userId + "&badgeId"
                        + "=" + badgeId;
        ResponseEntity<UserBadges> response =
                restTemplate.getForEntity(resourceUrl,
                UserBadges.class);
        return response.getBody();

    }

    /**
     * Retrieves all the badges of a single user.
     * @param user A user object.
     * @return An ArrayList of the badgeId's of the badges.
     */
    public static ArrayList<Integer> getBadges(User user) {
        String userId = user.getUserId();
        RestTemplate restTemplate = new RestTemplate();
        String resourceUrl = url + "/getuserbadges?userId=" + userId;
        ResponseEntity<ArrayList<UserBadges>> response = restTemplate.exchange(
                resourceUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ArrayList<UserBadges>>() {
                });
        ArrayList<UserBadges> userbadges = response.getBody();
        ArrayList<Integer> badges = new ArrayList();
        for (UserBadges b : userbadges) {
            int badgeId = b.getBadgeId();
            badges.add(badgeId);
        }
        return badges;
    }
}
