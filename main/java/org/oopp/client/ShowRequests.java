package org.oopp.client;

import javafx.concurrent.Task;
import org.oopp.server.database.User;
import org.springframework.web.client.ResourceAccessException;

import java.util.ArrayList;

/**
 * This task tries to get the friends of a particular user from the database.
 */
public class ShowRequests extends Task<ArrayList<User>> {

    private User user;

    /**
     * Empty Constructor.
     */
    public ShowRequests(){

    }

    /**
     * Constructor with User.
     * @param user user of this ShowRequest
     */
    public ShowRequests(User user) {
        this.user = user;
    }

    @Override
    protected ArrayList<User> call() throws Exception {
        ArrayList<User> friends = null;

        Thread.sleep(2000);

        try {
            friends = UserInterface.getFriendRequests(user);
            System.out.println("Friend requests successfully fetched.");
            return friends;
        } catch (ResourceAccessException e) {
            System.out.println("Friend requests could not be fetched.");
        }

        return null;
    }
}
