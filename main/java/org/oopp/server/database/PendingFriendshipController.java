package org.oopp.server.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class PendingFriendshipController {

    @Autowired
    private PendingFriendshipRepository pendingFriendshipRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     * Returns a list of all the friendship requests a user has received.
     * @param id id of the user who's friendship requests we want.
     * @return list of all friendship requests a user has.
     */
    @GetMapping(value = "/getfriendrequests")
    public ResponseEntity<ArrayList<User>> getFriendRequests(@RequestParam String id) {

        ArrayList<User> pending = new ArrayList<User>();

        String[] pendingId =
                pendingFriendshipRepository.getRequestedFriendsId(id);

        for (String userId : pendingId) {
            User user1 = userRepository.findUserById(userId);
            pending.add(user1);
        }

        return ResponseEntity.accepted().body(pending);
    }

    /**
     * Returns a list of all the friendship requests a user has made.
     * @param id id of the user who's pending friendships we want.
     * @return list of all pending friends a user has.
     */
    @GetMapping(value = "/getpendingfriends")
    public ResponseEntity<ArrayList<User>> getPendingFriends(@RequestParam String id) {

        ArrayList<User> pending = new ArrayList<User>();

        String[] pendingId =
                pendingFriendshipRepository.getPendingFriendsId(id);

        for (String userId : pendingId) {
            User user1 = userRepository.findUserById(userId);
            pending.add(user1);
        }

        return ResponseEntity.accepted().body(pending);
    }

    /**
     * Adds a new PendingFriendshipId to the database.
     * @param requesterId id of the requester
     * @param responderId id of the responder
     * @return the added PendingFriendshipId
     */
    @GetMapping(value = "/newpendingfriendship")
    public ResponseEntity<PendingFriendship> newPendingFriendship(
            @RequestParam String requesterId, @RequestParam String responderId) {
        PendingFriendshipId pfId = new PendingFriendshipId(requesterId,
                responderId);
        PendingFriendship pf = new PendingFriendship(pfId);
        pendingFriendshipRepository.save(pf);
        return ResponseEntity.accepted().body(pf);
    }

    /**
     * Returns the PendingFriendshipId between the two Users.
     * @param requesterId id of the requester
     * @param responderId id of the responder
     * @return PendingFriendshipId
     */
    @GetMapping(value = "/getpendingfriendship")
    public ResponseEntity<PendingFriendship> getPendingFriendship(
            @RequestParam String requesterId, @RequestParam String responderId) {

        PendingFriendship pf =
                pendingFriendshipRepository.getPendingFriendshipBetweenTwo(requesterId,
                        responderId);

        return ResponseEntity.accepted().body(pf);
    }

    /**
     * Deletes the PendingFriendshipId between the Users.
     * @param requesterId id of the requester
     * @param responderId id of the responder
     * @return the deleted PendingFriendshipId
     */
    @GetMapping(value = "/deletependingfriendship")
    public ResponseEntity<PendingFriendship> deletePendingFriendship(
            @RequestParam String requesterId, @RequestParam String responderId) {
        PendingFriendship pf = getPendingFriendship(requesterId, responderId).getBody();

        pendingFriendshipRepository.deletePendingFriendship(requesterId, responderId);
        return ResponseEntity.accepted().body(pf);

    }
}


