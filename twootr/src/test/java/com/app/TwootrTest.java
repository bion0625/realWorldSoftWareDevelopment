package com.app;

import org.junit.Test;

import java.util.Optional;

import static com.app.FollowStatus.ALREADY_FOLLOWING;
import static com.app.FollowStatus.SUCCESS;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class TwootrTest {

    Twootr twootr = new Twootr();

    ReceiverEndPoint receiverEndPoint = new ReceiverEndPoint();

    SenderEndPoint endPoint = new SenderEndPoint();

    @Test
    public void shouldNotAuthenticationUserWithWrongPassword() {
        final Optional<SenderEndPoint> endPoint = twootr.onLogon(
                TestData.USER_ID, "bad password", receiverEndPoint);

        assertFalse(endPoint.isPresent());
    }

    private void logon() {
        endPoint = twootr.onLogon(
                TestData.USER_ID, "bad password", receiverEndPoint)
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
}
