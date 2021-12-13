package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Index;
import com.example.demo.model.PostNumber;
import com.example.demo.service.MemberService;
import com.example.demo.service.MessageService;
import com.example.demo.service.PostingSystemService;
import com.example.demo.service.indexService;


@RestController
@RequestMapping(value = "/index")
public class IndexController {
	@Autowired
	private MemberService _memberService;
	
	@Autowired
	private PostingSystemService _PostingSystemService;
	
	@Autowired
	private MessageService _messageService;
	
	@Autowired
	private indexService _inIndexService;
	
	@GetMapping(value = "test")
	public ResponseEntity<List<Index>> HomePost(@CookieValue(value = "userName", defaultValue = "Atta") String userName,Integer postNumber) {
		postNumber = 1;
		return _inIndexService.HomePost(userName,postNumber);
	}
	
	@PostMapping(value = "test1")
	public ResponseEntity<List<Index>> HomePost1(@CookieValue(value = "userName", defaultValue = "Atta") String userName,@RequestBody PostNumber postNumber) {
		
		return _inIndexService.HomePost(userName,postNumber.getPostNumber());
	}
}
