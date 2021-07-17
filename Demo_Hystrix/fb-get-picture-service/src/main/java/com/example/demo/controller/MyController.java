package com.example.demo.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.demo.pojo.Picture;
import com.example.demo.pojo.PictureList;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class MyController {

	@Autowired
	PictureList pictureList;

	@HystrixCommand(commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "12000"),
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
			@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "3000") }, fallbackMethod = "getFallbackPicture")

	@GetMapping("/getPicture/{email}")
	public PictureList getPicture(@PathVariable String email) throws InterruptedException {

		RestTemplate restTemplate = new RestTemplate();

		Picture pic1 = new Picture("PIC", "C://users",
				restTemplate.getForObject("http://localhost:2121/getLikes/" + email, Integer.class));
		Picture pic2 = new Picture("PIC", "C://users",
				restTemplate.getForObject("http://localhost:2121/getLikes/" + email, Integer.class));

		List<Picture> list = Arrays.asList(pic1, pic2);

		log.info("getPicture called");
		pictureList.setList(list);
		Thread.sleep(8000);
		return pictureList;
	}

	public PictureList getFallbackPicture(@PathVariable String email) {
		
		log.info("getFallbackPicture called");
		Picture pic1 = new Picture("PIC", "C://users", null);
		Picture pic2 = new Picture("PIC", "C://users", null);

		List<Picture> list = Arrays.asList(pic1, pic2);

		pictureList.setList(list);

		return pictureList;
	}

}
