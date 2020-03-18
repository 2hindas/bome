package org.oopp.server.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class FriendshipController {

    @Autowired
    FriendshipRepository friendshipRepository;

    @Autowired
    UserRepository userRepository;


    /**
     * Check if the friendship between two users already exists.
     * If not, creates a new friendship between two users and saves it to the database.
     *
     * @param id1 id of the first User.
     * @param id2 id of the second User.
     * @return a Friendship object containing both User ids.
     */
    @GetMapping(value = "/addfriendship")
    public ResponseEntity<Friendship> addFriendship(@RequestParam String id1,
                                                    @RequestParam String id2) {
        if (checkFriendship(id1, id2) == false) {
            System.out.println("Post request! (new friendship)");
            Friendship friendship1 = new Friendship(id1, id2);
            Friendship friendship2 = new Friendship(id2, id1);
            friendshipRepository.save(friendship1);
            friendshipRepository.save(friendship2);
            return ResponseEntity.accepted().body(friendship1);
        } else {
            System.out.println("Friendship already exists");
            Friendship friendship = friendshipRepository.existsFriendship(id1, id2);
            return ResponseEntity.accepted().body(friendship);
        }
    }

    /**
     * Get a list of friends of a certain User.
     *
     * @param id id of the User who's friends are wanted.
     * @return Array List of friends.
     */
    @GetMapping(value = "/getfriends")
    public ResponseEntity<ArrayList<User>> getFriends(@RequestParam String id) {
        System.out.println("Get request! (get friends)");
        ArrayList<String> listOfFriendsId = friendshipRepository.listOfFriends(id);
        ArrayList<User> listOfFriends = new ArrayList<>();
        for (int i = 0; i < listOfFriendsId.size(); i++) {
            User user = userRepository.findUserById(listOfFriendsId.get(i));
            listOfFriends.add(user);
        }
        return ResponseEntity.accepted().body(listOfFriends);
    }

    /**
     * Checks if the friendship already exists between two users.
     *
     * @param id1 id of the first User.
     * @param id2 id of the second User.
     * @return True if the friendship exists, false otherwise.
     */
    @GetMapping(value = "/checkfriendship")
    public boolean checkFriendship(@RequestParam String id1, @RequestParam String id2) {
        Friendship friendship = friendshipRepository.existsFriendship(id1, id2);
        if (friendship == null) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Deletes a friendship from the database.
     *
     * @param id1 id of the first User.
     * @param id2 id of the second User.
     * @return updated list of friends of the first User.
     */
    @GetMapping(value = "/deletefriend")
    public ResponseEntity<ArrayList<User>> deleteFriend(@RequestParam String id1,
                                                        @RequestParam String id2) {
        System.out.println("Request to delete friendship");
        friendshipRepository.deleteFriendship(id1, id2);
        friendshipRepository.deleteFriendship(id2, id1);
        return getFriends(id1);
    }


}
