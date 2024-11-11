package com.app;

@FunctionalInterface
public interface Action {
    void execute(Facts facts);
}
