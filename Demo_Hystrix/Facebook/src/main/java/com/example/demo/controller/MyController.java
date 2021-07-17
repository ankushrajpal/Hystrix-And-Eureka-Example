package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.demo.pojo.PictureList;
import com.example.demo.pojo.Profile;
import com.example.demo.pojo.userDetails;
import com.example.demo.service.PictureInfo;
import com.example.demo.service.ProfileInfo;

@RestController

public class MyController {
	
	@Autowired
	PictureInfo pictureInfo;
	
	@Autowired
	ProfileInfo profileInfo;
	
	@GetMapping("/user/{email}")
	public userDetails getUser(@PathVariable String email)
	{
		RestTemplate restTemplate=new RestTemplate();
		PictureList picList=pictureInfo.getPictureList(email, restTemplate);
		Profile profile=profileInfo.getProfile(email, restTemplate);
		
		return new userDetails(profile, picList.getList());
		
	}

	


}
