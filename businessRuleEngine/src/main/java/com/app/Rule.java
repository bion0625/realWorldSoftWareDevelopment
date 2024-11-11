package com.app;

@FunctionalInterface
public interface Rule {
    void Perform(Facts facts);
}
