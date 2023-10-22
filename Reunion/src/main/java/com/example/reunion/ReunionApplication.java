package com.example.reunion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ReunionApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReunionApplication.class, args);
	}

}
