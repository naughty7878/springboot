package com.test.springboot;

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
public class TestSpringbootApplicationTests {



	@Autowired
	ApplicationContext context;

	@Test
	public void contextLoads(){
		boolean b = context.containsBean("helloController");
		System.out.println(b);
	}


}
