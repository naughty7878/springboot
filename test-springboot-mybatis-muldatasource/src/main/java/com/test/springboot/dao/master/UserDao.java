package com.test.springboot.dao.master;

import com.test.springboot.bean.User;

import java.util.List;

public interface UserDao {
    List<User> getAll();
}
