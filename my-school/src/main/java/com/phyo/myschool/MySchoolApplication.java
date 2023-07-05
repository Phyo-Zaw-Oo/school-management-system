package com.phyo.myschool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.phyo.myschool.repository")
@EntityScan("com.phyo.myschool.model")
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
public class MySchoolApplication {

	public static void main(String[] args) {
		SpringApplication.run(MySchoolApplication.class, args);
	}

}
