package com.example.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.pojo.Profile;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Service
public class ProfileInfo {

	@HystrixCommand(commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000"),
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
			@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "3000")

	}, fallbackMethod = "getFallbackProfile")

	public Profile getProfile(String email, RestTemplate restTemplate) {

		
		return restTemplate.getForObject("http://localhost:2123/getProfile/" + email, Profile.class);
	}

	public Profile getFallbackProfile(String email, RestTemplate restTemplate) {
		return new Profile("", "", null);
	}

}
