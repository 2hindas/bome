package org.oopp.server.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class UserBadgesController {

    @Autowired
    UserBadgesRepository userBadgesRepository;

    @Autowired
    UserRepository userRepository;


    /**
     * Check if a user already has a certain badge
     * If not, give the user the badge and saves it to the database.
     *
     * @param userId  User id.
     * @param badgeId badge id.
     */
    @GetMapping(value = "/newbadge")
    public ResponseEntity<UserBadges> newbadge(@RequestParam String userId,
                                               @RequestParam int badgeId) {

        if (checkUserBadges(userId, badgeId) == false) {
            System.out.println("Achieved! (new badge)");
            UserBadges userbadge1 = new UserBadges(userId, badgeId);
            userBadgesRepository.save(userbadge1);
            return ResponseEntity.accepted().body(userbadge1);
        } else {
            UserBadges userBadges = userBadgesRepository.hasBadges(userId, badgeId);
            return ResponseEntity.accepted().body(userBadges);


        }
    }

    /**
     * Checks if a user has a certain badge.
     *
     * @param userId  for user ID.
     * @param badgeId for badge ID.
     * @return True if the user has the badge, false otherwise.
     */
    @GetMapping(value = "/checkUserBadges")
    public boolean checkUserBadges(@RequestParam String userId, @RequestParam int badgeId) {
        UserBadges userBadges = userBadgesRepository.hasBadges(userId, badgeId);
        if (userBadges == null) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Returns all badges of a certain user from the database.
     *
     * @param userId The Id of the User.
     * @return A ResponseEntity with an Array List of badges of the User.
     */
    @GetMapping("/getuserbadges")
    public ResponseEntity<ArrayList<UserBadges>> getuserbadges(@RequestParam String userId) {
        System.out.println("All your Badges!! (get User Badges)");
        return ResponseEntity.accepted().body(userBadgesRepository.findUserBadges(userId));

    }

}