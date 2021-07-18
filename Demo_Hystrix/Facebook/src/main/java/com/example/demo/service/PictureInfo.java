package com.example.demo.service;

import java.util.Arrays;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.pojo.Picture;
import com.example.demo.pojo.PictureList;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Service
public class PictureInfo {
	

	@HystrixCommand(
			commandProperties= {
					@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value="15000"),
					@HystrixProperty(name="circuitBreaker.requestVolumeThreshold",value="5"),
					@HystrixProperty(name="circuitBreaker.errorThresholdPercentage",value="50"),
					@HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds",value="5000")
			}
			,fallbackMethod = "getFallbackPictureList")
	
	public PictureList getPictureList(String email, RestTemplate restTemplate) {
		System.out.println("list :");
		PictureList list=restTemplate.getForObject("http://localhost:2122/getPicture/"+email, PictureList.class );
		
		
		System.out.println("list :" +list);
		return list;
		
	}
	
	public PictureList getFallbackPictureList(String email, RestTemplate restTemplate) {
		System.out.println("CallinggetFallbackPicture");
		PictureList list=new PictureList();
		list.setList(Arrays.asList(new Picture("N/A","N/A",null)));
		return list;
	}
	

}
