package com.test.springboot.cloud.consumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConsumerController {

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping("/consumer")
    public String consumer(String name) {

        String object = restTemplate.getForObject("http://PROVIDER/provider?name=" + name, String.class);

        return "consumer---> " + object;
    }
}
