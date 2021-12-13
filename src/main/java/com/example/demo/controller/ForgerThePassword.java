package com.example.demo.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.ResultViewModel;
import com.example.demo.model.DB.Account;
import com.example.demo.service.MemberService;

@RestController
@RequestMapping(value = "/ForgerThePassword")
public class ForgerThePassword {

	@Autowired
	private MemberService _memberService;

	public ForgerThePassword(MemberService memberService) {
	
		_memberService = memberService;
	
	}

	@PostMapping(value = "/sendEmail")
	public ResponseEntity<ResultViewModel> sendEmail(@RequestBody Account account, HttpServletResponse response) {
		
		ResultViewModel rvm = new ResultViewModel();
		account.getEmail();
		_memberService.ForgetThePassword(account);
		String hashCookie = account.getEmail();
		Cookie cookie = new Cookie("userName", hashCookie);
		cookie.setMaxAge(1800 + (60 * 60 * 8));
		cookie.setPath("/");
		response.addCookie(cookie);
		rvm.setCode(200);
		rvm.setMessage("驗證成功");
		rvm.setData("/signup.html");
		return new ResponseEntity<ResultViewModel>(rvm, HttpStatus.OK);
	}

	@PostMapping(value = "/check")
	public ResponseEntity<ResultViewModel> check(@RequestBody Account account) {
		ResultViewModel rvm = new ResultViewModel();
		_memberService.CheckMail(account);

		if (account.getMail() == null) {
			rvm.setCode(400);
			rvm.setMessage("驗證碼錯誤");
			rvm.setData("/forgot_password.html");
			return new ResponseEntity<ResultViewModel>(rvm, HttpStatus.OK);
		}
		rvm.setCode(200);
		rvm.setMessage("帳號");
		
		return new ResponseEntity<ResultViewModel>(rvm, HttpStatus.OK);
	}

	@PostMapping(value = "/checkpassword")
	public ResponseEntity<ResultViewModel> checkpassword(@RequestBody Account account) {
		
		ResultViewModel rvm = new ResultViewModel();
		_memberService.ChangePassword(account);
		rvm.setCode(200);
		rvm.setMessage("帳號");
		rvm.setData("/index.html");
		return new ResponseEntity<ResultViewModel>(rvm, HttpStatus.OK);
	}
	
	}
