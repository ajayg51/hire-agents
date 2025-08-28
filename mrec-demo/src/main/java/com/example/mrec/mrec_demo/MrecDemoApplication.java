package com.example.mrec.mrec_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication()
@EnableJpaAuditing(auditorAwareRef = "AuditAwareImpl")
public class MrecDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MrecDemoApplication.class, args);
	}

}
