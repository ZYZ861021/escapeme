package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.model.DB.Account;
import com.example.demo.service.MemberService;


@RestController
@RequestMapping(value = "/Personalpage")
public class PersonalPageController {
	
	@Autowired
	private MemberService _memberService;
	
	@GetMapping("/mypage")
	public Account mypage(Account account,@CookieValue (value = "userName", defaultValue = "Atta")String userName) {
		
	Account account1 = 	_memberService.findByEmail(userName);
		return account1;
	}
}
