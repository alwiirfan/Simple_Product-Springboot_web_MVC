package com.alwi.irfani;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class SpringWebApplication {

	public static void main(String[] args) {

		// initialize dotenv
		Dotenv.configure().load();

		SpringApplication.run(SpringWebApplication.class, args);
	}

}
