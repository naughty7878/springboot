package com.test.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

import java.util.Locale;


/**
 * @SpringBootApplication 用来标注一个主程序，说明这是一个Spring Boot应用
 */

@SpringBootApplication
@ServletComponentScan
public class Application {

    public static void main(String[] args) {
        // Spring应用启动
        SpringApplication.run(Application.class, args);
    }
}
