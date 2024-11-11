package com.app;

import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertFalse;

public class TwootrTest {

    Twootr twootr = new Twootr();

    ReceiverEndPoint receiverEndPoint = new ReceiverEndPoint();

    @Test
    public void shouldNotAuthenticationUserWithWrongPassword() {
        final Optional<SenderEndPoint> endPoint = twootr.onLogon(
                TestData.USER_ID, "bad password", receiverEndPoint);

        assertFalse(endPoint.isPresent());
    }
}
