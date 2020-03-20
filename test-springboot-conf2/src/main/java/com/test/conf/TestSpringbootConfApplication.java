package com.test.conf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@ImportResource(locations = {"classpath:beans.xml"})
@SpringBootApplication
public class TestSpringbootConfApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestSpringbootConfApplication.class, args);
	}

}
