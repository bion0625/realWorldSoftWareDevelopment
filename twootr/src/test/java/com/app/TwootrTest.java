package com.app;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.app.FollowStatus.ALREADY_FOLLOWING;
import static com.app.FollowStatus.SUCCESS;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class TwootrTest {

    Twootr twootr = new Twootr();

    SenderEndPoint endPoint = new SenderEndPoint();

    @Test
    public void shouldNotAuthenticationUserWithWrongPassword() {
        final Optional<SenderEndPoint> endPoint = twootr.onLogon(
                TestData.USER_ID, "bad password", receiverEndPoint);

        assertFalse(endPoint.isPresent());
    }

    private void logon() {
        endPoint = twootr.onLogon(
                TestData.USER_ID, "1234", receiverEndPoint)
                .orElseThrow();
    }

    private SenderEndPoint otherLogon() {
        return twootr.onLogon(
                        TestData.OTHER_USER_ID, "1234", receiverEndPoint)
                .orElseThrow();
    }

    @Test
    public void shouldFollowValidUser() {
        logon();

        final FollowStatus followStatus = endPoint.onFollow(TestData.OTHER_USER_ID);

        assertEquals(SUCCESS, followStatus);
    }

    @Test
    public void shouldNotDuplicateFollowValidUser() {
        logon();

        endPoint.onFollow(TestData.OTHER_USER_ID);

        final FollowStatus followStatus = endPoint.onFollow(TestData.OTHER_USER_ID);
        assertEquals(ALREADY_FOLLOWING, followStatus);
    }

    @Test
    public void shouldReceiveTwootsFromFollowedUser() {
        final String id = "1";

        logon();

        endPoint.onFollow(TestData.USER_ID);

        final SenderEndPoint senderEndPoint = otherLogon();
        senderEndPoint.onSendTwoot(id, TWOOT);

        verify(twootRepository).add(id, TestData.OTHER_USER_ID, TWOOT);
        verify(receiverEndPoint).onTwoot(new Twoot(id, TestData.OTHER_USER_ID, TWOOT, new Position(0)));
    }

    @Test
    public void shouldReceiveReplayOfTwootsAfterLogoff() {
        final String id = "1";

        userFollowsOtherUser();

        final SenderEndPoint otherEndPoint = otherLogon();
        otherEndPoint.onSendTwoot(id, TWOOT);

        logon();

        verify(receiverEndPoint).onTwoot(twootAt(id, POSITION));
    }
}
