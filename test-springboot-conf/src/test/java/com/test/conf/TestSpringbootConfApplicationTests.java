package com.test.conf;

import com.test.conf.bean.Dog;
import com.test.conf.bean.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * SpringBoot单元测试
 *
 * 可以在测试期间很方便的类似编码一样的自动注入
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestSpringbootConfApplicationTests {

	@Autowired
	Person person;

	@Autowired
	Dog dog;

	@Autowired
	ApplicationContext context;

	@Test
	public void checkServiceExist(){
		boolean b = context.containsBean("helloService02");
		System.out.println(b);
	}

	@Test
	public void contextLoads() {
		System.out.println(person);
		System.out.println(dog);
	}

}
