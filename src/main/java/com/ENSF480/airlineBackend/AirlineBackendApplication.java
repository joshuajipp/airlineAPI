package com.ENSF480.airlineBackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AirlineBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(AirlineBackendApplication.class, args);
	}

}
