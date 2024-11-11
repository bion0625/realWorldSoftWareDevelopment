package com.app;

import java.util.List;
import java.util.stream.Stream;

public class User {
    private String id;
    private String password;
    private List<User> followers;
    private boolean isLoggedOn = false;

    public User() {}
    public User(String id, String password) {
        this.id = id;
        this.password = password;
    }

    public String getId() {
        return this.id;
    }

    public String getPassword() {
        return this.password;
    }

    public boolean isLoggedOn() {
        return this.isLoggedOn;
    }

    public Stream<User> followers() {
        return this.followers.stream();
    }

    public void receiveTwoot(Twoot twoot) {}
}
