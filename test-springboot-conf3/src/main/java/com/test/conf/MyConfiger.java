package com.test.conf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

@Configuration
public class MyConfiger {

//    @Value("${xxxx.name}")
    private String userName;

    @Bean
    public Date date() {
        Date date = new Date();
        System.out.println(date);
        System.out.println("userName = " + userName);
        return date;
    }
}
