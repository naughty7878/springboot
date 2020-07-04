package com.test;

import com.test.entities.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestApplication {

    @Autowired
    RestTemplate restTemplate;

    // 获取字符串
    @Test
    public void test01() {
        String url = "http://localhost:8080/employees";
                String res1 = restTemplate.getForObject(url, String.class);
        System.out.println("getForObject======" + res1);
    }

    // 使用RestTemplate的自定义HTTP标头
    @Test
    public void test02(){
        String url = "http://localhost:8080/employees";
                HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

        ResponseEntity<String> res2 = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        System.out.println("exchange======" + res2);
    }

    // 获取响应作为对象
    @Test
    public void test03(){
        String url = "http://localhost:8080/employee/1";

        Employee res3 = restTemplate.getForObject(url, Employee.class);
        System.out.println("getForObject======" + res3);
    }

    // 获取响应作为对象，并获取响应对象状态
    @Test
    public void test033(){
        String url = "http://localhost:8080/employee/1";
        ResponseEntity<Employee> res3 = restTemplate.getForEntity(url, Employee.class);
        System.out.println("getStatusCode======" + res3.getStatusCode());
        System.out.println("getForEntity======" + res3);
    }

    // URL参数
    @Test
    public void test04(){
        String url = "http://localhost:8080/employee/{id}";

        Map<String, String> params = new HashMap<String, String>();
        params.put("id", "1");
        Employee res4 = restTemplate.getForObject(url, Employee.class, params);
        System.out.println("getForObject======" + res4);
    }

    // HTTP POST方法
    @Test
    public void test05(){
        String url = "http://localhost:8080/employee";

        Employee newEmployee = new Employee(-1, "小黑", "1", "test@email.com");
        String res5 = restTemplate.postForObject(url, newEmployee, String.class);
        System.out.println("postForObject======" + res5);
    }

    // HTTP PUT方法示例
    @Test
    public void test06(){
        String url = "http://localhost:8080/employee";
        Employee newEmployee = new Employee(-1, "小黑", "1", "test@email.com");
        restTemplate.put(url, newEmployee);
    }

    // HTTP DELETE方法示例
    @Test
    public void test07(){
        String url = "http://localhost:8080/employee/1";
        restTemplate.delete(url);
    }
}
