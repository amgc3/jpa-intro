package com.springbootframework.datapostgres;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class DataPostgresApplicationTests {

	@Autowired
	private ApplicationContext context;

	@Test
	void contextLoads() {
		assertNotNull(context);
		int count = context.getBeanDefinitionCount();
		System.out.println(context.getClass().getName());
		System.out.println("There are " + count + " beans in the application context");
		Arrays.stream(context.getBeanDefinitionNames())
				.forEach(System.out::println);
	}

}
