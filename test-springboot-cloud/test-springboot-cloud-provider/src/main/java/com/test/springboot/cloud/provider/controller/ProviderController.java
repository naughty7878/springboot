package com.test.springboot.cloud.provider.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProviderController {

    @Value("${spring.application.name}")
    String applicationName;

    @Value("${server.port}")
    String port;

    @RequestMapping("/provider")
    public String provider(String name){
        return "hi " + name + "\nI'm " + applicationName + port;
    }

}
