package com.app;

import java.util.Set;

public class TwootQuery {
    private Set<String> inUsers;
    private Position lastSeenPosition;

    public Set<String> getInUsers() {
        return this.inUsers;
    }

    public Position getLastSeenPosition() {
        return this.lastSeenPosition;
    }

    public TwootQuery inUsers(final Set<String> inUsers) {
        this.inUsers = inUsers;
        return this;
    }

    public TwootQuery lastSeenPosition(final Position lastSeenPosition) {
        this.lastSeenPosition = lastSeenPosition;
        return this;
    }

    public boolean hasUsers() {
        return inUsers != null && !inUsers.isEmpty();
    }
}
