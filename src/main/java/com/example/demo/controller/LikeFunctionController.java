package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.DB.LikeFunction;
import com.example.demo.service.LikeFunctionService;

@RestController
@RequestMapping("/Like")
public class LikeFunctionController {
	
	@Autowired
	private LikeFunctionService _likeFunctionService;
	
	@PostMapping("/IsLike")
	public LikeFunction IsLike(@RequestBody LikeFunction likeFunction,@CookieValue(value = "userName", defaultValue = "Atta") String userName) {
		
		System.out.println(likeFunction);
		return _likeFunctionService.SaveLike(likeFunction, userName);
	}
}
