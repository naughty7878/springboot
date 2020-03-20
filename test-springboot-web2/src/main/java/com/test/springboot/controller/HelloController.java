package com.test.springboot.controller;

import com.test.springboot.exception.UserNotExistException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {

    @RequestMapping("/success")
    public String success(){
        return "success";
    }

    @RequestMapping("/hello")
    public String hello(@RequestParam("user") String user){

        if("aaa".equals(user))
        throw new UserNotExistException();
        return "hello";
    }

}
