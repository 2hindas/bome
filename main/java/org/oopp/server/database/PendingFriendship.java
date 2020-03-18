package org.oopp.server.database;

import java.util.Objects;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "pending_friendship")
public class PendingFriendship {

    @EmbeddedId
    private PendingFriendshipId pendingFriendshipId;

    public PendingFriendship() {

    }

    public PendingFriendship(PendingFriendshipId pendingFriendshipId) {
        this.pendingFriendshipId = pendingFriendshipId;
    }

    public PendingFriendshipId getPendingFriendshipId() {
        return pendingFriendshipId;
    }

    public void setPendingFriendshipId(PendingFriendshipId pendingFriendshipId) {
        this.pendingFriendshipId = pendingFriendshipId;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        PendingFriendship that = (PendingFriendship) obj;
        return Objects.equals(pendingFriendshipId, that.pendingFriendshipId);
    }
    
}
