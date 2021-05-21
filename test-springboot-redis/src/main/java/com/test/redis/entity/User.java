package com.test.redis.entity;

public class User {
    private int userId;
    private String name;
    public User(int id, String name) {
        this.userId = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}