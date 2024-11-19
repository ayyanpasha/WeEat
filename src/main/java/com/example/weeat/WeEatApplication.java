package com.example.weeat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class WeEatApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeEatApplication.class, args);
	}

}
