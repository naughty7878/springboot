package com.test.springboot.cache;

import com.test.springboot.cache.bean.Employee;
import com.test.springboot.cache.mapper.EmployeeMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sound.midi.Soundbank;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestApplication {

    @Autowired
    EmployeeMapper employeeMapper;

    // 操作k-v都是字符串的
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    // 操作k-v都是对象
    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    RedisTemplate jsonRedisTemplate;


    @Test
    public void test01(){
        // 给redis中保存数据
        stringRedisTemplate.opsForValue().append("msg", "hello world");
        System.out.println(stringRedisTemplate.opsForValue().get("msg"));
    }

    // 测试保存对象
    @Test
    public void test02(){

        Employee emp = employeeMapper.getEmpById(1);
        // 给redis中保存对象
        // 默认如果保存对象，使用jdk序列号机制，序列化的数据保存在redis中
        redisTemplate.opsForValue().set("emp-01", emp);
        System.out.println(redisTemplate.opsForValue().get("emp-01"));
    }


    @Test
    public void context(){
        System.out.println("===");
        Employee emp = employeeMapper.getEmpById(1);
        System.out.println(emp);
    }
}
