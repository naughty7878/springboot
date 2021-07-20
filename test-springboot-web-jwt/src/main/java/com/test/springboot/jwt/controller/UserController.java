package com.test.springboot.jwt.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.test.springboot.jwt.annotation.PassToken;
import com.test.springboot.jwt.annotation.UserLoginToken;
import com.test.springboot.jwt.entity.User;
import com.test.springboot.jwt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    // 登录
    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody User user) {
        Map<String, Object> result = new HashMap<>();

        User user1 = userService.findOne(user.getUsername(), user.getPassword());
        if (user1 != null) {
            String token = getToken(user1);
            result.put("token", token);
            result.put("user", user1);
            return result;
        } else {
            result.put("message", "登录失败");
            return result;
        }
    }

    @UserLoginToken
    @GetMapping("/getMessage")
    public Map<String, Object> getMessage() {
        Map<String, Object> result = new HashMap<>();
        result.put("content", "你已通过验证");
        return result;
    }

    @PassToken
    @GetMapping("/getPublicMessage")
    public Map<String, Object> getPublicMessage() {
        Map<String, Object> result = new HashMap<>();
        result.put("content", "公共信息");
        return result;
    }


    /**
     * token的生成方法
     */
    public String getToken(User user) {
        String token="";
                // 创建JWT
        token= JWT.create()
                // 设置听众信息，也可以这是其他信息，如：withSubject主题
                .withAudience(user.getId())
                // 签名
                .sign(Algorithm.HMAC256(user.getPassword()));
        return token;
    }
}