package org.oopp.client;

import javafx.concurrent.Task;
import org.oopp.server.database.User;
import org.springframework.web.client.ResourceAccessException;

import java.util.ArrayList;

/**
 * This task tries to get the friends of a particular user from the database.
 */
public class ShowFriends extends Task<ArrayList<User>> {

    private User user;

    /**
     * Empty constructor.
     */
    public ShowFriends(){
    }

    /**
     * Constructor with User.
     * @param user user of this ShowFriends
     */
    public ShowFriends(User user) {
        this.user = user;
    }

    @Override
    protected ArrayList<User> call() throws Exception {
        ArrayList<User> friends = null;

        Thread.sleep(2000);

        try {
            friends = UserInterface.getFriends(user);
            System.out.println("Friends successfully fetched.");
            return friends;
        } catch (ResourceAccessException e) {
            System.out.println("Friends could not be fetched.");
        }

        return null;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
