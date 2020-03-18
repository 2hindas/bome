package org.oopp.server.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

public interface FriendshipRepository extends JpaRepository<Friendship, Long> {

    /**
     * Retrieves a list of friends of a certain User from the database.
     *
     * @param id id of the User.
     * @return Array list of users that are friends of the given User.
     */
    @Query("SELECT f.user2Id FROM Friendship f WHERE user1Id = :id ")
    ArrayList<String> listOfFriends(@Param("id") String id);

    /**
     * Retrieves a friendship between two Users from the database.
     *
     * @param id1 id of the first User.
     * @param id2 id of the second User.
     * @return A friendship object that contains ids of the two Users.
     */
    @Query("SELECT f FROM Friendship f WHERE user1Id = :id1 AND user2Id = :id2")
    Friendship existsFriendship(@Param("id1") String id1, @Param("id2") String id2);


    /**
     * Deletes a row from the database that contains the given two users.
     *
     * @param id1 id of the first User.
     * @param id2 id of the second User.
     */
    @Transactional
    @Modifying
    @Query("DELETE FROM Friendship f WHERE user1Id = :id1 AND user2Id = :id2")
    void deleteFriendship(@Param("id1") String id1, @Param("id2") String id2);

}
