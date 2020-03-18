package org.oopp.server.database;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Friendships")
public class Friendship {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "friendship_Id")
    private int friendshipId;

    @Column(name = "user1_id")
    private String user1Id;

    @Column(name = "user2_id")
    private String user2Id;

    public Friendship() {
    }

    public Friendship(String user1Id, String user2Id) {
        this.user1Id = user1Id;
        this.user2Id = user2Id;
    }

    public String getUser1Id() {
        return user1Id;
    }

    public void setUser1Id(String user1Id) {
        this.user1Id = user1Id;
    }

    public String getUser2Id() {
        return user2Id;
    }

    public void setUser2Id(String user2Id) {
        this.user2Id = user2Id;
    }

    public int getFriendshipId() {
        return friendshipId;
    }

    public void setFriendshipId(int friendshipId) {
        this.friendshipId = friendshipId;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Friendship that = (Friendship) obj;
        return friendshipId == that.friendshipId
                && user1Id.equals(that.user1Id)
                && user2Id.equals(that.user2Id);
    }

}

