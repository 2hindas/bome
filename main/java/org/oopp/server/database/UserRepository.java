package org.oopp.server.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;

public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Selects all the users with appropriate id.
     *
     * @param id of the user.
     * @return The user.
     */
    @Query("SELECT a FROM User a WHERE user_id = :id")
    User findUserById(@Param("id") String id);

    /**
     *  Selects all the users with appropriate name(there is a possibility of more than one user).
     * @param name name of the user.
     * @return Array List of users.
     */
    @Query("SELECT a FROM User a WHERE a.fullName LIKE :name%")
    ArrayList<User> findUserByName(@Param("name") String name);

    /**
     * Selects all users with an appropriate username.
     * @param username username of the user.
     * @return Array List of users.
     */
    @Query("SELECT a FROM User a WHERE userName LIKE :username%")
    ArrayList<User> findUserByUsername(@Param("username") String username);

    /**
     * Selects all the users that have an appropriate name of username.
     * @param user name or username of the user.
     * @return Array List of users.
     */
    @Query("SELECT a FROM User a WHERE lower(userName) LIKE lower(concat(:user, '%')) "
            + "OR lower(fullName) LIKE lower(concat(:user, '%'))")
    ArrayList<User> findUserByBoth(@Param("user") String user);
}