package com.automaticLife;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@EnableScheduling
@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "Automatic Life API",
                version = "1.0.0",
                description = "This API help you in your day!",
                contact = @Contact(
                        name = "Automatic Life API Support",
                        email = "rogeriolalonso@hotmail.com"
                )
        ),
        servers = {
                @Server(url = "http://localhost:8080", description = "Development server")
        }
)
public class AutomaticLifeApplication {

	public static void main(String[] args) {
		SpringApplication.run(AutomaticLifeApplication.class, args);
	}
}
