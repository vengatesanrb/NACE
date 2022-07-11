package com.example.nace.Job;

import org.apache.catalina.core.ApplicationContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class JobApplicationTests {
	@Autowired
	private ApplicationContext context;

	@Autowired
	private JobApplication jobApplication;

	@Test
	void contextLoads() {
		assertNotNull(context);
	}

}
