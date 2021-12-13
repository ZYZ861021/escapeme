package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.CountTaskType;
import com.example.demo.model.Index;
import com.example.demo.model.ResultViewModel;
import com.example.demo.model.DB.Account;
import com.example.demo.model.DB.PostingSystem;
import com.example.demo.repository.MemberServiceRepository;
import com.example.demo.service.PostingSystemService;

@RestController
@RequestMapping("/PostingSystem")
public class PostingSystemController {

	@Autowired
	private PostingSystemService _postingSystemService;


	ResultViewModel rvm = new ResultViewModel();


	@GetMapping("/PostRank")
	public ResponseEntity<List<Index>> postRank(
			@CookieValue(value = "userName", defaultValue = "Atta") String userName) {
		return _postingSystemService.SelectPost(userName);

	}
	
	@GetMapping("/countTaskType")
	public CountTaskType countTaskType(@RequestParam (value = "id") Long member_id) {
		
		return _postingSystemService.countTaskType(member_id);
	}
}
