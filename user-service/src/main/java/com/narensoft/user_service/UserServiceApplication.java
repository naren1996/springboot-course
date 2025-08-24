package com.narensoft.user_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UserServiceApplication {

	public static void main(String[] args) {

		User user = new User();

		user.getName();

		SpringApplication.run(UserServiceApplication.class, args);
	}

}
