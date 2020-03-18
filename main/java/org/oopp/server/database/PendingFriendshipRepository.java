package org.oopp.server.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface PendingFriendshipRepository extends JpaRepository<PendingFriendship, String> {

    /**
     * Returns the id of the pending friends of the given requester.
     * @Param userId id of the user
     * @Return String[] of all the id's
     */
    @Query("SELECT f.pendingFriendshipId.responderId FROM PendingFriendship f"
            + " WHERE f.pendingFriendshipId.requesterId = :uid")
    String[] getPendingFriendsId(@Param("uid") String userid);

    /**
     * Returns the id of the Users that want to be friends with the given user.
     * @Param userId id of the user
     * @Return String[] of all the id's
     */
    @Query("SELECT f.pendingFriendshipId.requesterId FROM PendingFriendship f"
            + " WHERE f.pendingFriendshipId.responderId = :uid")
    String[] getRequestedFriendsId(@Param("uid") String userid);

    /**
     * Returns the PendingFriendshipId between responder and requester.
     * @param responderid id of the responder
     * @param requesterid id of the requester
     * @return PendingFriendshipId between the two users
     */
    @Query("SELECT a FROM PendingFriendship a "
            + "WHERE a.pendingFriendshipId.responderId = :resid "
            + "AND a.pendingFriendshipId.requesterId = :reqid")
    PendingFriendship getPendingFriendshipBetweenTwo(@Param("reqid") String requesterid,
                                                       @Param("resid") String responderid);


    /**
     * Deletes a row from the friendship requests table from the database.
     *
     * @param requesterId id of the requester.
     * @param responderId id of the responder.
     */
    @Transactional
    @Modifying
    @Query("DELETE FROM PendingFriendship f "
            + "WHERE f.pendingFriendshipId.requesterId = :reqid "
            + "AND f.pendingFriendshipId.responderId = :resid")
    void deletePendingFriendship(@Param("reqid") String requesterId,
                                 @Param("resid") String responderId);
}
