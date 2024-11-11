package com.app;

public class Twoot {
    private String id;
    private String userId;
    private String content;

    public String getSenderId() {
        return this.id;
    }
    public String getContent() {
        return this.content;
    }

    public Twoot(String id, String userId, String content) {
        this.id = id;
        this.userId = userId;
        this.content = content;
    }
}
