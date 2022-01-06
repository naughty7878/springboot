package com.test;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author H__D
 * @description TODO
 * @date 2021/10/30
 */
@EnableBatchProcessing
@SpringBootApplication
public class Application {

    public static void main(String[] args) {

        SpringApplication.run(Application.class);
    }
}
