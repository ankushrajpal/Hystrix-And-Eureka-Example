package com.example.demo.controller;

import java.util.Date;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.pojo.Profile;

@RestController
public class MyController {
	

			 
	@GetMapping("/getProfile/{email}")
	public Profile getProfile(@PathVariable String email)
	{
		Profile profile=new Profile("Ankush","male",new Date());
		return profile;
	}
	
}
