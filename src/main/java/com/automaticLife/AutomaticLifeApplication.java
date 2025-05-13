package com.automaticLife;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class AutomaticLifeApplication {

	public static void main(String[] args) {
		SpringApplication.run(AutomaticLifeApplication.class, args);
	}
}
