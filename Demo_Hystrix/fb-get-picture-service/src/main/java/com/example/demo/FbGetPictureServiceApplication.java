package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import com.example.demo.pojo.PictureList;

@SpringBootApplication
@EnableCircuitBreaker
@EnableDiscoveryClient
public class FbGetPictureServiceApplication {

	@Bean
	PictureList getPictureList() {
		return new PictureList();

	}

	public static void main(String[] args) {
		SpringApplication.run(FbGetPictureServiceApplication.class, args);
	}

}
