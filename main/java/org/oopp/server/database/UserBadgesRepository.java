package org.oopp.server.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;


public interface UserBadgesRepository extends JpaRepository<UserBadges, Long> {



    /**
     * Check if a certain user has a certain badge.
     * @param userId  for user id.
     * @param badgeId for badge id
     */
    @Query("SELECT a FROM UserBadges a WHERE badgeId = :badge_id AND userId = :user_id")
    UserBadges hasBadges(@Param("user_id") String userId, @Param("badge_id") int badgeId);

    /**
     * Retrieves all the badges of a specific user.
     * @param userId id of the User.
     * @return Array List of badges of the User.
     */
    @Query ("SELECT a FROM UserBadges a WHERE userId = :user_id")
    ArrayList<UserBadges> findUserBadges(@Param("user_id") String userId);


}