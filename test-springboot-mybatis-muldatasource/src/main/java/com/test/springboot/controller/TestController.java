package com.test.springboot.controller;

import com.test.springboot.service.DogService;
import com.test.springboot.bean.Dog;
import com.test.springboot.bean.User;
import com.test.springboot.dao.slave.DogDao;
import com.test.springboot.dao.master.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {

    @Autowired
    UserDao userDao;

    @Autowired
    DogDao dogDao;

    @Autowired
    DogService dogService;

    @RequestMapping("/users")
    public List<User> users(){
        return userDao.getAll();
    }


    @RequestMapping("/dogs")
    public List<Dog> dogs(){
        return dogDao.getAll();
    }

    @RequestMapping("/dog/save")
    public Integer save(){
        return dogService.save();
    }
}
