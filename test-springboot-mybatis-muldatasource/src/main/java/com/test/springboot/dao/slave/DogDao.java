package com.test.springboot.dao.slave;

import com.test.springboot.bean.Dog;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface DogDao {

    List<Dog> getAll();

    @Select("INSERT INTO dog (dog_name) VALUES (#{dogName})")
    void save(Dog dog);
}
