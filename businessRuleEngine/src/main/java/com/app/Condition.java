package com.app;

@FunctionalInterface
public interface Condition {
    boolean evaluate(Facts facts);
}
