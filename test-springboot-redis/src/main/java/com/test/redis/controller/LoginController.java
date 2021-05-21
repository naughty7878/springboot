package com.test.redis.controller;

import com.test.redis.entity.ReturnData;
import com.test.redis.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping(value = "/api/user")
public class LoginController
{

    @Autowired
    private StringRedisTemplate redisTemplate;

    @RequestMapping("/login")
    @ResponseBody
    public ReturnData login(HttpServletRequest request, String account, String password)
    {
        User user = new User(1, "xiaobai");
        if (user != null)
        {
            HttpSession session = request.getSession();
            session.setAttribute("loginUserId", user.getUserId());
            redisTemplate.opsForValue().set("loginUser:" + user.getUserId(), session.getId());

            return new ReturnData("登录成功！");
        }
        else
        {
            throw new RuntimeException("账户名或密码错误！");
        }
    }

    @RequestMapping(value = "/getUserInfo")
    public ReturnData get(long userId)
    {
        User user = new User(1, "xiaohai");
        if (user != null)
        {
            return new ReturnData( "查询成功！");
        }
        else
        {
            throw new RuntimeException("用户不存在！");
        }
    }


}