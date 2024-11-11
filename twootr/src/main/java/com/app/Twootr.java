package com.app;

import java.util.Arrays;
import java.util.Optional;

public class Twootr {

    private final UserRepository userRepository;

    private final TwootRepository twootRepository;

    Optional<SenderEndPoint> onLogon(String userId, String password, ReceiverEndPoint receiverEndPoint) {

        var authenticatedUser = userRepository.get(userId)
                .filter(userOfSameId -> {
                    var hashedPassword = KeyGenerator.hash(password, userOfSameId.getSalt());
                    return Arrays.equals(hashedPassword, userOfSameId.getPassword());
                });

        authenticatedUser.ifPresent(user -> {
            user.onLogon(receiverEndPoint);
            twootRepository.query(
                    new TwootQuery().inUsers(user.getFollowing())
                            .lastSeenPosition(user.getLastSeenPosition()),
                    user::receiveTwoot);
            userRepository.update(user);
        });

        return authenticatedUser.map(user -> new SenderEndPoint(user, this));
    }
}
