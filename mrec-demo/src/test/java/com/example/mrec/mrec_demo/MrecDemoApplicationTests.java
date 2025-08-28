package com.example.mrec.mrec_demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = "APP_NAME=Mrec Demo")
class MrecDemoApplicationTests {

	@Test
	void contextLoads() {
	}

}
