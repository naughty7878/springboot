package com.test.springboot.service;

import com.test.springboot.bean.Dog;
import com.test.springboot.dao.slave.DogDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DogService {

    @Autowired
    DogDao dogDao;

    /**
     * 在springboot中已经默认对jpa、jdbc、mybatis开启了事事务，引入它们依赖的时候，事物就默认开启。
     * springboot开启事务很简单，只需要一个注解@Transactional 就可以了。
     * @Transactional可以在在方法上和类上使用。
     * @return
     */
    @Transactional(value = "slaveTransactionManager")
    public Integer save() {

        Dog dog = new Dog();
        dog.setDogName("大黄");
        dogDao.save(dog);

        return 1/0;
    }
}
