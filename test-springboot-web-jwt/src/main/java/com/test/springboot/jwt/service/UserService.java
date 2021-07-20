package com.test.springboot.jwt.service;

import com.test.springboot.jwt.entity.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class UserService {

    private static Map<String, User> userMap = new ConcurrentHashMap<>();

    static {
        userMap.put("1001", new User("1001", "小白", "123456"));
        userMap.put("1002", new User("1002", "小黑", "123456"));
        userMap.put("1003", new User("1003", "小黄", "123456"));
    }

    public User findOne(String username, String password) {
        for (Map.Entry<String, User> entry : userMap.entrySet()) {
            User value = entry.getValue();
            if (value.getUsername().equals(username)
                    && value.getPassword().equals(password)) {
                return value;
            }
        }
        return null;
    }

    public User findUserById(String userId) {
        return userMap.get(userId);
    }
}
