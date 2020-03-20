package com.test.springboot.cache;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * 一、搭建基本环境
 * 1、创建数据库，创建department和employee表
 * 2、创建JavaBean封装数据
 * 3、整合MyBatis操作数据库
 *      a、配置数据源信息
 *      b、使用注解版的MyBatis：
 *          1）、@MapperScan指定需要扫描的mapper接口所在的包
 * 二、快速体验缓存
 *  步骤：
 *      1、开启基于注解的缓存 @EnableCaching
 *      2、标注缓存注解即可 @Cacheable、@CacheEvict、@CachePut
 */
@EnableCaching
@MapperScan("com.test.springboot.cache.mapper")
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
