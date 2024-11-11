package com.app;

import java.util.Objects;

public class SenderEndPoint {
    private final User user;
    private final Twootr twootr;

    SenderEndPoint(final User user, final Twootr twootr) {
        Objects.requireNonNull(user, "user");
        Objects.requireNonNull(twootr, "twootr");

        this.user = user;
        this.twootr = twootr;
    }

    FollowStatus onFollow(final String userIdToFollow) {
        Objects.requireNonNull(userIdToFollow, "userIdToFollow");

        return twootr.onFollow(user, userIdToFollow);
    }

    void onSendTwoot(final String id, final User user, final String content) {
        final String userId = user.getId();
        final Twoot twoot = new Twoot(id, userId, content);
        user.followers()
                .filter(User::isLoggedOn)
                .forEach(follower -> follower.receiveTwoot(twoot));
    }
}
