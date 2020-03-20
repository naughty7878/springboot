package com.test.springboot.dubbo.consumer;

import com.test.springboot.dubbo.provider.service.DemoService;
import org.apache.dubbo.config.annotation.Reference;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestApplication {

    @Reference(version = "1.0.0", url = "dubbo://127.0.0.1:12345")
    private DemoService demoService;

    @Test
    public void sayHello(){
        System.out.println(demoService.sayHello("XX"));
    }

}
