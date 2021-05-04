package com.dailycodebuffer.userservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = {"spring.cloud.config.import-check.enabled=false"})
class UserServiceApplicationTests {

	@Test
	void contextLoads() {
	}

}
