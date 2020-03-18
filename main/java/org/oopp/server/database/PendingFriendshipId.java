package org.oopp.server.database;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
public class PendingFriendshipId implements Serializable {


    @NotNull
    private String requesterId;

    @NotNull
    private String responderId;

    public PendingFriendshipId(String requesterId, String responderId) {
        this.requesterId = requesterId;
        this.responderId = responderId;
    }

    /**
     * Empty constructor.
     */
    public PendingFriendshipId() {

    }

    public String getRequesterId() {
        return requesterId;
    }

    public void setRequesterId(String requesterId) {
        this.requesterId = requesterId;
    }

    public String getResponderId() {
        return responderId;
    }

    public void setResponderId(String responderId) {
        this.responderId = responderId;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        PendingFriendshipId that = (PendingFriendshipId) obj;
        return requesterId.equals(that.requesterId)
                && responderId.equals(that.responderId);
    }

}
