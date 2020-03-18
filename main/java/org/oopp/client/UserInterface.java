package org.oopp.client;

import org.oopp.server.database.Friendship;
import org.oopp.server.database.PendingFriendship;
import org.oopp.server.database.User;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

public class UserInterface {

    public static String url = Url.url;

    /**
     * Empty constructor.
     */
    public UserInterface() {

    }

    /**
     * Sends a friendship request from one user to another by creating a new.
     * PendingFriendship and adding it to the database.
     *
     * @param requester The user who sends the request.
     * @param responder The user who receives the request.
     */
    public static PendingFriendship sendRequest(User requester, User responder) {
        String requesterId = requester.getUserId();
        String responderId = responder.getUserId();

        RestTemplate restTemplate = new RestTemplate();
        String resourceUrl =
                url + "/newpendingfriendship?requesterId="
                        + requesterId + "&responderId=" + responderId;
        ResponseEntity<PendingFriendship> response =
                restTemplate.getForEntity(resourceUrl, PendingFriendship.class);
        return response.getBody();
    }


    /**
     * The responder deletes a friendship request sent by the requester.
     * @param responder The responder who deletes the request.
     * @param requester The requester who sent the request.
     */
    public static PendingFriendship deleteRequest(User responder, User requester) {
        String responderId = responder.getUserId();
        String requesterId = requester.getUserId();

        RestTemplate restTemplate = new RestTemplate();
        String resourceUrl =
                url + "/deletependingfriendship?requesterId="
                        + requesterId + "&responderId=" + responderId;
        ResponseEntity<PendingFriendship> response =
                restTemplate.getForEntity(resourceUrl, PendingFriendship.class);
        return response.getBody();

    }


    /**
     * Confirms a friendship between two users.
     *
     * @param responder The user who accepts the friendship request.
     * @param requester The user who sent the request.
     */
    public static void acceptFriendship(User responder, User requester) {
        // delete user tuple from pending requests table
        deleteRequest(responder, requester);

        // add new user tuple to the friendship tables
        addFriend(responder, requester);
    }


    /**
     * Returns a list of all the users to which the user has sent a
     * friendship request.
     *
     * @param user The current user.
     * @return An ArrayList of all the users to whom the user has sent a
     *          friedship request.
     */
    public static ArrayList<User> getPendingFriends(User user) {
        String userId = user.getUserId();
        RestTemplate restTemplate = new RestTemplate();
        String resourceUrl = url + "/getpendingfriends?id=" + userId;
        ResponseEntity<ArrayList<User>> response = restTemplate.exchange(
                resourceUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ArrayList<User>>() {
                });
        return response.getBody();
    }



    /**
     * Returns a list of all the users who have sent a friendship request to
     * the user.
     *
     * @param user The current user.
     * @return An ArrayList of all the users who sent a friendship request to
     *          the current user.
     */
    public static ArrayList<User> getFriendRequests(User user) {
        String userId = user.getUserId();
        RestTemplate restTemplate = new RestTemplate();
        String resourceUrl = url + "/getfriendrequests?id=" + userId;
        ResponseEntity<ArrayList<User>> response = restTemplate.exchange(
                resourceUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ArrayList<User>>() {
                });
        return response.getBody();
    }


    /**
     * Returns a list of all the friends of a specific user.
     *
     * @param user A user object.
     * @return An arraylist of all the friends of the user.
     */
    public static ArrayList<User> getFriends(User user) {
        String userId = user.getUserId();
        RestTemplate restTemplate = new RestTemplate();
        String resourceUrl = url + "/getfriends?id=" + userId;
        ResponseEntity<ArrayList<User>> response = restTemplate.exchange(
                resourceUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ArrayList<User>>() {
                });
        return response.getBody();
    }

    /**
     * Checks whether two users are friends or not.
     *
     * @param user1 The first user.
     * @param user2 The second user.
     */
    public static boolean areFriends(User user1, User user2) {
        String id1 = user1.getUserId();
        String id2 = user2.getUserId();

        RestTemplate restTemplate = new RestTemplate();
        String resourceUrl =
                url + "/checkfriendship?id1="
                        + id1 + "&id2=" + id2;
        boolean response =
                restTemplate.getForObject(resourceUrl, Boolean.class);
        return response;
    }

    /**
     * Adds a new Friendship between 2 users.
     *
     * @param user1 First user.
     * @param user2 Second user.
     */
    public static Friendship addFriend(User user1, User user2) {
        String id1 = user1.getUserId();
        String id2 = user2.getUserId();

        RestTemplate restTemplate = new RestTemplate();
        String resourceUrl =
                url + "/addfriendship?id1="
                        + id1 + "&id2=" + id2;
        ResponseEntity<Friendship> response =
                restTemplate.getForEntity(resourceUrl, Friendship.class);
        return response.getBody();
    }

    /**
     * Removed a friend from user 1's friend list, and returns the list of the
     * remaining friends.
     *
     * @param user1 The user who removes a friend.
     * @param user2 The user who is removed from user 1's friend list.
     */
    public static ArrayList<User> deleteFriend(User user1, User user2) {
        String id1 = user1.getUserId();
        String id2 = user2.getUserId();

        RestTemplate restTemplate = new RestTemplate();
        String resourceUrl =
                url + "/deletefriend?id1="
                        + id1 + "&id2=" + id2;
        ResponseEntity<ArrayList<User>> response = restTemplate.exchange(
                resourceUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ArrayList<User>>() {
                });
        return response.getBody();
    }

    /**
     * Returns the userId of the current logged in user.
     *
     * @return The user id of the current user.
     */
    public static String getUserId() {
        RestTemplate restTemplate = new RestTemplate();
        String resourceUrl =
                url + "/userid";
        String userId = restTemplate.getForObject(resourceUrl, String.class);
        return userId;
    }

    /**
     * Returns a user from the database matching the userId.
     * @param userId The id to search a user.
     * @return A matching user.
     */
    public static User getUser(String userId) {
        RestTemplate restTemplate = new RestTemplate();
        String resourceUrl =
                url + "/getuser?id=" + userId ;
        User user = restTemplate.getForObject(resourceUrl, User.class);
        return user;
    }

    /**
     * Sends an updated User object to the database so the database can be
     * updated with the new values.
     * @param user An updated User object.
     * @return The user object.
     */
    public static User updateUser(User user) {
        RestTemplate restTemplate = new RestTemplate();
        String resourceUrl =
                url + "/updateuser";
        ResponseEntity<User> response = restTemplate.postForEntity(resourceUrl,
                user, User.class);
        return response.getBody();
    }


    /**
     * Searches the database for users with names or usernames matching the
     * input String.
     * @param name A String that a user fills in in an input field to find a
     *             user.
     * @return A list of all users that have matching names and/or usernames.
     */
    public static ArrayList<User> findUsersByName(String name) {
        name = name.replace(" ", "+");
        RestTemplate restTemplate = new RestTemplate();
        String resourceUrl =
                url + "/getbyboth?name=" + name ;
        ResponseEntity<ArrayList<User>> response = restTemplate.exchange(
                resourceUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ArrayList<User>>() {
                });
        return response.getBody();
    }



}
