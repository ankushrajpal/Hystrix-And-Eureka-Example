package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MyController {
	
	@GetMapping("/getLikes/{picName}")
	
			
	public int getLikes() throws InterruptedException
	{
		//Thread.sleep(3000);
		return (int)(Math.random()*100);
		
	}
	
	

}
