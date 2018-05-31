package com.sportuenteller.olympic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("config.properties")
public class OlympicApplication {
	public static void main(String[] args) {
		SpringApplication.run(OlympicApplication.class, args);
	}
}
