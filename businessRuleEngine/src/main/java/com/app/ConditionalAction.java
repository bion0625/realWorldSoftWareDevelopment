package com.app;

public interface ConditionalAction {
    boolean evaluate(Facts facts);
    void perform(Facts facts);
}
