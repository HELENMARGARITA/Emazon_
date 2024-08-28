package com.emazon.stock_microservice.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.emazon.stock_microservice")
public class StockMicroserviceApplication {

	public static void main(String[] args) {
		// Ejecuta la aplicación Spring Boot
		SpringApplication.run(StockMicroserviceApplication.class, args);
	}
}

